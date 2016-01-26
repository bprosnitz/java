// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.baku.toolkit;

import android.app.Activity;

import io.v.baku.toolkit.bind.SyncbaseBinding;
import io.v.baku.toolkit.bind.CollectionBinding;
import io.v.baku.toolkit.syncbase.BakuDb;
import io.v.baku.toolkit.syncbase.BakuSyncbase;
import io.v.baku.toolkit.syncbase.BakuTable;
import io.v.rx.syncbase.GlobalUserSyncgroup;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import rx.subscriptions.CompositeSubscription;

/**
 * Activity trait for activities with distributed UI state. By default, shared state is stored
 * in Syncbase under <i>app.package.name</i>/db/ui.
 * <p>
 * Default activity extensions incorporating this mix-in are available:
 * <ul>
 * <li>{@link BakuActivity} (extends {@link Activity})</li>
 * <li>{@link BakuAppCompatActivity} (extends {@link android.support.v7.app.AppCompatActivity})</li>
 * </ul>
 * Since Java doesn't actually support multiple inheritance, clients requiring custom inheritance
 * hierarchies will need to wire in manually, like any of the examples above.
 */
@Accessors(prefix = "m")
@Slf4j
public class BakuActivityMixin<T extends Activity> implements BakuActivityTrait<T> {
    @Getter
    private final VAndroidContextTrait<T> mVAndroidContextTrait;

    @Getter
    private final BakuSyncbase mSyncbase;
    @Getter
    private final BakuDb mSyncbaseDb;
    @Getter
    private final BakuTable mSyncbaseTable;
    @Getter
    private final CompositeSubscription mSubscriptions;

    public BakuActivityMixin(final VAndroidContextTrait<T> vAndroidContextTrait) {
        mVAndroidContextTrait = vAndroidContextTrait;

        mSubscriptions = new CompositeSubscription();
        mSyncbase = new BakuSyncbase(this);

        final String app = getSyncbaseAppName(),
                db = getSyncbaseDbName(),
                t = getSyncbaseTableName();
        log.info("Mapping Syncbase path: {}/{}/{}", app, db, t);
        mSyncbaseDb = mSyncbase.rxApp(app).rxDb(db);
        mSyncbaseTable = mSyncbaseDb.rxTable(t);

        joinInitialSyncGroup();
    }

    @Override
    public void close() {
        mSubscriptions.unsubscribe();
        mSyncbase.close();
    }

    protected String getSyncbaseAppName() {
        return mVAndroidContextTrait.getAndroidContext().getPackageName();
    }

    protected String getSyncbaseDbName() {
        return "db";
    }

    public String getSyncbaseTableName() {
        return "ui";
    }

    protected void joinInitialSyncGroup() {
        GlobalUserSyncgroup.forActivity(this).join();
    }

    public void onSyncError(final Throwable t) {
        mVAndroidContextTrait.getErrorReporter().onError(R.string.err_sync, t);
    }

    public <U> SyncbaseBinding.Builder<U> binder() {
        return SyncbaseBinding.<U>builder()
                .bakuActivity(this);
    }

    public CollectionBinding.Builder collectionBinder() {
        return CollectionBinding.builder()
                .bakuActivity(this);
    }
}
