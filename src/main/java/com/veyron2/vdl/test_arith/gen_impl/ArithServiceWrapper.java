// This file was auto-generated by the veyron vdl tool.
// Source(s):  arith.vdl
package com.veyron2.vdl.test_arith.gen_impl;

public final class ArithServiceWrapper {

    private final com.veyron2.vdl.test_arith.ArithService service;




    public ArithServiceWrapper(final com.veyron2.vdl.test_arith.ArithService service) {
        this.service = service;
        
        
    }

    /**
     * Returns all tags associated with the provided method or null if the method isn't implemented
     * by this service.
     */
    public java.lang.Object[] getMethodTags(final com.veyron2.ipc.ServerCall call, final java.lang.String method) throws com.veyron2.ipc.VeyronException {
        
        if ("add".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        if ("count".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        if ("divMod".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        if ("genError".equals(method)) {
            return new java.lang.Object[] {
                 "foo",  "barz",  "hello",  129,  36L, 
            };
        }
        
        if ("getMethodTags".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        if ("mul".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        if ("quoteAny".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        if ("streamingAdd".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        if ("sub".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        
        throw new com.veyron2.ipc.VeyronException("method: " + method + " not found");
    }

     
    
    public int add(final com.veyron2.ipc.ServerCall call, final int a, final int b) throws com.veyron2.ipc.VeyronException {
         
         return  this.service.add( call , a, b  );
    }

    public com.veyron2.vdl.test_arith.Arith.DivModOut divMod(final com.veyron2.ipc.ServerCall call, final int a, final int b) throws com.veyron2.ipc.VeyronException {
         
         return  this.service.divMod( call , a, b  );
    }

    public int sub(final com.veyron2.ipc.ServerCall call, final com.veyron2.vdl.test_base.Args args) throws com.veyron2.ipc.VeyronException {
         
         return  this.service.sub( call , args  );
    }

    public int mul(final com.veyron2.ipc.ServerCall call, final com.veyron2.vdl.test_base.NestedArgs nested) throws com.veyron2.ipc.VeyronException {
         
         return  this.service.mul( call , nested  );
    }

    public void genError(final com.veyron2.ipc.ServerCall call) throws com.veyron2.ipc.VeyronException {
         
         this.service.genError( call   );
    }

    public void count(final com.veyron2.ipc.ServerCall call, final int Start) throws com.veyron2.ipc.VeyronException {
        
        final com.veyron2.vdl.Stream<java.lang.Void, java.lang.Integer> stream = new com.veyron2.vdl.Stream<java.lang.Void, java.lang.Integer>() {
            @Override
            public void send(java.lang.Void item) throws com.veyron2.ipc.VeyronException {
                call.send(item);
            }
            @Override
            public java.lang.Integer recv() throws java.io.EOFException, com.veyron2.ipc.VeyronException {
                final com.google.common.reflect.TypeToken<?> type = new com.google.common.reflect.TypeToken< java.lang.Integer >() {
                    private static final long serialVersionUID = 1L;
                };
                final java.lang.Object result = call.recv(type);
                try {
                    return (java.lang.Integer)result;
                } catch (java.lang.ClassCastException e) {
                    throw new com.veyron2.ipc.VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
                }
            }
        };
         
         this.service.count( call , Start  ,stream  );
    }

    public int streamingAdd(final com.veyron2.ipc.ServerCall call) throws com.veyron2.ipc.VeyronException {
        
        final com.veyron2.vdl.Stream<java.lang.Integer, java.lang.Integer> stream = new com.veyron2.vdl.Stream<java.lang.Integer, java.lang.Integer>() {
            @Override
            public void send(java.lang.Integer item) throws com.veyron2.ipc.VeyronException {
                call.send(item);
            }
            @Override
            public java.lang.Integer recv() throws java.io.EOFException, com.veyron2.ipc.VeyronException {
                final com.google.common.reflect.TypeToken<?> type = new com.google.common.reflect.TypeToken< java.lang.Integer >() {
                    private static final long serialVersionUID = 1L;
                };
                final java.lang.Object result = call.recv(type);
                try {
                    return (java.lang.Integer)result;
                } catch (java.lang.ClassCastException e) {
                    throw new com.veyron2.ipc.VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
                }
            }
        };
         
         return  this.service.streamingAdd( call   ,stream  );
    }

    public java.lang.Object quoteAny(final com.veyron2.ipc.ServerCall call, final java.lang.Object a) throws com.veyron2.ipc.VeyronException {
         
         return  this.service.quoteAny( call , a  );
    }



 

}