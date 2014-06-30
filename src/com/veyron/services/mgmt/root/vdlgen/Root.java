// This file was auto-generated by the veyron vdl tool.
// Source: root.vdl
package com.veyron.services.mgmt.root.vdlgen;

import com.veyron2.Options;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;

/**
 * Root is an interface to be implemented by a process with root level
 * privileges.
**/
public interface Root { 
	// Reset waits for the given deadline (in milliseconds) and then
// restars the host node machine.
	public void reset(Context context, long deadline) throws VeyronException;
	public void reset(Context context, long deadline, Options veyronOpts) throws VeyronException;
}