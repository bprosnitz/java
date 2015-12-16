// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.moments.ux;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.v.moments.R;
import io.v.moments.ifc.Advertiser;
import io.v.moments.ifc.Moment;
import io.v.moments.ifc.MomentFactory;
import io.v.moments.lib.DiscoveredList;
import io.v.moments.lib.Id;
import io.v.moments.lib.ObservedList;
import io.v.moments.lib.PermissionManager;
import io.v.moments.lib.V23Manager;
import io.v.moments.model.AdConverterMoment;
import io.v.moments.model.AdvertiserFactory;
import io.v.moments.model.BitMapper;
import io.v.moments.model.Config;
import io.v.moments.model.FileUtil;
import io.v.moments.model.MomentFactoryImpl;
import io.v.moments.model.StateStore;
import io.v.v23.context.CancelableVContext;

/**
 * This app allows the user to take photos and advertise them on the network.
 * Other instances of the app will scan for and display such photos.
 *
 * This app is an example of running, advertising and scanning for multiple
 * services.
 *
 * A photo and its ancillary data (id, author, caption, date, ordinal number,
 * etc.) are called a Moment.  There are _local_ moments, created locally, and
 * _remote_ moments, found via discovery.  Local moments can be advertised so
 * that remote devices can discover and request them.  Remote moments cannot be
 * re-advertised.
 *
 * Local moments are persistent, in that the user must delete them to get rid of
 * them.  Remote moments are pulled in over the network, and not officially
 * retained between runs, though remote photo data may be left on disc between
 * runs as a simple cache. The moment's id is used to invalidate the cache.
 *
 * Every local moment is served by its own service.  This egregious use of
 * services allows for easy creation of multiple scan targets to exercise
 * discovery code. The involvement of a photo forces use of RPC since a photo is
 * too large to send as an advertisement attribute.
 */
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    // Android Marshmallow permissions list.
    private static final String[] PERMS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_ADMIN,
    };
    // A string that unambiguously identifies the phone in human readable form.
    private static final String DEVICE_SIGNATURE = Build.MODEL + " " + Build.SERIAL;
    // For Marshmallow permissions.
    private final PermissionManager mPermissionManager
            = new PermissionManager(this, RequestCode.PERMISSIONS, PERMS);
    // Use a serial executor to assure serial execution, e.g. toggling a switch
    // on and off without a race condition.
    private final ExecutorService mSerialExecutor = Executors.newSingleThreadExecutor();
    // Use a pool when order isn't important.
    private final ExecutorService mPoolExecutor = Executors.newCachedThreadPool();
    // For changes to UX.
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    // For discovery, serving and behaving as a client.
    private final V23Manager mV23Manager = V23Manager.Singleton.get();

    // See wireItUp for discussion of the following.
    private StateStore mStateStore;
    private AdvertiserFactory mAdvertiserFactory;
    private MomentFactory mMomentFactory;
    private BitMapper mBitMapper;
    private ObservedList<Moment> mLocalMoments;
    private DiscoveredList<Moment> mRemoteMoments;
    private CancelableVContext mScanCtx;
    private boolean mShouldBeScanning;
    private Id mCurrentPhotoId;

    /**
     * The number used in a moment's file name is called the moments 'ordinal'
     * number.  At the time of writing local moments are never individually
     * deleted - either they are all kept or all deleted when app data is
     * deleted. Therefore, the _next_ local ordinal is just an array size
     * increment.
     *
     * Remote (discovered) moments, however, come and go individually, so one
     * cannot use, say, the size of the current list of remote moments to
     * determine the next ordinal number, as one might use a number already in
     * use, and display the wrong photo.
     *
     * When the phone is rotated, discovery scanning stops, then restarts.  So
     * known moments are immediately 're-'discovered.  To avoid reacquiring
     * data, retain and use a cache of known remote moments.  When a discovery
     * is made, check the map, and if there's a hit, there's no need to contact
     * the remote service.  The cache can also be used to compute the 'next'
     * ordinal number for remote services.
     */
    private Map<Id, Moment> mRemoteMomentCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logState("onCreate");

        setContentView(R.layout.activity_main);

        wireItUp();

        initializeOrRestore(savedInstanceState);


        // This might leave to start a new activity.
        // Will trigger onActivityResult as expected.
        mV23Manager.init(getApplicationContext(), this);
    }

    /**
     * This method builds the app's object graph, and intentionally has no
     * branches that depend on state loaded from the app's prefs or instance
     * bundle. State is loaded after the wiring is complete, to make phone
     * rotation easy.
     */
    private void wireItUp() {
        // Stores remote moments by Id to avoid having to wait for re-discovery.
        mRemoteMomentCache = new HashMap<>();

        // Compresses byte data, converts byte[] to bitmap, manages file storage.
        mBitMapper = Config.makeBitmapper(this);

        // Makes moments.  Each moment needs a bitmapper to read its BitMaps.
        mMomentFactory = new MomentFactoryImpl(mBitMapper);

        // Local moments, with photos taken by the local device.
        mLocalMoments = new ObservedList<>();

        // Makes advertisers.  Needs v23Manager to do advertising, needs
        // mMomentFactory to create advertisements from Moments.
        mAdvertiserFactory = new AdvertiserFactory(mV23Manager, mMomentFactory);

        // Converts advertisements to 'remote' moments.  Needs v23Manager to
        // make RPCs, needs mMomentFactory to make moments, needs a thread pool
        // for making RPCs to get photos, needs mHandler to post photos on the
        // UX thread, fills the cache with remote moments.
        AdConverterMoment converter = new AdConverterMoment(
                mV23Manager, mMomentFactory, mPoolExecutor,
                mHandler, mRemoteMomentCache);

        // The list of remote (discovered) moments.  Pass mAdvertiserFactory
        // as a container of Id's to reject from discovery (because they
        // represent local moments).
        mRemoteMoments = new DiscoveredList<>(converter, mAdvertiserFactory, mHandler);

        // Stores app state to bundles, preferences, etc.  The mMomentFactory
        // needed to recreate moments.
        mStateStore = new StateStore(
                getSharedPreferences(
                        nameOfSharedPrefs(), Context.MODE_PRIVATE),
                mMomentFactory);

        // Tell the converter where to place remote moments.
        converter.setList(mRemoteMoments);

        // The adapter allows remote and local moment lists to 'stack' in a
        // RecyclerView.  mAdvertiserFactory is used to generate advertisers
        // for local moments when a user wants to advertise them.  The
        // serialExecutor is used to start/stop advertisements in the UX.
        MomentAdapter adapter = new MomentAdapter(
                mRemoteMoments, mLocalMoments,
                mAdvertiserFactory, mSerialExecutor, mHandler);

        // Lets the adapter speed up a bit.
        adapter.setHasStableIds(true);

        // Hook the adapter to a view, and begin observing changes to the
        // moment lists.
        RecyclerView view = configureRecyclerView();
        view.setAdapter(adapter);
        adapter.beginObserving();

        // Expose the scan switch on the action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // Expose the camera button.
        setFabClickHandler(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });
    }

    private void setFabClickHandler(View.OnClickListener listener) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(listener);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        logState("onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        logState("onResume");
        if (mV23Manager.isBlessed()) {
            if (mPermissionManager.haveAllPermissions()) {
            } else {
                if (!mPermissionManager.isRequestInProgress()) {
                    mPermissionManager.obtainPermission();
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        logState("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        logState("onStop");
    }

    /**
     * Before calling this, must have permission to read/write to storage, to
     * read/write photo data. Don't need camera permission, since a new activity
     * is started to actually take the photo.
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mCurrentPhotoId = Id.makeRandom();
        Integer ordinal = mLocalMoments.size() + 1;
        Uri uri = mBitMapper.getCameraPhotoUri(ordinal);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, RequestCode.CAPTURE_IMAGE);
    }


    /**
     * On new permissions, assume it's a fresh install and wipe the working
     * directory.   File formats and naming might have changed.  This is not
     * good for permanent photo management obviously.
     */
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] results) {
        logState("onRequestPermissionsResult");
        if (mPermissionManager.granted(requestCode, permissions, results)) {
            FileUtil.initializeDirectory(
                    Config.getWorkingDirectory(this));
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(
                        MainActivity.this,
                        R.string.need_permissions,
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    /**
     * A more realistic example would obtain the phone owner's name or email
     * address from, say, the contacts list.  Instead using a device
     * identifier.
     */
    public String getAuthorName() {
        return DEVICE_SIGNATURE;
    }

    /**
     * Could prompt the user for this; using a label derived from the id
     * instead.
     */
    public String getCaption(int index) {
        return "Generated caption " + index;
    }

    @Override
    protected void onStart() {
        super.onStart();
        logState("onStart");
        if (mLocalMoments.isEmpty()) {
            Log.d(TAG, "Loading moments from prefs.");
            mStateStore.prefsLoad(mLocalMoments);
        }
        for (Moment moment : mLocalMoments) {
            if (moment.shouldBeAdvertising()) {
                Log.d(TAG, "on start - found a moment that should be advertising");
            }
        }
        if (mShouldBeScanning && !isScanning()) {
            startScanning();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logState("onDestroy");
        if (isScanning()) {
            stopScanning();
        }
        // A better impl would save advertising state for restoration.
        stopAllAdvertising();
        Log.d(TAG, "Destruction complete.");
    }

    private void stopAllAdvertising() {
        for (Advertiser advertiser : mAdvertiserFactory.allAdvertisers()) {
            if (advertiser.isAdvertising()) {
                try {
                    advertiser.advertiseStop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "A moment has stopped advertising");
            } else {
                Log.d(TAG, "A moment was not advertising");
            }
        }
    }

    private RecyclerView configureRecyclerView() {
        RecyclerView view = (RecyclerView) findViewById(R.id.all_moments);
        view.setLayoutManager(makeLayoutManager());
        // Add some lines between items
        view.addItemDecoration(new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        // This makes it faster, but at a cost.
        view.setHasFixedSize(true);
        return view;
    }

    private LinearLayoutManager makeLayoutManager() {
        LinearLayoutManager mgr = new LinearLayoutManager(this);
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        mgr.scrollToPosition(0);
        return mgr;
    }

    private boolean isScanning() {
        return mScanCtx != null;
    }

    private Runnable setScanningSwitch(final boolean on) {
        return new Runnable() {
            @Override
            public void run() {
                SwitchCompat sw = (SwitchCompat) findViewById(R.id.action_scan);
                if (sw != null) {
                    sw.setChecked(on);
                    if (on) {
                        toast("Started scanning.");
                    } else {
                        toast("Stopped scanning.");
                    }
                }
            }
        };
    }

    private void toast(final String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void startScanning() {
        if (isScanning()) {
            throw new IllegalStateException("Already scanning.");
        }
        mSerialExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Starting scan.");
                mScanCtx = mV23Manager.scan(Config.QUERY, mRemoteMoments);
                runOnUiThread(setScanningSwitch(true));
                Log.d(TAG, "Scan started.");
            }
        });
    }

    private void stopScanning() {
        if (!isScanning()) {
            throw new IllegalStateException("Not scanning.");
        }
        mSerialExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Stopping scan.");
                mScanCtx.cancel();
                mScanCtx = null;
                mRemoteMoments.dropAll();
                runOnUiThread(setScanningSwitch(false));
                Log.d(TAG, "Scan stopped.");
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        if (button.getId() != R.id.action_scan) {
            throw new IllegalStateException("Bad scan wiring.");
        }
        if (isChecked) {
            mShouldBeScanning = true;
            if (!isScanning()) {
                startScanning();
            }
        } else {
            mShouldBeScanning = false;
            if (isScanning()) {
                stopScanning();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_scan);
        ((SwitchCompat) MenuItemCompat.getActionView(item)).setOnCheckedChangeListener(this);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        logState("onActivityResult");
        if (V23Manager.onActivityResult(
                getApplicationContext(), requestCode, resultCode, data)) {
            Log.d(TAG, "Got the v23 result");
            if (!mPermissionManager.haveAllPermissions()) {
                Log.d(TAG, "Post v23 trying to get permissions");
                mPermissionManager.obtainPermission();
            }
            return;
        }
        if (requestCode == RequestCode.CAPTURE_IMAGE) {
            if (resultCode == RESULT_OK) {
                processCapturedPhoto();
            }
        }
    }

    private void processCapturedPhoto() {
        int ordinal = mLocalMoments.size() + 1;
        final Moment moment = mMomentFactory.make(
                mCurrentPhotoId, ordinal,
                getAuthorName(), getCaption(ordinal));
        Log.d(TAG, "pcp:     moment = " + moment);
        Log.d(TAG, "pcp:  list size = " + mLocalMoments.size());
        mLocalMoments.push(moment);
        mSerialExecutor.submit(new Runnable() {
            @Override
            public void run() {
                mBitMapper.dealWithCameraResult(
                        mLocalMoments, moment);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle b) {
        super.onSaveInstanceState(b);
        logState("onSaveInstanceState");
        b.putBoolean(B.SHOULD_BE_SCANNING, mShouldBeScanning);
        mStateStore.bundleSave(b, mRemoteMomentCache.values());
        mStateStore.prefsSave(mLocalMoments);
    }

    private void initializeOrRestore(Bundle b) {
        mStateStore.prefsLoad(mLocalMoments);
        if (b == null) {
            Log.d(TAG, "No bundle passed, starting fresh.");
            mShouldBeScanning = false;
            mRemoteMomentCache.clear();
            return;
        }
        Log.d(TAG, "Reloading from bundle.");
        mShouldBeScanning = b.getBoolean(B.SHOULD_BE_SCANNING, false);
        mStateStore.bundleLoad(b, mRemoteMomentCache);
    }

    private String nameOfSharedPrefs() {
        return getClass().getPackage() +
                "." + getString(R.string.photo_file_prefix);
    }

    private void logState(String state) {
        Log.d(TAG, state + " --------------------------------------------");
    }

    private static class RequestCode {
        static final int PERMISSIONS = 1001;
        static final int CAPTURE_IMAGE = 1003;
    }

    private static class B {
        static final String SHOULD_BE_SCANNING = "SHOULD_BE_SCANNING";
    }

}