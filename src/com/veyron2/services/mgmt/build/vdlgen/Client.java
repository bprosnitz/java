// This file was auto-generated by the veyron vdl tool.
// Source(s):  build.vdl
package com.veyron2.services.mgmt.build.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.OptionDefs;
import com.veyron2.Options;
import com.veyron2.RuntimeFactory;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;

public class Client { 
	/* Bind methods for interfaces in file: build.vdl. */
	public static Build bindBuild(String name) throws VeyronException {
		return bindBuild(name, null);
	}
	public static Build bindBuild(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		return new BuildStub(client, name);
	}

	/* Client stubs for interfaces in file: build.vdl. */
	private static class BuildStub implements Build {
		private static final String vdlIfacePathOpt = "com.veyron2.services.mgmt.build.vdlgen.Build";
		private final com.veyron2.ipc.Client client;
		private final String name;

		BuildStub(com.veyron2.ipc.Client client, String name) {
			this.client = client;
			this.name = name;
		}
		// Methods from interface Build.
		@Override
		public BinaryDescription describe(Context context, String Name) throws VeyronException {
			return describe(context, Name, null);
		}
		@Override
		public BinaryDescription describe(Context context, String Name, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{ Name };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, BuildStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Describe", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<BinaryDescription>() {} };
			return (BinaryDescription)call.finish(resultTypes)[0];

		}
	}
}
