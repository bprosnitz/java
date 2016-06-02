// Copyright 2016 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.syncbase;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.SettableFuture;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.v.v23.V;
import io.v.v23.context.VContext;
import io.v.v23.rpc.ListenSpec;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SyncbaseTest {
    private VContext ctx;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    // To run these tests from Android Studio, add the following VM option to the default JUnit
    // build configuration, via Run > Edit Configurations... > Defaults > JUnit > VM options:
    // -Djava.library.path=/Users/sadovsky/vanadium/release/java/syncbase/build/libs
    @Before
    public void setUp() throws Exception {
        ctx = V.init();
        ctx = V.withListenSpec(ctx, V.getListenSpec(ctx).withAddress(
                new ListenSpec.Address("tcp", "localhost:0")));
    }

    private Syncbase.DatabaseOptions newDatabaseOptions() {
        Syncbase.DatabaseOptions opts = new Syncbase.DatabaseOptions();
        // Use a fresh rootDir for each test run.
        try {
            opts.rootDir = folder.newFolder().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        opts.disableUserdataSyncgroup = true;
        opts.disableSyncgroupPublishing = true;
        opts.vContext = ctx;
        return opts;
    }

    private Database createDatabase() throws Exception {
        final SettableFuture<Database> future = SettableFuture.create();

        Syncbase.database(new Syncbase.DatabaseCallback() {
            @Override
            public void onSuccess(Database db) {
                future.set(db);
            }

            @Override
            public void onError(Throwable e) {
                future.setException(e);
            }
        }, newDatabaseOptions());

        return future.get(5, TimeUnit.SECONDS);
    }

    private static Iterable<Id> getCollectionIds(Database db) {
        List<Id> res = new ArrayList<>();
        for (Iterator<Collection> it = db.getCollections(); it.hasNext(); ) {
            res.add(it.next().getId());
        }
        return res;
    }

    private static Iterable<Id> getSyncgroupIds(Database db) {
        List<Id> res = new ArrayList<>();
        for (Iterator<Syncgroup> it = db.getSyncgroups(); it.hasNext(); ) {
            res.add(it.next().getId());
        }
        return res;
    }

    @Test
    public void testCreateDatabase() throws Exception {
        createDatabase();
    }

    @Test
    public void testCreateAndGetCollections() throws Exception {
        Database db = createDatabase();
        DatabaseHandle.CollectionOptions opts = new DatabaseHandle.CollectionOptions();
        opts.withoutSyncgroup = true;
        Collection cxA = db.collection("a", opts);
        // TODO(sadovsky): Should we omit the userdata collection?
        assertThat(getCollectionIds(db)).containsExactly(
                new Id(Syncbase.getPersonalBlessingString(), "a"),
                new Id(Syncbase.getPersonalBlessingString(), "userdata__"));
        db.collection("b", opts);
        assertThat(getCollectionIds(db)).containsExactly(
                new Id(Syncbase.getPersonalBlessingString(), "a"),
                new Id(Syncbase.getPersonalBlessingString(), "b"),
                new Id(Syncbase.getPersonalBlessingString(), "userdata__"));
        // Note, createDatabase() sets disableSyncgroupPublishing to true, so db.collection(name) is
        // a purely local operation.
        db.collection("c");
        assertThat(getCollectionIds(db)).containsExactly(
                new Id(Syncbase.getPersonalBlessingString(), "a"),
                new Id(Syncbase.getPersonalBlessingString(), "b"),
                new Id(Syncbase.getPersonalBlessingString(), "c"),
                new Id(Syncbase.getPersonalBlessingString(), "userdata__"));
        Collection secondCxA = db.collection("a", opts);
        assertEquals(cxA.getId(), secondCxA.getId());
    }

    @Test
    public void testRowCrudMethods() throws Exception {
        Database db = createDatabase();
        Collection cx = db.collection("cx");
        assertFalse(cx.exists("foo"));
        assertEquals(cx.get("foo", String.class), null);
        cx.put("foo", "bar");
        assertTrue(cx.exists("foo"));
        assertEquals(cx.get("foo", String.class), "bar");
        cx.put("foo", "baz");
        assertTrue(cx.exists("foo"));
        assertEquals(cx.get("foo", String.class), "baz");
        cx.delete("foo");
        assertFalse(cx.exists("foo"));
        assertEquals(cx.get("foo", String.class), null);
        cx.put("foo", 5);
        assertEquals(cx.get("foo", Integer.class), Integer.valueOf(5));

        // This time, with a POJO.
        class MyObject {
            String str;
            int num;
        }
        MyObject putObj = new MyObject();
        putObj.str = "hello";
        putObj.num = 7;
        cx.put("foo", putObj);
        MyObject getObj = cx.get("foo", MyObject.class);
        assertEquals(putObj.str, getObj.str);
        assertEquals(putObj.num, getObj.num);
    }

    @Test
    public void testCreateAndGetSyncgroups() throws Exception {
        Database db = createDatabase();
        DatabaseHandle.CollectionOptions opts = new DatabaseHandle.CollectionOptions();
        opts.withoutSyncgroup = true;
        Collection cxA = db.collection("a", opts);
        Collection cxB = db.collection("b", opts);
        Collection cxC = db.collection("c");
        // Note, there's no userdata syncgroup since we set disableUserdataSyncgroup to true.
        assertThat(getSyncgroupIds(db)).containsExactly(
                new Id(Syncbase.getPersonalBlessingString(), "c"));
        db.syncgroup("sg1", ImmutableList.of(cxA));
        db.syncgroup("sg2", ImmutableList.of(cxA, cxB, cxC));
        assertThat(getSyncgroupIds(db)).containsExactly(
                new Id(Syncbase.getPersonalBlessingString(), "c"),
                new Id(Syncbase.getPersonalBlessingString(), "sg1"),
                new Id(Syncbase.getPersonalBlessingString(), "sg2"));
    }

    @Test
    public void testWatch() throws Exception {
        // TODO(sadovsky): Implement.
    }

    @Test
    public void testRunInBatch() throws Exception {
        // TODO(sadovsky): Implement.
    }
}