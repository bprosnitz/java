// This file was auto-generated by the veyron vdl tool.
// Source(s):  service.vdl
package com.veyron2.services.mounttable.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.ipc.ServerCall;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.Stream;
import java.util.ArrayList;

public class Server { 
	/* Server stub creation methods for interfaces in file: service.vdl. */
	public static Object newGlobable(GlobableService service) { 
		return new GlobableStub(service);
	}
	public static Object newMountTable(MountTableService service) { 
		final Server.GlobableStub globable = (Server.GlobableStub)Server.newGlobable(service);
		return new MountTableStub(service, globable);
	}
	
	/* Server stubs for interfaces in file: service.vdl. */
	public static class GlobableStub {
		private final GlobableService service;

		GlobableStub(GlobableService service) {
			this.service = service;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public Object[] getMethodTags(ServerCall call, String method) { 
			if (method == "Glob") {
				return new Object[]{ 1 };
			}
			return null;
		}
		// Methods from interface Globable.
		public void glob(ServerCall call, String pattern) throws VeyronException { 
			final ServerCall serverCall = call;
			final Stream<MountEntry,Void> stream = new Stream<MountEntry,Void>() {
				@Override
				public void send(MountEntry item) throws VeyronException {
					serverCall.send(item);
				}
				@Override
				public Void recv() throws java.io.EOFException, VeyronException {
					final TypeToken<?> type = new TypeToken<Void>() {};
					final Object result = serverCall.recv(type);
					try {
						return (Void)result;
					} catch (java.lang.ClassCastException e) {
						throw new VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
					}
				}
			};
			this.service.glob(call, pattern, stream);
		}
	}
	public static class MountTableStub {
		private final MountTableService service;
		private final Server.GlobableStub globable;

		MountTableStub(MountTableService service, Server.GlobableStub globable) {
			this.service = service;
			this.globable = globable;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public Object[] getMethodTags(ServerCall call, String method) { 
			{
				final Object[] tags = this.globable.getMethodTags(call, method);
				if (tags != null) return tags;
			}
			if (method == "Mount") {
				return new Object[]{ 2 };
			}
			if (method == "Unmount") {
				return new Object[]{ 2 };
			}
			if (method == "ResolveStep") {
				return new Object[]{ 1 };
			}
			return null;
		}
		// Methods from interface MountTable.
		public void mount(ServerCall call, String Server, int TTL) throws VeyronException { 
			this.service.mount(call, Server, TTL);
		}
		public void unmount(ServerCall call, String Server) throws VeyronException { 
			this.service.unmount(call, Server);
		}
		public MountTableService.ResolveStepOut resolveStep(ServerCall call) throws VeyronException { 
			return this.service.resolveStep(call);
		}
		// Methods from sub-interface Globable.
		public void glob(ServerCall call, String pattern) throws VeyronException {
			this.globable.glob(call, pattern);
		}
	}
}