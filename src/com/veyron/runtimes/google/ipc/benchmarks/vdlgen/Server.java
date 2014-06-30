// This file was auto-generated by the veyron vdl tool.
// Source(s):  service.vdl
package com.veyron.runtimes.google.ipc.benchmarks.vdlgen;

import com.google.common.reflect.TypeToken;
import com.veyron2.ipc.ServerCall;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.Stream;
import java.util.ArrayList;

public class Server { 
	/* Server stub creation methods for interfaces in file: service.vdl. */
	public static Object newBenchmark(BenchmarkService service) { 
		return new BenchmarkStub(service);
	}
	
	/* Server stubs for interfaces in file: service.vdl. */
	public static class BenchmarkStub {
		private final BenchmarkService service;

		BenchmarkStub(BenchmarkService service) {
			this.service = service;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public Object[] getMethodTags(ServerCall call, String method) { 
			if (method == "Echo") {
				return new Object[]{  };
			}
			if (method == "EchoStream") {
				return new Object[]{  };
			}
			return null;
		}
		// Methods from interface Benchmark.
		public ArrayList<Byte> echo(ServerCall call, ArrayList<Byte> Payload) throws VeyronException { 
			return this.service.echo(call, Payload);
		}
		public void echoStream(ServerCall call) throws VeyronException { 
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
			this.service.echoStream(call, stream);
		}
	}
}
