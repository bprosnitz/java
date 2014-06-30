// This file was auto-generated by the veyron vdl tool.
// Source(s):  service.vdl
package com.veyron2.services.store.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.OptionDefs;
import com.veyron2.Options;
import com.veyron2.RuntimeFactory;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;
import com.veyron2.query.vdlgen.Query;
import com.veyron2.services.mounttable.vdlgen.Globable;
import com.veyron2.services.mounttable.vdlgen.MountEntry;
import com.veyron2.services.watch.vdlgen.ChangeBatch;
import com.veyron2.services.watch.vdlgen.GlobRequest;
import com.veyron2.services.watch.vdlgen.GlobWatcher;
import com.veyron2.services.watch.vdlgen.QueryRequest;
import com.veyron2.services.watch.vdlgen.QueryWatcher;
import com.veyron2.vdl.ClientStream;
import java.util.ArrayList;

public class Client { 
	/* Bind methods for interfaces in file: service.vdl. */
	public static Object bindObject(String name) throws VeyronException {
		return bindObject(name, null);
	}
	public static Object bindObject(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		final Globable globable = com.veyron2.services.mounttable.vdlgen.Client.bindGlobable(name, veyronOpts);
		final GlobWatcher globWatcher = com.veyron2.services.watch.vdlgen.Client.bindGlobWatcher(name, veyronOpts);
		final QueryWatcher queryWatcher = com.veyron2.services.watch.vdlgen.Client.bindQueryWatcher(name, veyronOpts);
		return new ObjectStub(client, name, globable, globWatcher, queryWatcher);
	}
	public static Store bindStore(String name) throws VeyronException {
		return bindStore(name, null);
	}
	public static Store bindStore(String name, Options veyronOpts) throws VeyronException {
		com.veyron2.ipc.Client client = null;
		if (veyronOpts != null && veyronOpts.get(OptionDefs.CLIENT) != null) {
			client = veyronOpts.get(OptionDefs.CLIENT, com.veyron2.ipc.Client.class);
		} else if (veyronOpts != null && veyronOpts.get(OptionDefs.RUNTIME) != null) {
			client = veyronOpts.get(OptionDefs.RUNTIME, com.veyron2.Runtime.class).getClient();
		} else {
			client = RuntimeFactory.getRuntime().getClient();
		}
		return new StoreStub(client, name);
	}

	/* Client stubs for interfaces in file: service.vdl. */
	private static class ObjectStub implements Object {
		private static final String vdlIfacePathOpt = "com.veyron2.services.store.vdlgen.Object";
		private final com.veyron2.ipc.Client client;
		private final String name;
		private final Globable globable;
		private final GlobWatcher globWatcher;
		private final QueryWatcher queryWatcher;

		ObjectStub(com.veyron2.ipc.Client client, String name, Globable globable, GlobWatcher globWatcher, QueryWatcher queryWatcher) {
			this.client = client;
			this.name = name;
			this.globable = globable;
			this.globWatcher = globWatcher;
			this.queryWatcher = queryWatcher;
		}
		// Methods from interface Object.
		@Override
		public boolean exists(Context context, long TID) throws VeyronException {
			return exists(context, TID, null);
		}
		@Override
		public boolean exists(Context context, long TID, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID) };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Exists", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<Boolean>() {} };
			return (boolean)call.finish(resultTypes)[0];

		}
		@Override
		public Entry get(Context context, long TID) throws VeyronException {
			return get(context, TID, null);
		}
		@Override
		public Entry get(Context context, long TID, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID) };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Get", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<Entry>() {} };
			return (Entry)call.finish(resultTypes)[0];

		}
		@Override
		public Stat put(Context context, long TID, java.lang.Object V) throws VeyronException {
			return put(context, TID, V, null);
		}
		@Override
		public Stat put(Context context, long TID, java.lang.Object V, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID), V };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Put", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<Stat>() {} };
			return (Stat)call.finish(resultTypes)[0];

		}
		@Override
		public void remove(Context context, long TID) throws VeyronException {
			remove(context, TID, null);
		}
		@Override
		public void remove(Context context, long TID, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID) };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Remove", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{  };
			call.finish(resultTypes);

		}
		@Override
		public void setAttr(Context context, long TID, ArrayList<java.lang.Object> Attrs) throws VeyronException {
			setAttr(context, TID, Attrs, null);
		}
		@Override
		public void setAttr(Context context, long TID, ArrayList<java.lang.Object> Attrs, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID), Attrs };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "SetAttr", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{  };
			call.finish(resultTypes);

		}
		@Override
		public Stat stat(Context context, long TID) throws VeyronException {
			return stat(context, TID, null);
		}
		@Override
		public Stat stat(Context context, long TID, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID) };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Stat", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{ new TypeToken<Stat>() {} };
			return (Stat)call.finish(resultTypes)[0];

		}
		@Override
		public ClientStream<Void,QueryResult,Void> query(Context context, long TID, Query Q) throws VeyronException {
			return query(context, TID, Q, null);
		}
		@Override
		public ClientStream<Void,QueryResult,Void> query(Context context, long TID, Query Q, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID), Q };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Query", inArgs, veyronOpts);

			return new ClientStream<Void, QueryResult, Void>() {
				@Override
				public void send(Void item) throws VeyronException {
					call.send(item);
				}
				@Override
				public QueryResult recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<QueryResult>() {};
					final java.lang.Object result = call.recv(type);
					try {
						return (QueryResult)result;
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
		public ClientStream<Void,String,Void> globT(Context context, long TID, String pattern) throws VeyronException {
			return globT(context, TID, pattern, null);
		}
		@Override
		public ClientStream<Void,String,Void> globT(Context context, long TID, String pattern, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID), pattern };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "GlobT", inArgs, veyronOpts);

			return new ClientStream<Void, String, Void>() {
				@Override
				public void send(Void item) throws VeyronException {
					call.send(item);
				}
				@Override
				public String recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<String>() {};
					final java.lang.Object result = call.recv(type);
					try {
						return (String)result;
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
		// Methods from sub-interface Globable.
		@Override
		public ClientStream<Void,MountEntry,Void> glob(Context context, String pattern) throws VeyronException {
			return glob(context, pattern, null);
		}
		@Override
		public ClientStream<Void,MountEntry,Void> glob(Context context, String pattern, Options veyronOpts) throws VeyronException {
			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
		    // Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}
			return this.globable.glob(context, pattern, veyronOpts);
		}
		// Methods from sub-interface GlobWatcher.
		@Override
		public ClientStream<Void,ChangeBatch,Void> watchGlob(Context context, GlobRequest Req) throws VeyronException {
			return watchGlob(context, Req, null);
		}
		@Override
		public ClientStream<Void,ChangeBatch,Void> watchGlob(Context context, GlobRequest Req, Options veyronOpts) throws VeyronException {
			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
		    // Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}
			return this.globWatcher.watchGlob(context, Req, veyronOpts);
		}
		// Methods from sub-interface QueryWatcher.
		@Override
		public ClientStream<Void,ChangeBatch,Void> watchQuery(Context context, QueryRequest Req) throws VeyronException {
			return watchQuery(context, Req, null);
		}
		@Override
		public ClientStream<Void,ChangeBatch,Void> watchQuery(Context context, QueryRequest Req, Options veyronOpts) throws VeyronException {
			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
		    // Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, ObjectStub.vdlIfacePathOpt);
			}
			return this.queryWatcher.watchQuery(context, Req, veyronOpts);
		}
	}
	private static class StoreStub implements Store {
		private static final String vdlIfacePathOpt = "com.veyron2.services.store.vdlgen.Store";
		private final com.veyron2.ipc.Client client;
		private final String name;

		StoreStub(com.veyron2.ipc.Client client, String name) {
			this.client = client;
			this.name = name;
		}
		// Methods from interface Store.
		@Override
		public void createTransaction(Context context, long TID, ArrayList<java.lang.Object> Options) throws VeyronException {
			createTransaction(context, TID, Options, null);
		}
		@Override
		public void createTransaction(Context context, long TID, ArrayList<java.lang.Object> Options, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID), Options };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, StoreStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "CreateTransaction", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{  };
			call.finish(resultTypes);

		}
		@Override
		public void commit(Context context, long TID) throws VeyronException {
			commit(context, TID, null);
		}
		@Override
		public void commit(Context context, long TID, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID) };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, StoreStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Commit", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{  };
			call.finish(resultTypes);

		}
		@Override
		public void abort(Context context, long TID) throws VeyronException {
			abort(context, TID, null);
		}
		@Override
		public void abort(Context context, long TID, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{ new Long(TID) };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, StoreStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "Abort", inArgs, veyronOpts);

			// Prepare output argument and finish the call.
			final TypeToken<?>[] resultTypes = new TypeToken<?>[]{  };
			call.finish(resultTypes);

		}
		@Override
		public ClientStream<Void,Conflict,Void> readConflicts(Context context) throws VeyronException {
			return readConflicts(context, null);
		}
		@Override
		public ClientStream<Void,Conflict,Void> readConflicts(Context context, Options veyronOpts) throws VeyronException {
			// Prepare input arguments.
			final java.lang.Object[] inArgs = new java.lang.Object[]{  };

			// Add VDL path option.
			// NOTE(spetrovic): this option is temporary and will be removed soon after we switch
			// Java to encoding/decoding from vom.Value objects.
			if (veyronOpts == null) veyronOpts = new Options();
			if (!veyronOpts.has(OptionDefs.VDL_INTERFACE_PATH)) {
				veyronOpts.set(OptionDefs.VDL_INTERFACE_PATH, StoreStub.vdlIfacePathOpt);
			}

			// Start the call.
			final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.name, "ReadConflicts", inArgs, veyronOpts);

			return new ClientStream<Void, Conflict, Void>() {
				@Override
				public void send(Void item) throws VeyronException {
					call.send(item);
				}
				@Override
				public Conflict recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<Conflict>() {};
					final java.lang.Object result = call.recv(type);
					try {
						return (Conflict)result;
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
	}
}