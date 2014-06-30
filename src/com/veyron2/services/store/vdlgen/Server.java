// This file was auto-generated by the veyron vdl tool.
// Source(s):  service.vdl
package com.veyron2.services.store.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.ipc.ServerCall;
import com.veyron2.ipc.VeyronException;
import com.veyron2.query.vdlgen.Query;
import com.veyron2.services.mounttable.vdlgen.GlobableService;
import com.veyron2.services.mounttable.vdlgen.MountEntry;
import com.veyron2.services.watch.vdlgen.ChangeBatch;
import com.veyron2.services.watch.vdlgen.GlobRequest;
import com.veyron2.services.watch.vdlgen.GlobWatcherService;
import com.veyron2.services.watch.vdlgen.QueryRequest;
import com.veyron2.services.watch.vdlgen.QueryWatcherService;
import com.veyron2.vdl.Stream;
import java.util.ArrayList;

public class Server { 
	/* Server stub creation methods for interfaces in file: service.vdl. */
	public static java.lang.Object newObject(ObjectService service) { 
		final com.veyron2.services.mounttable.vdlgen.Server.GlobableStub globable = (com.veyron2.services.mounttable.vdlgen.Server.GlobableStub)com.veyron2.services.mounttable.vdlgen.Server.newGlobable(service);
		final com.veyron2.services.watch.vdlgen.Server.GlobWatcherStub globWatcher = (com.veyron2.services.watch.vdlgen.Server.GlobWatcherStub)com.veyron2.services.watch.vdlgen.Server.newGlobWatcher(service);
		final com.veyron2.services.watch.vdlgen.Server.QueryWatcherStub queryWatcher = (com.veyron2.services.watch.vdlgen.Server.QueryWatcherStub)com.veyron2.services.watch.vdlgen.Server.newQueryWatcher(service);
		return new ObjectStub(service, globable, globWatcher, queryWatcher);
	}
	public static java.lang.Object newStore(StoreService service) { 
		return new StoreStub(service);
	}
	
	/* Server stubs for interfaces in file: service.vdl. */
	public static class ObjectStub {
		private final ObjectService service;
		private final com.veyron2.services.mounttable.vdlgen.Server.GlobableStub globable;
		private final com.veyron2.services.watch.vdlgen.Server.GlobWatcherStub globWatcher;
		private final com.veyron2.services.watch.vdlgen.Server.QueryWatcherStub queryWatcher;

		ObjectStub(ObjectService service, com.veyron2.services.mounttable.vdlgen.Server.GlobableStub globable, com.veyron2.services.watch.vdlgen.Server.GlobWatcherStub globWatcher, com.veyron2.services.watch.vdlgen.Server.QueryWatcherStub queryWatcher) {
			this.service = service;
			this.globable = globable;
			this.globWatcher = globWatcher;
			this.queryWatcher = queryWatcher;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public java.lang.Object[] getMethodTags(ServerCall call, String method) { 
			{
				final java.lang.Object[] tags = this.globable.getMethodTags(call, method);
				if (tags != null) return tags;
			}
			{
				final java.lang.Object[] tags = this.globWatcher.getMethodTags(call, method);
				if (tags != null) return tags;
			}
			{
				final java.lang.Object[] tags = this.queryWatcher.getMethodTags(call, method);
				if (tags != null) return tags;
			}
			if (method == "Exists") {
				return new java.lang.Object[]{  };
			}
			if (method == "Get") {
				return new java.lang.Object[]{  };
			}
			if (method == "Put") {
				return new java.lang.Object[]{  };
			}
			if (method == "Remove") {
				return new java.lang.Object[]{  };
			}
			if (method == "SetAttr") {
				return new java.lang.Object[]{  };
			}
			if (method == "Stat") {
				return new java.lang.Object[]{  };
			}
			if (method == "Query") {
				return new java.lang.Object[]{  };
			}
			if (method == "GlobT") {
				return new java.lang.Object[]{  };
			}
			return null;
		}
		// Methods from interface Object.
		public boolean exists(ServerCall call, long TID) throws VeyronException { 
			return this.service.exists(call, TID);
		}
		public Entry get(ServerCall call, long TID) throws VeyronException { 
			return this.service.get(call, TID);
		}
		public Stat put(ServerCall call, long TID, java.lang.Object V) throws VeyronException { 
			return this.service.put(call, TID, V);
		}
		public void remove(ServerCall call, long TID) throws VeyronException { 
			this.service.remove(call, TID);
		}
		public void setAttr(ServerCall call, long TID, ArrayList<java.lang.Object> Attrs) throws VeyronException { 
			this.service.setAttr(call, TID, Attrs);
		}
		public Stat stat(ServerCall call, long TID) throws VeyronException { 
			return this.service.stat(call, TID);
		}
		public void query(ServerCall call, long TID, Query Q) throws VeyronException { 
			final ServerCall serverCall = call;
			final Stream<QueryResult,Void> stream = new Stream<QueryResult,Void>() {
				@Override
				public void send(QueryResult item) throws VeyronException {
					serverCall.send(item);
				}
				@Override
				public Void recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<Void>() {};
					final java.lang.Object result = serverCall.recv(type);
					try {
						return (Void)result;
					} catch (java.lang.ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
			};
			this.service.query(call, TID, Q, stream);
		}
		public void globT(ServerCall call, long TID, String pattern) throws VeyronException { 
			final ServerCall serverCall = call;
			final Stream<String,Void> stream = new Stream<String,Void>() {
				@Override
				public void send(String item) throws VeyronException {
					serverCall.send(item);
				}
				@Override
				public Void recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<Void>() {};
					final java.lang.Object result = serverCall.recv(type);
					try {
						return (Void)result;
					} catch (java.lang.ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
			};
			this.service.globT(call, TID, pattern, stream);
		}
		// Methods from sub-interface Globable.
		public void glob(ServerCall call, String pattern) throws VeyronException {
			this.globable.glob(call, pattern);
		}
		// Methods from sub-interface GlobWatcher.
		public void watchGlob(ServerCall call, GlobRequest Req) throws VeyronException {
			this.globWatcher.watchGlob(call, Req);
		}
		// Methods from sub-interface QueryWatcher.
		public void watchQuery(ServerCall call, QueryRequest Req) throws VeyronException {
			this.queryWatcher.watchQuery(call, Req);
		}
	}
	public static class StoreStub {
		private final StoreService service;

		StoreStub(StoreService service) {
			this.service = service;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public java.lang.Object[] getMethodTags(ServerCall call, String method) { 
			if (method == "CreateTransaction") {
				return new java.lang.Object[]{  };
			}
			if (method == "Commit") {
				return new java.lang.Object[]{  };
			}
			if (method == "Abort") {
				return new java.lang.Object[]{  };
			}
			if (method == "ReadConflicts") {
				return new java.lang.Object[]{  };
			}
			return null;
		}
		// Methods from interface Store.
		public void createTransaction(ServerCall call, long TID, ArrayList<java.lang.Object> Options) throws VeyronException { 
			this.service.createTransaction(call, TID, Options);
		}
		public void commit(ServerCall call, long TID) throws VeyronException { 
			this.service.commit(call, TID);
		}
		public void abort(ServerCall call, long TID) throws VeyronException { 
			this.service.abort(call, TID);
		}
		public void readConflicts(ServerCall call) throws VeyronException { 
			final ServerCall serverCall = call;
			final Stream<Conflict,Void> stream = new Stream<Conflict,Void>() {
				@Override
				public void send(Conflict item) throws VeyronException {
					serverCall.send(item);
				}
				@Override
				public Void recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<Void>() {};
					final java.lang.Object result = serverCall.recv(type);
					try {
						return (Void)result;
					} catch (java.lang.ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
			};
			this.service.readConflicts(call, stream);
		}
	}
}
