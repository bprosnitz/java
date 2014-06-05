// This file was auto-generated by the veyron vdl tool.
// Source(s):  fortune.vdl
package com.veyron.examples.fortune;

import com.veyron2.ipc.ServerCall;
import com.veyron2.ipc.VeyronException;

public class Server { 
	/* Server stub creation methods for interfaces in file: fortune.vdl. */
	public static Object newFortune(FortuneService service) { 
		return new FortuneStub(service);
	}
	
	/* Server stubs for interfaces in file: fortune.vdl. */
	public static class FortuneStub {
		private final FortuneService service;

		FortuneStub(FortuneService service) {
			this.service = service;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public Object[] getMethodTags(ServerCall call, String method) { 
			if (method == "Get") {
				return new Object[]{ 1 };
			}
			if (method == "Add") {
				return new Object[]{ 2 };
			}
			return null;
		}
		// Methods from interface Fortune.
		public String get(ServerCall call) throws VeyronException { 
			return this.service.get(call);
		}
		public void add(ServerCall call, String Fortune) throws VeyronException { 
			this.service.add(call, Fortune);
		}
	}
}
