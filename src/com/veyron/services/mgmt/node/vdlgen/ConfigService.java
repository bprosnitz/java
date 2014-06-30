// This file was auto-generated by the veyron vdl tool.
// Source: node.vdl
package com.veyron.services.mgmt.node.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;

/**
 * Config is an RPC API to the config service.
**/
public interface ConfigService { 
	// Set sets the value for key.
	public void set(ServerContext context, String key, String value) throws VeyronException;
}
