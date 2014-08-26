// This file was auto-generated by the veyron vdl tool.
// Source: service.vdl
package com.veyron.services.store.raw;

/**
 * Store defines a raw interface for the Veyron store. Mutations can be received
 * via the Watcher interface, and committed via PutMutation.
 */

public interface Store  {

    
    

    
    // Watch returns a stream of all changes.

    public com.veyron2.vdl.ClientStream<java.lang.Void,com.veyron2.services.watch.types.Change, java.lang.Void> watch(final com.veyron2.ipc.Context context, final com.veyron.services.store.raw.Request Req) throws com.veyron2.ipc.VeyronException;
    public com.veyron2.vdl.ClientStream<java.lang.Void,com.veyron2.services.watch.types.Change, java.lang.Void> watch(final com.veyron2.ipc.Context context, final com.veyron.services.store.raw.Request Req, final com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException;

    
    

    
    // PutMutations atomically commits a stream of Mutations when the stream is
// closed. Mutations are not committed if the request is cancelled before
// the stream has been closed.

    public com.veyron2.vdl.ClientStream<com.veyron.services.store.raw.Mutation,java.lang.Void, java.lang.Void> putMutations(final com.veyron2.ipc.Context context) throws com.veyron2.ipc.VeyronException;
    public com.veyron2.vdl.ClientStream<com.veyron.services.store.raw.Mutation,java.lang.Void, java.lang.Void> putMutations(final com.veyron2.ipc.Context context, final com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException;

}
