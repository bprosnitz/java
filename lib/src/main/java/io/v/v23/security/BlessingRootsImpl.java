package io.v.v23.security;

import io.v.v23.verror.VException;

import java.security.interfaces.ECPublicKey;

class BlessingRootsImpl implements BlessingRoots {
    private final long nativePtr;

    private native void nativeAdd(long nativePtr, ECPublicKey root, BlessingPattern pattern)
        throws VException;
    private native void nativeRecognized(long nativePtr, ECPublicKey root, String blessing)
        throws VException;
    private native String nativeDebugString(long nativePtr);
    private native String nativeToString(long nativePtr);
    private native void nativeFinalize(long nativePtr);

    private BlessingRootsImpl(long nativePtr) {
        this.nativePtr = nativePtr;
    }

    @Override
    public void add(ECPublicKey root, BlessingPattern pattern) throws VException {
        nativeAdd(this.nativePtr, root, pattern);
    }
    @Override
    public void recognized(ECPublicKey root, String blessing) throws VException {
        nativeRecognized(this.nativePtr, root, blessing);
    }
    @Override
    public String debugString() {
        return nativeDebugString(this.nativePtr);
    }
    @Override
    public String toString() {
        return nativeToString(this.nativePtr);
    }
    @Override
    public void finalize() {
        nativeFinalize(this.nativePtr);
    }
}