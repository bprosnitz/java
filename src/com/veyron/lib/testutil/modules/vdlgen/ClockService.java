// This file was auto-generated by the veyron vdl tool.
// Source: servers.vdl
package com.veyron.lib.testutil.modules.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;

public interface ClockService { 
	// Time returns a string of the form "(current time) msg"
	public String time(ServerContext context, String msg) throws VeyronException;
}