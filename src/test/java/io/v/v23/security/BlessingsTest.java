package io.v.v23.security;

import android.test.AndroidTestCase;

import io.v.v23.verror.VException;
import io.v.v23.android.V;

import java.util.Arrays;

/**
 * Tests the default Blessings implementation.
 */
public class BlessingsTest extends AndroidTestCase {
    public void testForContext() {
        try {
            V.init(getContext(), null);
            final Principal p1 = Security.newPrincipal();
            final Principal p2 = Security.newPrincipal();
            final Blessings alice = p1.blessSelf("alice");
            p2.addToRoots(alice);

            final Blessings aliceWorkFriend = p1.bless(p2.publicKey(),
                    alice, "work/friend", Security.newUnconstrainedUseCaveat());
            final VContext ctx = Security.newContext(new VContextParams().withLocalPrincipal(p2));
            final String[] blessings = aliceWorkFriend.forContext(ctx);
            assertTrue(Arrays.equals(new String[]{ "alice/work/friend" }, blessings));
        } catch (VException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    public void testPublicKey() {
        try {
            V.init(getContext(), null);
            final Principal p1 = Security.newPrincipal();
            final Principal p2 = Security.newPrincipal();
            final Blessings alice = p1.blessSelf("alice");
            assertTrue(Arrays.equals(p1.publicKey().getEncoded(), alice.publicKey().getEncoded()));
            p2.addToRoots(alice);

            final Blessings aliceWorkFriend = p1.bless(p2.publicKey(),
                    alice, "work/friend", Security.newUnconstrainedUseCaveat());
            assertTrue(Arrays.equals(
                    p2.publicKey().getEncoded(), aliceWorkFriend.publicKey().getEncoded()));
        } catch (VException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}