
// This file was auto-generated by the veyron vdl tool.
// Source: protocol.vdl
package com.veyron.runtimes.google.ipc.stream.proxy.vdlgen;

import com.veyron2.ipc.VeyronException;

/**
 * Response is sent by the proxy to the server after processing Request.
**/
public class Response { 
	// Error is a description of why the proxy refused to proxy the server.
// A nil error indicates that the proxy will route traffic to the server.
public VeyronException error;
	// Endpoint is the string representation of an endpoint that can be
// used to communicate with the server through the proxy.
public String endpoint;
}