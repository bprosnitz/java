// Copyright 2016 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.moments.ux;

import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;

import com.google.common.util.concurrent.FutureCallback;

import org.joda.time.Duration;

import java.util.concurrent.CancellationException;

import io.v.moments.ifc.Moment;
import io.v.moments.lib.DiscoveredList;
import io.v.moments.model.Toaster;
import io.v.moments.v23.ifc.Scanner;

/**
 * Manages scanning UX.
 *
 * The callbacks provided will be run on the UX thread, so its safe to perform
 * UX operations in the callbacks.
 */
class ScanSwitchHolder implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "ScanSwitchHolder";
    /**
     * After this duration a scan automatically stop. Choice is arbitrary.
     */
    private static final Duration DURATION = Duration.standardMinutes(5);
    private final Scanner mScanner;
    private final Toaster mToaster;
    private final DiscoveredList<Moment> mRemoteMoments;
    private SwitchCompat mSwitch;

    public ScanSwitchHolder(
            Toaster toaster, Scanner scanner,
            DiscoveredList<Moment> remoteMoments) {
        if (toaster == null) {
            throw new IllegalArgumentException("Null activity.");
        }
        if (scanner == null) {
            throw new IllegalArgumentException("Null scanner.");
        }
        if (remoteMoments == null) {
            throw new IllegalArgumentException("Null remoteMoments.");
        }
        mToaster = toaster;
        mScanner = scanner;
        mRemoteMoments = remoteMoments;
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        Log.d(TAG, "onCheckedChanged with checked = " + isChecked);
        if (button.getId() != mSwitch.getId()) {
            throw new IllegalStateException("Bad scan wiring.");
        }
        if (isChecked) {
            if (mScanner.isScanning()) {
                Log.d(TAG, "Asked to start scanning, but already scanning.");
                return;
            }
            mScanner.start(
                    makeStartupCallback(),
                    mRemoteMoments,
                    mRemoteMoments,
                    makeCompletionCallback(),
                    DURATION);
        } else {
            if (!mScanner.isScanning()) {
                Log.d(TAG, "Asked to stop scanning, but already not scanning.");
                return;
            }
            mScanner.stop();
        }
    }

    public void setSwitch(SwitchCompat theSwitch) {
        mSwitch = theSwitch;
        theSwitch.setOnCheckedChangeListener(this);
    }

    private FutureCallback<Void> makeStartupCallback() {
        return new FutureCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                Log.d(TAG, "Started scanning.");
                mToaster.toast("Scanning.");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "Unable to scan.", t);
                if (mSwitch.isChecked()) {
                    Log.d(TAG, "On failure settings switch to off.");
                    mSwitch.setChecked(false);
                }
                mToaster.toast("Unable to scan.");
            }
        };
    }

    /**
     * Verify that scanning is off and that the UX reflects that fact.
     */
    private void cleanUpPostStop() {
        Log.d(TAG, "cleanUpPostStop");
        if (mScanner.isScanning()) {
            throw new IllegalStateException("Scanning should be off.");
        }
        if (mToaster.isDestroyed()) {
            Log.d(TAG, "The activity is dead, no UX to fix.");
            return;
        }
        mRemoteMoments.dropAll();
        if (mSwitch.isChecked()) {
            // The button might be checked if the scan expired.
            // The following call will trigger onCheckedChanged(false), but
            // shouldn't do more than change the UX because everything is
            // already shutdown.
            mSwitch.setChecked(false);
        }
    }

    private FutureCallback<Void> makeCompletionCallback() {
        return new FutureCallback<Void>() {
            /** Likely this is never called. */
            @Override
            public void onSuccess(Void result) {
                cleanUpPostStop();
            }

            @Override
            public void onFailure(Throwable t) {
                if (t instanceof CancellationException) {
                    // At the time of writing, the only way scanning ends
                    // is by throwing this exception, so this is actually
                    // a non-exceptional success case.
                    cleanUpPostStop();
                } else {
                    Log.d(TAG, "Failure to gracefully stop scanning.", t);
                }
            }
        };
    }
}
