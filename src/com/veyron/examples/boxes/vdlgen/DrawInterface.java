// This file was auto-generated by the veyron vdl tool.
// Source: boxes.vdl
package com.veyron.examples.boxes.vdlgen;

import com.veyron2.Options;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.ClientStream;

/**
 * DrawInterface enables adding a box on another peer
**/
public interface DrawInterface { 
	// Draw is used to send/receive a stream of boxes to another peer
	public ClientStream<Box,Box,Void> draw(Context context) throws VeyronException;
	public ClientStream<Box,Box,Void> draw(Context context, Options veyronOpts) throws VeyronException;
	// SyncBoxes is used to setup a sync service over store to send/receive
// boxes to another peer
	public void syncBoxes(Context context) throws VeyronException;
	public void syncBoxes(Context context, Options veyronOpts) throws VeyronException;
}