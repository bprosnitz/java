// This file was auto-generated by the veyron vdl tool.
// Source: service.vdl
package com.veyron.services.store.raw.vdlgen;

import com.veyron2.Options;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;
import com.veyron2.services.watch.vdlgen.ChangeBatch;
import com.veyron2.vdl.ClientStream;

/**
 * Store defines a raw interface for the Veyron store. Mutations can be received
 * via the Watcher interface, and committed via PutMutation.
**/
public interface Store { 
	// Watch returns a stream of all changes.
	public ClientStream<Void,ChangeBatch,Void> watch(Context context, Request req) throws VeyronException;
	public ClientStream<Void,ChangeBatch,Void> watch(Context context, Request req, Options veyronOpts) throws VeyronException;
	// PutMutations atomically commits a stream of Mutations when the stream is
// closed. Mutations are not committed if the request is cancelled before
// the stream has been closed.
	public ClientStream<Mutation,Void,Void> putMutations(Context context) throws VeyronException;
	public ClientStream<Mutation,Void,Void> putMutations(Context context, Options veyronOpts) throws VeyronException;
}
