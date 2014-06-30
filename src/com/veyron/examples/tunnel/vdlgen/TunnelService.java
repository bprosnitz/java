// This file was auto-generated by the veyron vdl tool.
// Source: tunnel.vdl
package com.veyron.examples.tunnel.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.Stream;
import java.util.ArrayList;

public interface TunnelService { 
	// The Forward method is used for network forwarding. All the data sent over
// the byte stream is forwarded to the requested network address and all the
// data received from that network connection is sent back in the reply
// stream.
	public void forward(ServerContext context, String network, String address, Stream<ArrayList<Byte>,ArrayList<Byte>> stream) throws VeyronException;
	// The Shell method is used to either run shell commands remotely, or to open
// an interactive shell. The data received over the byte stream is sent to the
// shell's stdin, and the data received from the shell's stdout and stderr is
// sent back in the reply stream. It returns the exit status of the shell
// command.
	public int shell(ServerContext context, String command, ShellOpts shellOpts, Stream<ServerShellPacket,ClientShellPacket> stream) throws VeyronException;
}