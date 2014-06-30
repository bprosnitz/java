// This file was auto-generated by the veyron vdl tool.
// Source(s):  tunnel.vdl
package com.veyron.examples.tunnel.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.ipc.ServerCall;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.Stream;
import java.util.ArrayList;

public class Server { 
	/* Server stub creation methods for interfaces in file: tunnel.vdl. */
	public static Object newTunnel(TunnelService service) { 
		return new TunnelStub(service);
	}
	
	/* Server stubs for interfaces in file: tunnel.vdl. */
	public static class TunnelStub {
		private final TunnelService service;

		TunnelStub(TunnelService service) {
			this.service = service;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public Object[] getMethodTags(ServerCall call, String method) { 
			if (method == "Forward") {
				return new Object[]{ 4 };
			}
			if (method == "Shell") {
				return new Object[]{ 4 };
			}
			return null;
		}
		// Methods from interface Tunnel.
		public void forward(ServerCall call, String network, String address) throws VeyronException { 
			final ServerCall serverCall = call;
			final Stream<ArrayList<Byte>,ArrayList<Byte>> stream = new Stream<ArrayList<Byte>,ArrayList<Byte>>() {
				@Override
				public void send(ArrayList<Byte> item) throws VeyronException {
					serverCall.send(item);
				}
				@Override
				public ArrayList<Byte> recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<ArrayList<Byte>>() {};
					final Object result = serverCall.recv(type);
					try {
						return (ArrayList<Byte>)result;
					} catch (java.lang.ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
			};
			this.service.forward(call, network, address, stream);
		}
		public int shell(ServerCall call, String command, ShellOpts shellOpts) throws VeyronException { 
			final ServerCall serverCall = call;
			final Stream<ServerShellPacket,ClientShellPacket> stream = new Stream<ServerShellPacket,ClientShellPacket>() {
				@Override
				public void send(ServerShellPacket item) throws VeyronException {
					serverCall.send(item);
				}
				@Override
				public ClientShellPacket recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<ClientShellPacket>() {};
					final Object result = serverCall.recv(type);
					try {
						return (ClientShellPacket)result;
					} catch (java.lang.ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
			};
			return this.service.shell(call, command, shellOpts, stream);
		}
	}
}
