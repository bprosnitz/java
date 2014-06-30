// This file was auto-generated by the veyron vdl tool.
// Source: service.vdl
package com.veyron.services.store.raw.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;
import com.veyron2.services.watch.vdlgen.ChangeBatch;
import com.veyron2.vdl.Stream;

/**
 * Store defines a raw interface for the Veyron store. Mutations can be received
 * via the Watcher interface, and committed via PutMutation.
**/
public interface StoreService { 
	// Watch returns a stream of all changes.
	public void watch(ServerContext context, Request req, Stream<ChangeBatch,Void> stream) throws VeyronException;
	// PutMutations atomically commits a stream of Mutations when the stream is
// closed. Mutations are not committed if the request is cancelled before
// the stream has been closed.
	public void putMutations(ServerContext context, Stream<Void,Mutation> stream) throws VeyronException;
}
