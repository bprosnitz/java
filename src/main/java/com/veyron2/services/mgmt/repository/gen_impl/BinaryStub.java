// This file was auto-generated by the veyron vdl tool.
// Source(s):  repository.vdl
package com.veyron2.services.mgmt.repository.gen_impl;

/* Client stub for interface: Binary. */
public final class BinaryStub implements com.veyron2.services.mgmt.repository.Binary {
    private static final java.lang.String vdlIfacePathOpt = "com.veyron2.services.mgmt.repository.Binary";
    private final com.veyron2.ipc.Client client;
    private final java.lang.String veyronName;

    
    

    public BinaryStub(final com.veyron2.ipc.Client client, final java.lang.String veyronName) {
        this.client = client;
        this.veyronName = veyronName;
        
        
    }

    // Methods from interface Binary.


    
    public void create(final com.veyron2.ipc.Context context, final int nparts) throws com.veyron2.ipc.VeyronException {
         create(context, nparts, null);
    }
    
    public void create(final com.veyron2.ipc.Context context, final int nparts, com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException {
        
        // Add VDL path option.
        // NOTE(spetrovic): this option is temporary and will be removed soon after we switch
        // Java to encoding/decoding from vom.Value objects.
        if (veyronOpts == null) veyronOpts = new com.veyron2.Options();
        if (!veyronOpts.has(com.veyron2.OptionDefs.VDL_INTERFACE_PATH)) {
            veyronOpts.set(com.veyron2.OptionDefs.VDL_INTERFACE_PATH, BinaryStub.vdlIfacePathOpt);
        }

        
        // Start the call.
        final java.lang.Object[] inArgs = new java.lang.Object[]{ nparts };
        final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.veyronName, "create", inArgs, veyronOpts);

        // Finish the call.
        
        

        
        final com.google.common.reflect.TypeToken<?>[] resultTypes = new com.google.common.reflect.TypeToken<?>[]{};
        call.finish(resultTypes);
         

        
    }

    
    public void delete(final com.veyron2.ipc.Context context) throws com.veyron2.ipc.VeyronException {
         delete(context, null);
    }
    
    public void delete(final com.veyron2.ipc.Context context, com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException {
        
        // Add VDL path option.
        // NOTE(spetrovic): this option is temporary and will be removed soon after we switch
        // Java to encoding/decoding from vom.Value objects.
        if (veyronOpts == null) veyronOpts = new com.veyron2.Options();
        if (!veyronOpts.has(com.veyron2.OptionDefs.VDL_INTERFACE_PATH)) {
            veyronOpts.set(com.veyron2.OptionDefs.VDL_INTERFACE_PATH, BinaryStub.vdlIfacePathOpt);
        }

        
        // Start the call.
        final java.lang.Object[] inArgs = new java.lang.Object[]{  };
        final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.veyronName, "delete", inArgs, veyronOpts);

        // Finish the call.
        
        

        
        final com.google.common.reflect.TypeToken<?>[] resultTypes = new com.google.common.reflect.TypeToken<?>[]{};
        call.finish(resultTypes);
         

        
    }

    
    public com.veyron2.vdl.ClientStream<java.lang.Void,byte[], java.lang.Void> download(final com.veyron2.ipc.Context context, final int part) throws com.veyron2.ipc.VeyronException {
        return download(context, part, null);
    }
    
    public com.veyron2.vdl.ClientStream<java.lang.Void,byte[], java.lang.Void> download(final com.veyron2.ipc.Context context, final int part, com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException {
        
        // Add VDL path option.
        // NOTE(spetrovic): this option is temporary and will be removed soon after we switch
        // Java to encoding/decoding from vom.Value objects.
        if (veyronOpts == null) veyronOpts = new com.veyron2.Options();
        if (!veyronOpts.has(com.veyron2.OptionDefs.VDL_INTERFACE_PATH)) {
            veyronOpts.set(com.veyron2.OptionDefs.VDL_INTERFACE_PATH, BinaryStub.vdlIfacePathOpt);
        }

        
        // Start the call.
        final java.lang.Object[] inArgs = new java.lang.Object[]{ part };
        final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.veyronName, "download", inArgs, veyronOpts);

        // Finish the call.
        
         
        return new com.veyron2.vdl.ClientStream<java.lang.Void, byte[], java.lang.Void>() {
            @Override
            public void send(final java.lang.Void item) throws com.veyron2.ipc.VeyronException {
                call.send(item);
            }
            @Override
            public byte[] recv() throws java.io.EOFException, com.veyron2.ipc.VeyronException {
                final com.google.common.reflect.TypeToken<?> type = new com.google.common.reflect.TypeToken<byte[]>() {
                    private static final long serialVersionUID = 1L;
                };
                final java.lang.Object result = call.recv(type);
                try {
                    return (byte[])result;
                } catch (java.lang.ClassCastException e) {
                    throw new com.veyron2.ipc.VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
                }
            }
            @Override
            public java.lang.Void finish() throws com.veyron2.ipc.VeyronException {
                
                final com.google.common.reflect.TypeToken<?>[] resultTypes = new com.google.common.reflect.TypeToken<?>[]{};
                call.finish(resultTypes);
                return null;
                 
            }
        };
        
    }

    
    public com.veyron2.services.mgmt.repository.Binary.DownloadURLOut downloadURL(final com.veyron2.ipc.Context context) throws com.veyron2.ipc.VeyronException {
        return downloadURL(context, null);
    }
    
    public com.veyron2.services.mgmt.repository.Binary.DownloadURLOut downloadURL(final com.veyron2.ipc.Context context, com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException {
        
        // Add VDL path option.
        // NOTE(spetrovic): this option is temporary and will be removed soon after we switch
        // Java to encoding/decoding from vom.Value objects.
        if (veyronOpts == null) veyronOpts = new com.veyron2.Options();
        if (!veyronOpts.has(com.veyron2.OptionDefs.VDL_INTERFACE_PATH)) {
            veyronOpts.set(com.veyron2.OptionDefs.VDL_INTERFACE_PATH, BinaryStub.vdlIfacePathOpt);
        }

        
        // Start the call.
        final java.lang.Object[] inArgs = new java.lang.Object[]{  };
        final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.veyronName, "downloadURL", inArgs, veyronOpts);

        // Finish the call.
        
        

         
        final com.google.common.reflect.TypeToken<?>[] resultTypes = new com.google.common.reflect.TypeToken<?>[]{
            
            new com.google.common.reflect.TypeToken<java.lang.String>() {
                private static final long serialVersionUID = 1L;
            },
            
            new com.google.common.reflect.TypeToken<java.lang.Long>() {
                private static final long serialVersionUID = 1L;
            },
            
        };
        final java.lang.Object[] results = call.finish(resultTypes);
        
        final com.veyron2.services.mgmt.repository.Binary.DownloadURLOut ret = new com.veyron2.services.mgmt.repository.Binary.DownloadURLOut();
            
        ret.uRL = (java.lang.String)results[0];
            
        ret.tTL = (java.lang.Long)results[1];
             
        return ret;
         

         

        
    }

    
    public java.util.List<com.veyron2.services.mgmt.binary.PartInfo> stat(final com.veyron2.ipc.Context context) throws com.veyron2.ipc.VeyronException {
        return stat(context, null);
    }
    
    public java.util.List<com.veyron2.services.mgmt.binary.PartInfo> stat(final com.veyron2.ipc.Context context, com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException {
        
        // Add VDL path option.
        // NOTE(spetrovic): this option is temporary and will be removed soon after we switch
        // Java to encoding/decoding from vom.Value objects.
        if (veyronOpts == null) veyronOpts = new com.veyron2.Options();
        if (!veyronOpts.has(com.veyron2.OptionDefs.VDL_INTERFACE_PATH)) {
            veyronOpts.set(com.veyron2.OptionDefs.VDL_INTERFACE_PATH, BinaryStub.vdlIfacePathOpt);
        }

        
        // Start the call.
        final java.lang.Object[] inArgs = new java.lang.Object[]{  };
        final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.veyronName, "stat", inArgs, veyronOpts);

        // Finish the call.
        
        

         
        final com.google.common.reflect.TypeToken<?>[] resultTypes = new com.google.common.reflect.TypeToken<?>[]{
            
            new com.google.common.reflect.TypeToken<java.util.List<com.veyron2.services.mgmt.binary.PartInfo>>() {
                private static final long serialVersionUID = 1L;
            },
            
        };
        final java.lang.Object[] results = call.finish(resultTypes);
         
        return (java.util.List<com.veyron2.services.mgmt.binary.PartInfo>)results[0];
         

         

        
    }

    
    public com.veyron2.vdl.ClientStream<byte[],java.lang.Void, java.lang.Void> upload(final com.veyron2.ipc.Context context, final int part) throws com.veyron2.ipc.VeyronException {
        return upload(context, part, null);
    }
    
    public com.veyron2.vdl.ClientStream<byte[],java.lang.Void, java.lang.Void> upload(final com.veyron2.ipc.Context context, final int part, com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException {
        
        // Add VDL path option.
        // NOTE(spetrovic): this option is temporary and will be removed soon after we switch
        // Java to encoding/decoding from vom.Value objects.
        if (veyronOpts == null) veyronOpts = new com.veyron2.Options();
        if (!veyronOpts.has(com.veyron2.OptionDefs.VDL_INTERFACE_PATH)) {
            veyronOpts.set(com.veyron2.OptionDefs.VDL_INTERFACE_PATH, BinaryStub.vdlIfacePathOpt);
        }

        
        // Start the call.
        final java.lang.Object[] inArgs = new java.lang.Object[]{ part };
        final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.veyronName, "upload", inArgs, veyronOpts);

        // Finish the call.
        
         
        return new com.veyron2.vdl.ClientStream<byte[], java.lang.Void, java.lang.Void>() {
            @Override
            public void send(final byte[] item) throws com.veyron2.ipc.VeyronException {
                call.send(item);
            }
            @Override
            public java.lang.Void recv() throws java.io.EOFException, com.veyron2.ipc.VeyronException {
                final com.google.common.reflect.TypeToken<?> type = new com.google.common.reflect.TypeToken<java.lang.Void>() {
                    private static final long serialVersionUID = 1L;
                };
                final java.lang.Object result = call.recv(type);
                try {
                    return (java.lang.Void)result;
                } catch (java.lang.ClassCastException e) {
                    throw new com.veyron2.ipc.VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
                }
            }
            @Override
            public java.lang.Void finish() throws com.veyron2.ipc.VeyronException {
                
                final com.google.common.reflect.TypeToken<?>[] resultTypes = new com.google.common.reflect.TypeToken<?>[]{};
                call.finish(resultTypes);
                return null;
                 
            }
        };
        
    }





}
