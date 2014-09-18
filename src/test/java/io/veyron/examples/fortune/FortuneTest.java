package io.veyron.examples.fortune;

import com.veyron2.OptionDefs;
import com.veyron2.Options;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.Dispatcher;
import com.veyron2.ipc.Server;
import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.ServiceObjectWithAuthorizer;
import com.veyron2.ipc.VeyronException;
import com.veyron2.Runtime;
import com.veyron2.RuntimeFactory;

import android.test.AndroidTestCase;
import android.util.Log;

public class FortuneTest extends AndroidTestCase {
	private static VeyronException noneAdded = new VeyronException("no fortunes added");

	public static class FortuneServiceImpl implements FortuneService {
		private String lastAddedFortune;

		@Override
		public String get(ServerContext context) throws VeyronException {
			if (lastAddedFortune == null) {
				throw noneAdded;
			}
			return lastAddedFortune;
		}

		@Override
		public void add(ServerContext context, String fortune) throws VeyronException {
			lastAddedFortune = fortune;
		}

	}

    public void testFortuneJavaToJava()
        throws IllegalArgumentException, VeyronException {
    	// TODO(bprosnitz) We shouldn't have to initialize the default runtime to use non-default runtimes.
    	RuntimeFactory.init(getContext(), new Options());

    	final Runtime serverRuntime = RuntimeFactory.newRuntime(getContext(), null);
    	final Server server = serverRuntime.newServer();
    	final String endpoint = server.listen("tcp", "127.0.0.1:0");
    	final FortuneService service = new FortuneServiceImpl();
    	server.serve("fortune", new Dispatcher() {
            @Override
            public ServiceObjectWithAuthorizer lookup(String suffix)
                throws VeyronException {
            	return new ServiceObjectWithAuthorizer(service, null);
            }
        });
    	try {
	    	Options options = new Options();
	    	options.set(OptionDefs.RUNTIME, serverRuntime);
	    	// TODO(bprosnitz) We get an ACL related error when using a different runtime than the server. Fix this.
	    	//Runtime clientRuntime = RuntimeFactory.newRuntime(getContext(),
	        //        null);
	    	//options.set(OptionDefs.RUNTIME_ID, serverRuntime.getIdentity());
	    	//options.set(OptionDefs.RUNTIME, clientRuntime);

	    	// TODO(bprosnitz) This gets around the mounttable by prefixing the endpoint by "/".
	    	// We should start the mounttable and use it for the test in the future.
	    	String name = "/" + endpoint + "/fortune";

	    	Fortune fortune = FortuneFactory.bind(name, options);

	    	Context context = null;

	    	try {
	    		fortune.get(context);
	    		fail("Expected exception during call to get() before call to add()");
	    	} catch (VeyronException e) {
	    		assertEquals(e, noneAdded);
	    	}

	    	final String firstMessage = "First fortune";
	    	fortune.add(context, firstMessage);
	    	assertEquals(firstMessage, fortune.get(context));
    	} finally {
    		server.stop();
    	}
    }

}