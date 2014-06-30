// This file was auto-generated by the veyron vdl tool.
// Source(s):  discharger.vdl
package com.veyron.services.security.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.OptionDefs;
import com.veyron2.Options;
import com.veyron2.RuntimeFactory;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;

public class Client { 
	/* Bind methods for interfaces in file: discharger.vdl. */
	public static Discharger bindDischarger(String name) throws VeyronException {
		return bindDischarger(name, null);
	}
	public static Discharger bindDischarger(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		return new DischargerStub(client, name);
	}

	/* Client stubs for interfaces in file: discharger.vdl. */
	private static class DischargerStub implements Discharger {
		private static final String vdlIfacePathOpt = "com.veyron.services.security.vdlgen.Discharger";
		private final com.veyron2.ipc.Client client;
		private final String name;

		DischargerStub(com.veyron2.ipc.Client client, String name) {
			this.client = client;
			this.name = name;
		}
		// Methods from interface Discharger.
		@Override
		public Object discharge(Context context, Object Caveat) throws VeyronException {
			return discharge(context, Caveat, null);
		}
		@Override
		public Object discharge(Context context, Object Caveat, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{ Caveat };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, DischargerStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Discharge", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<Object>() {} };
			return (Object)call.finish(resultTypes)[0];

		}
	}
}