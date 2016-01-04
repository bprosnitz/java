// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package io.v.android.libs.security;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import java.security.interfaces.ECPublicKey;
import java.util.UUID;

import io.v.android.impl.google.services.blessing.BlessingActivity;
import io.v.android.v23.V;
import io.v.v23.context.VContext;
import io.v.v23.security.Blessings;
import io.v.v23.security.Constants;
import io.v.v23.security.VPrincipal;
import io.v.v23.security.VSecurity;
import io.v.v23.verror.VException;
import io.v.v23.vom.VomUtil;

/**
 * Manages {@link Blessings} for a given Android application, persisting them in its
 * shared preferences.
 * <p>
 * This class is thread-safe.
 */
public class BlessingsManager extends Fragment {
    private static String TAG = "BlessingsManager";

    private static final int REQUEST_CODE_MINT_BLESSINGS = 1000;
    private static final String STATE_SAVED = "STATE_SAVED";

    private static SettableFuture<Blessings> mintFuture;

    private boolean wasDestroyed = false;
    private String prefKey;                   // may be null if wasDestroyed == true
    private String googleAccount;             // may be null if wasDestroyed == true

    /**
     * Returns a new {@link ListenableFuture} whose result are the {@link Blessings} found in
     * {@link SharedPreferences} under the given key.
     * <p>
     * If no {@link Blessings} are found, mints a new set of {@link Blessings} and stores them
     * in {@link SharedPreferences} under the provided key.
     * <p>
     * This method may start an activity to handle the creation of new blessings, if needed.
     * Hence, you should be prepared that your activity may be stopped and re-started.
     * <p>
     * This method is re-entrant: if blessings need to be minted, multiple concurrent invocations
     * of this method will result in only the last invocation's future ever being invoked.  This
     * means that it's safe to call this method from any of the android's lifecycle methods
     * (e.g., onCreate(), onStart(), onResume()).
     * <p>
     * This method must be invoked on the UI thread.
     *
     * @param activity     android {@link Activity} requesting blessings
     * @param key          a key under which the blessings are stored
     * @param setAsDefault if true, the returned {@link Blessings} will be set as default
     *                     blessings for the app
     * @return             a new {@link ListenableFuture} whose result are the blessings
     *                     persisted under the given key
     */
    public static ListenableFuture<Blessings> getBlessings(
            final Activity activity, String key, boolean setAsDefault) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return Futures.immediateFailedFuture(new VException("getBlessings() must be invoked " +
                    "on the UI thread"));
        }
        try {
            Blessings blessings = readBlessings(activity, key);
            if (blessings != null) {
                // TODO(spetrovic): validate the blessings and mint if they aren't valid.
                ListenableFuture<Blessings> ret = Futures.immediateFuture(blessings);
                return setAsDefault ? wrapWithSetAsDefault(activity, ret) : ret;
            }
        } catch (VException e) {
            Log.e(TAG, "Malformed blessings in SharedPreferences. Minting new blessings: " +
                    e.getMessage());
        }
        return mintBlessings(activity, key, setAsDefault);
    }

    /**
     * A shortcut for {@link #mintBlessings(Activity, String, String, boolean)}} with empty
     * Google account, causing the user to be prompted to pick one of the installed Google
     * accounts (if there is more than one installed).
     */
    public static ListenableFuture<Blessings> mintBlessings(
            Activity activity, final String key, boolean setAsDefault) {
        return mintBlessings(activity, key, "", setAsDefault);
    }

    /**
     * Mints a new set of {@link Blessings} that are persisted in {@link SharedPreferences} under
     * the provided key.
     * <p>
     * If {@code googleAccount} is non-{@code null} and non-empty, mints the blessings using
     * that account;  otherwise, prompts the user to pick one of the installed Google accounts
     * (if there is more than one installed).
     * <p>
     * This method will start an activity to handle the creation of new blessings.  Hence, you
     * should be prepared that your activity will be stopped and re-started, at the minimum.
     * <p>
     * This method is re-entrant: if invoked the 2nd time while the 1st invocation is still
     * pending, the future associated with the 2nd invocation will overwrite the 1st future: the
     * 1st future will never be invoked.
     * <p>
     * This method must be invoked on the UI thread.
     *
     * @param activity      android {@link Activity} requesting blessings
     * @param key           a key in {@link SharedPreferences} under which the newly minted
     *                      blessings are persisted
     * @param googleAccount a Google account to use to mint the blessings; if {@code null} or
     *                      empty, user will be prompted to pick one of the installed Google
     *                      accounts, if there is more than one installed
     * @param setAsDefault  if true, the returned {@link Blessings} will be set as default
     *                      blessings for the app
     * @return              a new {@link ListenableFuture} whose result are the newly minted
     *                      {@link Blessings}
     */
    public static ListenableFuture<Blessings> mintBlessings(
            final Activity activity, String key, String googleAccount, boolean setAsDefault) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return Futures.immediateFailedFuture(new VException("mintBlessings() must be invoked " +
                    "on the UI thread"));
        }
        if (mintFuture != null) {
            // Mint already in progress, which means that the invoking activity has been
            // destroyed and then recreated.  Register the new future to be invoked on completion
            // of that mint.  Note that it is safe and desirable to override the old future
            // as it's invocation would be handled by a destroyed activity.
            mintFuture = SettableFuture.create();
            return setAsDefault ? wrapWithSetAsDefault(activity, mintFuture) : mintFuture;
        }
        mintFuture = SettableFuture.create();
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        BlessingsManager fragment = new BlessingsManager();
        fragment.prefKey = key;
        fragment.googleAccount = googleAccount;
        transaction.add(fragment, UUID.randomUUID().toString());
        transaction.commit();  // this will invoke the fragment's onCreate() immediately.
        return setAsDefault ? wrapWithSetAsDefault(activity, mintFuture) : mintFuture;
    }

    public BlessingsManager() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // onCreate() being called with non-null savedInstanceState is an indicator that the
        // fragment (and the containing activity) have been destroyed since originally
        // created, as onCreate() wouldn't be called again, otherwise.
        wasDestroyed = savedInstanceState != null;
        if (!wasDestroyed) {
            // Start the intent to fetch the blessings.
            VContext vCtx = V.init(getActivity());
            ECPublicKey pubKey = V.getPrincipal(vCtx).publicKey();
            Intent intent = new Intent(getActivity(), BlessingActivity.class);
            intent.putExtra(BlessingActivity.EXTRA_PUBLIC_KEY, pubKey);
            if (googleAccount != null && !googleAccount.isEmpty()) {
                intent.putExtra(BlessingActivity.EXTRA_GOOGLE_ACCOUNT, googleAccount);
            }
            intent.putExtra(BlessingActivity.EXTRA_PREF_KEY, prefKey);
            startActivityForResult(intent, REQUEST_CODE_MINT_BLESSINGS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Just write something into the bundle (see onCreate() above).
        savedInstanceState.putBoolean(STATE_SAVED, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_MINT_BLESSINGS: {
                if (mintFuture == null) {  // shouldn't really happen
                    break;
                }
                SettableFuture<Blessings> future = mintFuture;
                mintFuture = null;
                V.init(getActivity());
                // Extract VOM-encoded blessings.
                if (data == null) {
                    future.setException(new VException("NULL blessing response"));
                    break;
                }
                if (resultCode != Activity.RESULT_OK) {
                    future.setException(new VException("Error getting blessing: " +
                            data.getStringExtra(BlessingActivity.EXTRA_ERROR)));
                    break;
                }
                byte[] blessingsVom = data.getByteArrayExtra(BlessingActivity.EXTRA_REPLY);
                if (blessingsVom == null || blessingsVom.length <= 0) {
                    future.setException(new VException("Got null blessings."));
                    break;
                }
                // VOM-Decode blessings.
                try {
                    Blessings blessings =
                            (Blessings) VomUtil.decode(blessingsVom, Blessings.class);
                    future.set(blessings);
                } catch (VException e) {
                    future.setException(e);
                }
                break;
            }
        }
        // Remove this fragment from the invoking activity.
        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
        transaction.remove(this);
        transaction.commit();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private static Blessings readBlessings(Context context, String prefKey)
            throws VException {
        String blessingsVom =
                PreferenceManager.getDefaultSharedPreferences(context).getString(prefKey, "");
        if (blessingsVom == null || blessingsVom.isEmpty()) {
            return null;
        }
        return (Blessings) VomUtil.decodeFromString(blessingsVom, Blessings.class);
    }

    private static ListenableFuture<Blessings> wrapWithSetAsDefault(
            final Context context, ListenableFuture<Blessings> future) {
        return Futures.transform(future, new AsyncFunction<Blessings, Blessings>() {
            @Override
            public ListenableFuture<Blessings> apply(Blessings blessings) throws Exception {
                try {
                    // Update local state with the new blessings.
                    VContext vCtx = V.init(context);
                    VPrincipal p = V.getPrincipal(vCtx);
                    p.blessingStore().setDefaultBlessings(blessings);
                    p.blessingStore().set(blessings, Constants.ALL_PRINCIPALS);
                    VSecurity.addToRoots(p, blessings);
                    return Futures.immediateFuture(blessings);
                } catch (VException e) {
                    return Futures.immediateFailedFuture(e);
                }
            }
        });
    }
}
