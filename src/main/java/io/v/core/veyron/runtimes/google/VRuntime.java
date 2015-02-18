package io.v.core.veyron.runtimes.google;

import io.v.core.veyron2.Options;
import io.v.core.veyron2.verror.VException;
import io.v.core.veyron2.context.VContext;
import io.v.core.veyron2.ipc.ListenSpec;
import io.v.core.veyron2.naming.Namespace;
import io.v.core.veyron2.security.Principal;

/**
 * VRuntime is an implementation of {@code io.v.core.veyron2.VRuntime} that calls to native Go
 * code for most of its functionalities.
 */
public class VRuntime implements io.v.core.veyron2.VRuntime {
    private static final String TAG = "Veyron runtime";

    private static native VContext nativeInit() throws VException;
    private static native VContext nativeSetNewClient(VContext ctx, Options opts)
            throws VException;
    private static native io.v.core.veyron2.ipc.Client nativeGetClient(VContext ctx)
            throws VException;
    private static native io.v.core.veyron2.ipc.Server nativeNewServer(
            VContext ctx, ListenSpec spec) throws VException;
    private static native VContext nativeSetPrincipal(VContext ctx, Principal principal)
            throws VException;
    private static native Principal nativeGetPrincipal(VContext ctx) throws VException;
    private static native VContext nativeSetNewNamespace(VContext ctx, String... roots)
            throws VException;
    private static native Namespace nativeGetNamespace(VContext ctx) throws VException;

    /**
     * Returns a new runtime instance.
     *
     * @return      a new runtime instance
     */
    public static VRuntime create() throws VException {
        return new VRuntime(nativeInit());
    }

    private final VContext ctx;  // non-null

    private VRuntime(VContext ctx) {
        this.ctx = ctx;
    }
    @Override
    public VContext setNewClient(VContext ctx, Options opts) throws VException {
        return nativeSetNewClient(ctx, opts);
    }
    @Override
    public io.v.core.veyron2.ipc.Client getClient(VContext ctx) {
        try {
            return nativeGetClient(ctx);
        } catch (VException e) {
            throw new RuntimeException("Couldn't get client: " + e.getMessage());
        }
    }
    @Override
    public io.v.core.veyron2.ipc.Server newServer(VContext ctx, Options opts) throws VException {
        // Get a Java ListenSpec that is attached to this context (if any).
        final ListenSpec spec = (ListenSpec) ctx.value(this);
        return nativeNewServer(ctx, spec);
    }
    @Override
    public VContext setPrincipal(VContext ctx, Principal principal) throws VException {
        return nativeSetPrincipal(ctx, principal);
    }
    @Override
    public Principal getPrincipal(VContext ctx) {
        try {
            return nativeGetPrincipal(ctx);
        } catch (VException e) {
            throw new RuntimeException("Couldn't get principal: " + e.getMessage());
        }
    }
    @Override
    public VContext setNewNamespace(VContext ctx, String... roots) throws VException {
        return nativeSetNewNamespace(ctx, roots);
    }
    @Override
    public Namespace getNamespace(VContext ctx) {
        try {
            return nativeGetNamespace(ctx);
        } catch (VException e) {
            throw new RuntimeException("Couldn't get namespace: " + e.getMessage());
        }
    }
    @Override
    public VContext setListenSpec(VContext ctx, ListenSpec spec) {
        return ctx.withValue(this, spec);
    }

    @Override
    public ListenSpec getListenSpec(VContext ctx) {
        // Get the ListenSpec attached to this context.
        final ListenSpec spec = (ListenSpec) ctx.value(this);
        if (spec == null) {
            throw new RuntimeException("Couldn't get attached listen spec");
        }
        return spec;
    }
    @Override
    public VContext getContext() {
        return this.ctx;
    }
}