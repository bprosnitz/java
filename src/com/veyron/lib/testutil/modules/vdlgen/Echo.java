// This file was auto-generated by the veyron vdl tool.
// Source: servers.vdl
package com.veyron.lib.testutil.modules.vdlgen;

import com.veyron2.Options;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;

public interface Echo { 
	// Echo simply returns its argument as its result
	public String echo(Context context, String msg) throws VeyronException;
	public String echo(Context context, String msg, Options veyronOpts) throws VeyronException;
}
