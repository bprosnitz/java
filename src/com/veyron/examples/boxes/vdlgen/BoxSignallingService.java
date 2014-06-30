// This file was auto-generated by the veyron vdl tool.
// Source: boxes.vdl
package com.veyron.examples.boxes.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;

/**
 * BoxSignalling allows peers to rendezvous with each other
**/
public interface BoxSignallingService { 
	// Add endpoint information to the signalling server.
	public void add(ServerContext context, String endpoint) throws VeyronException;
	// Get endpoint information about a peer.
	public String get(ServerContext context) throws VeyronException;
}