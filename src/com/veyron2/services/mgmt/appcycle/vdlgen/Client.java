// This file was auto-generated by the veyron vdl tool.
// Source(s):  appcycle.vdl
package com.veyron2.services.mgmt.appcycle.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.OptionDefs;
import com.veyron2.Options;
import com.veyron2.RuntimeFactory;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.ClientStream;

public class Client { 
	/* Bind methods for interfaces in file: appcycle.vdl. */
	public static AppCycle bindAppCycle(String name) throws VeyronException {
		return bindAppCycle(name, null);
	}
	public static AppCycle bindAppCycle(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		return new AppCycleStub(client, name);
	}

	/* Client stubs for interfaces in file: appcycle.vdl. */
	private static class AppCycleStub implements AppCycle {
		private static final String vdlIfacePathOpt = "com.veyron2.services.mgmt.appcycle.vdlgen.AppCycle";
		private final com.veyron2.ipc.Client client;
		private final String name;

		AppCycleStub(com.veyron2.ipc.Client client, String name) {
			this.client = client;
			this.name = name;
		}
		// Methods from interface AppCycle.
		@Override
		public ClientStream<Void,Task,Void> stop(Context context) throws VeyronException {
			return stop(context, null);
		}
		@Override
		public ClientStream<Void,Task,Void> stop(Context context, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{  };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, AppCycleStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Stop", inArgs, veyronOpts);

			return new ClientStream<Void, Task, Void>() {
				@Override
				public void send(Void item) throws VeyronException {
					call.send(item);
				}
				@Override
				public Task recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<Task>() {};
					final Object result = call.recv(type);
					try {
						return (Task)result;
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
		public void forceStop(Context context) throws VeyronException {
			forceStop(context, null);
		}
		@Override
		public void forceStop(Context context, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{  };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, AppCycleStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "ForceStop", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{  };
			call.finish(resultTypes);

		}
	}
}