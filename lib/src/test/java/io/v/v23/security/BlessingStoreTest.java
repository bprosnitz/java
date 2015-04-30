// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.v23.security;

import com.google.common.collect.ImmutableMap;

import junit.framework.TestCase;

import io.v.v23.V;
import io.v.v23.verror.VException;

import java.util.Arrays;
import java.util.Map;

/**
 * Tests the default {@code BlessingStore} implementation.
 */
public class BlessingStoreTest extends TestCase {
    public void testSet() throws VException {
        V.init();
        final Principal principal = Security.newPrincipal();
        final BlessingStore store = principal.blessingStore();
        final Blessings blessingA = newBlessing(principal, "root", "A");
        final Blessings blessingB = newBlessing(principal, "root", "B");
        final Blessings blessingOther = Security.newPrincipal().blessSelf("other");
        final Map<BlessingPattern, Blessings> want =
                ImmutableMap.<BlessingPattern, Blessings>builder()
                .put(new BlessingPattern("..."), blessingA)
                .put(new BlessingPattern("foo"), blessingA)
                .put(new BlessingPattern("bar"), blessingB)
                .build();
        final Map<BlessingPattern, Blessings> errors =
                ImmutableMap.<BlessingPattern, Blessings>builder()
                .put(new BlessingPattern("..."), blessingOther)
                .put(new BlessingPattern(""), blessingA)
                .put(new BlessingPattern("foo..."), blessingA)
                .put(new BlessingPattern("...foo"), blessingB)
                .put(new BlessingPattern("foo/.../bar"), blessingB)
                .build();

        for (Map.Entry<BlessingPattern, Blessings> entry : want.entrySet()) {
            store.set(entry.getValue(), entry.getKey());
        }
        for (Map.Entry<BlessingPattern, Blessings> entry : errors.entrySet()) {
            try {
                store.set(entry.getValue(), entry.getKey());
                fail("Expected error for pattern: " + entry.getValue());
            } catch (VException e) {
                // OK
            }
        }
        final Map<BlessingPattern, Blessings> actual = store.peerBlessings();
        assertEquals(want, actual);
    }

    public void testSetDefault() throws VException {
        V.init();
        final Principal principal = Security.newPrincipal();
        final BlessingStore store = principal.blessingStore();
        final Blessings blessingA = newBlessing(principal, "root", "A");
        final Blessings blessingB = newBlessing(principal, "root", "B");
        assertTrue(store.defaultBlessings().isEmpty());
        store.setDefaultBlessings(blessingA);
        assertEquals(blessingA, store.defaultBlessings());
        store.setDefaultBlessings(blessingB);
        assertEquals(blessingB, store.defaultBlessings());
    }

    public void testForPeer() throws VException {
        V.init();
        final Principal principal = Security.newPrincipal();
        final BlessingStore store = principal.blessingStore();
        final Blessings blessingFoo = newBlessing(principal, "foo", "A");
        final Blessings blessingBar = newBlessing(principal, "bar", "B");
        final Blessings blessingAll = newBlessing(principal, "all", "C");
        store.set(blessingAll, new BlessingPattern("..."));
        store.set(blessingFoo, new BlessingPattern("foo"));
        store.set(blessingBar, new BlessingPattern("bar/$"));

        final Map<String[], Blessings> testdata =
               ImmutableMap.<String[], Blessings>builder()
               .put(new String[] {}, blessingAll)
               .put(new String[]{ "baz" }, blessingAll)
               .put(new String[]{ "foo" }, Security.unionOfBlessings(blessingAll, blessingFoo))
               .put(new String[]{ "bar" }, Security.unionOfBlessings(blessingAll, blessingBar))
               .put(new String[]{ "foo/foo" },
                       Security.unionOfBlessings(blessingAll, blessingFoo))
               .put(new String[] { "bar/baz" }, blessingAll)
               .put(new String[] { "foo/foo/bar" },
                       Security.unionOfBlessings(blessingAll, blessingFoo))
               .put(new String[] { "bar/foo", "foo" },
                       Security.unionOfBlessings(blessingAll, blessingFoo))
               .put(new String[] { "bar", "foo" },
                       Security.unionOfBlessings(blessingAll, blessingFoo, blessingBar))
               .build();
        for (int i = 0; i < 100; i++) {
            for (Map.Entry<String[], Blessings> entry : testdata.entrySet()) {
                store.forPeer(entry.getKey());
                assertEquals(entry.getValue(), store.forPeer(entry.getKey()));
            }
        }
    }

    private static Blessings newBlessing(Principal blessee, String root, String extension)
            throws VException {
        final Principal blesser = Security.newPrincipal();
        return blesser.bless(blessee.publicKey(), blesser.blessSelf(root), extension,
                Security.newUnconstrainedUseCaveat());
    }
}