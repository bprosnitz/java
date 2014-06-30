// This file was auto-generated by the veyron vdl tool.
// Source(s):  tunnel.vdl
package com.veyron.examples.tunnel.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.OptionDefs;
import com.veyron2.Options;
import com.veyron2.RuntimeFactory;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.ClientStream;
import java.util.ArrayList;

public class Client { 
	/* Bind methods for interfaces in file: tunnel.vdl. */
	public static Tunnel bindTunnel(String name) throws VeyronException {
		return bindTunnel(name, null);
	}
	public static Tunnel bindTunnel(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		return new TunnelStub(client, name);
	}

	/* Client stubs for interfaces in file: tunnel.vdl. */
	private static class TunnelStub implements Tunnel {
		private static final String vdlIfacePathOpt = "com.veyron.examples.tunnel.vdlgen.Tunnel";
		private final com.veyron2.ipc.Client client;
		private final String name;

		TunnelStub(com.veyron2.ipc.Client client, String name) {
			this.client = client;
			this.name = name;
		}
		// Methods from interface Tunnel.
		@Override
		public ClientStream<ArrayList<Byte>,ArrayList<Byte>,Void> forward(Context context, String network, String address) throws VeyronException {
			return forward(context, network, address, null);
		}
		@Override
		public ClientStream<ArrayList<Byte>,ArrayList<Byte>,Void> forward(Context context, String network, String address, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{ network, address };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, TunnelStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Forward", inArgs, veyronOpts);

			return new ClientStream<ArrayList<Byte>, ArrayList<Byte>, Void>() {
				@Override
				public void send(ArrayList<Byte> item) throws VeyronException {
					call.send(item);
				}
				@Override
				public ArrayList<Byte> recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<ArrayList<Byte>>() {};
					final Object result = call.recv(type);
					try {
						return (ArrayList<Byte>)result;
					} catch (ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
				@Override
				public Void finish() throws VeyronException {
					// Prepare output argument and finish the call.
					final TypeToken<?>[] resultTypes = new TypeToken<?>[]{  };
					call.finish(resultTypes);
					return null;

				}
			};
		}
		@Override
		public ClientStream<ClientShellPacket,ServerShellPacket,Integer> shell(Context context, String command, ShellOpts shellOpts) throws VeyronException {
			return shell(context, command, shellOpts, null);
		}
		@Override
		public ClientStream<ClientShellPacket,ServerShellPacket,Integer> shell(Context context, String command, ShellOpts shellOpts, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{ command, shellOpts };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, TunnelStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Shell", inArgs, veyronOpts);

			return new ClientStream<ClientShellPacket, ServerShellPacket, Integer>() {
				@Override
				public void send(ClientShellPacket item) throws VeyronException {
					call.send(item);
				}
				@Override
				public ServerShellPacket recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<ServerShellPacket>() {};
					final Object result = call.recv(type);
					try {
						return (ServerShellPacket)result;
					} catch (ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
				@Override
				public Integer finish() throws VeyronException {
					// Prepare output argument and finish the call.
					final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<Integer>() {} };
					return (Integer)call.finish(resultTypes)[0];

				}
			};
		}
	}
}
