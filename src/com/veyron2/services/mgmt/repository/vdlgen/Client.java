// This file was auto-generated by the veyron vdl tool.
// Source(s):  repository.vdl
package com.veyron2.services.mgmt.repository.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.OptionDefs;
import com.veyron2.Options;
import com.veyron2.RuntimeFactory;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;
import com.veyron2.services.mgmt.application.vdlgen.Envelope;
import com.veyron2.vdl.ClientStream;
import java.util.ArrayList;

public class Client { 
	/* Bind methods for interfaces in file: repository.vdl. */
	public static Application bindApplication(String name) throws VeyronException {
		return bindApplication(name, null);
	}
	public static Application bindApplication(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		return new ApplicationStub(client, name);
	}
	public static Content bindContent(String name) throws VeyronException {
		return bindContent(name, null);
	}
	public static Content bindContent(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		return new ContentStub(client, name);
	}
	public static Profile bindProfile(String name) throws VeyronException {
		return bindProfile(name, null);
	}
	public static Profile bindProfile(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		return new ProfileStub(client, name);
	}

	/* Client stubs for interfaces in file: repository.vdl. */
	private static class ApplicationStub implements Application {
		private static final String vdlIfacePathOpt = "com.veyron2.services.mgmt.repository.vdlgen.Application";
		private final com.veyron2.ipc.Client client;
		private final String name;

		ApplicationStub(com.veyron2.ipc.Client client, String name) {
			this.client = client;
			this.name = name;
		}
		// Methods from interface Application.
		@Override
		public Envelope match(Context context, ArrayList<String> Profiles) throws VeyronException {
			return match(context, Profiles, null);
		}
		@Override
		public Envelope match(Context context, ArrayList<String> Profiles, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{ Profiles };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ApplicationStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Match", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<Envelope>() {} };
			return (Envelope)call.finish(resultTypes)[0];

		}
	}
	private static class ContentStub implements Content {
		private static final String vdlIfacePathOpt = "com.veyron2.services.mgmt.repository.vdlgen.Content";
		private final com.veyron2.ipc.Client client;
		private final String name;

		ContentStub(com.veyron2.ipc.Client client, String name) {
			this.client = client;
			this.name = name;
		}
		// Methods from interface Content.
		@Override
		public void delete(Context context) throws VeyronException {
			delete(context, null);
		}
		@Override
		public void delete(Context context, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{  };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ContentStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Delete", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{  };
			call.finish(resultTypes);

		}
		@Override
		public ClientStream<Void,ArrayList<Byte>,Void> download(Context context) throws VeyronException {
			return download(context, null);
		}
		@Override
		public ClientStream<Void,ArrayList<Byte>,Void> download(Context context, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{  };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ContentStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Download", inArgs, veyronOpts);

			return new ClientStream<Void, ArrayList<Byte>, Void>() {
				@Override
				public void send(Void item) throws VeyronException {
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
		public ClientStream<ArrayList<Byte>,Void,String> upload(Context context) throws VeyronException {
			return upload(context, null);
		}
		@Override
		public ClientStream<ArrayList<Byte>,Void,String> upload(Context context, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{  };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ContentStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Upload", inArgs, veyronOpts);

			return new ClientStream<ArrayList<Byte>, Void, String>() {
				@Override
				public void send(ArrayList<Byte> item) throws VeyronException {
					call.send(item);
				}
				@Override
				public Void recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<Void>() {};
					final Object result = call.recv(type);
					try {
						return (Void)result;
					} catch (ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
				@Override
				public String finish() throws VeyronException {
					// Prepare output argument and finish the call.
					final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<String>() {} };
					return (String)call.finish(resultTypes)[0];

				}
			};
		}
	}
	private static class ProfileStub implements Profile {
		private static final String vdlIfacePathOpt = "com.veyron2.services.mgmt.repository.vdlgen.Profile";
		private final com.veyron2.ipc.Client client;
		private final String name;

		ProfileStub(com.veyron2.ipc.Client client, String name) {
			this.client = client;
			this.name = name;
		}
		// Methods from interface Profile.
		@Override
		public String label(Context context) throws VeyronException {
			return label(context, null);
		}
		@Override
		public String label(Context context, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{  };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ProfileStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Label", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<String>() {} };
			return (String)call.finish(resultTypes)[0];

		}
		@Override
		public String description(Context context) throws VeyronException {
			return description(context, null);
		}
		@Override
		public String description(Context context, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final Object[] inArgs = new Object[]{  };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ProfileStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Description", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<String>() {} };
			return (String)call.finish(resultTypes)[0];

		}
	}
}
