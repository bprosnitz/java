// This file was auto-generated by the veyron vdl tool.
// Source(s):  logreader.vdl
package com.veyron2.services.mgmt.logreader.gen_impl;

/* Client stub for interface: LogFile. */
public final class LogFileStub implements com.veyron2.services.mgmt.logreader.LogFile {
    private static final java.lang.String vdlIfacePathOpt = "com.veyron2.services.mgmt.logreader.LogFile";
    private final com.veyron2.ipc.Client client;
    private final java.lang.String veyronName;

    
    

    public LogFileStub(final com.veyron2.ipc.Client client, final java.lang.String veyronName) {
        this.client = client;
        this.veyronName = veyronName;
        
        
    }

    // Methods from interface LogFile.


    
    public long size(final com.veyron2.ipc.Context context) throws com.veyron2.ipc.VeyronException {
        return size(context, null);
    }
    
    public long size(final com.veyron2.ipc.Context context, com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException {
        
        // Add VDL path option.
        // NOTE(spetrovic): this option is temporary and will be removed soon after we switch
        // Java to encoding/decoding from vom.Value objects.
        if (veyronOpts == null) veyronOpts = new com.veyron2.Options();
        if (!veyronOpts.has(com.veyron2.OptionDefs.VDL_INTERFACE_PATH)) {
            veyronOpts.set(com.veyron2.OptionDefs.VDL_INTERFACE_PATH, LogFileStub.vdlIfacePathOpt);
        }

        
        // Start the call.
        final java.lang.Object[] inArgs = new java.lang.Object[]{  };
        final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.veyronName, "size", inArgs, veyronOpts);

        // Finish the call.
        
        

         
        final com.google.common.reflect.TypeToken<?>[] resultTypes = new com.google.common.reflect.TypeToken<?>[]{
            
            new com.google.common.reflect.TypeToken<java.lang.Long>() {
                private static final long serialVersionUID = 1L;
            },
            
        };
        final java.lang.Object[] results = call.finish(resultTypes);
         
        return (java.lang.Long)results[0];
         

         

        
    }

    
    public com.veyron2.vdl.ClientStream<java.lang.Void,com.veyron2.services.mgmt.logreader.LogEntry, java.lang.Long> readLog(final com.veyron2.ipc.Context context, final long StartPos, final int NumEntries, final boolean Follow) throws com.veyron2.ipc.VeyronException {
        return readLog(context, StartPos, NumEntries, Follow, null);
    }
    
    public com.veyron2.vdl.ClientStream<java.lang.Void,com.veyron2.services.mgmt.logreader.LogEntry, java.lang.Long> readLog(final com.veyron2.ipc.Context context, final long StartPos, final int NumEntries, final boolean Follow, com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException {
        
        // Add VDL path option.
        // NOTE(spetrovic): this option is temporary and will be removed soon after we switch
        // Java to encoding/decoding from vom.Value objects.
        if (veyronOpts == null) veyronOpts = new com.veyron2.Options();
        if (!veyronOpts.has(com.veyron2.OptionDefs.VDL_INTERFACE_PATH)) {
            veyronOpts.set(com.veyron2.OptionDefs.VDL_INTERFACE_PATH, LogFileStub.vdlIfacePathOpt);
        }

        
        // Start the call.
        final java.lang.Object[] inArgs = new java.lang.Object[]{ StartPos, NumEntries, Follow };
        final com.veyron2.ipc.Client.Call call = this.client.startCall(context, this.veyronName, "readLog", inArgs, veyronOpts);

        // Finish the call.
        
         
        return new com.veyron2.vdl.ClientStream<java.lang.Void, com.veyron2.services.mgmt.logreader.LogEntry, java.lang.Long>() {
            @Override
            public void send(final java.lang.Void item) throws com.veyron2.ipc.VeyronException {
                call.send(item);
            }
            @Override
            public com.veyron2.services.mgmt.logreader.LogEntry recv() throws java.io.EOFException, com.veyron2.ipc.VeyronException {
                final com.google.common.reflect.TypeToken<?> type = new com.google.common.reflect.TypeToken<com.veyron2.services.mgmt.logreader.LogEntry>() {
                    private static final long serialVersionUID = 1L;
                };
                final java.lang.Object result = call.recv(type);
                try {
                    return (com.veyron2.services.mgmt.logreader.LogEntry)result;
                } catch (java.lang.ClassCastException e) {
                    throw new com.veyron2.ipc.VeyronException("Unexpected result type: " + result.getClass().getCanonicalName());
                }
            }
            @Override
            public java.lang.Long finish() throws com.veyron2.ipc.VeyronException {
                 
                final com.google.common.reflect.TypeToken<?>[] resultTypes = new com.google.common.reflect.TypeToken<?>[]{
                    new com.google.common.reflect.TypeToken<java.lang.Long>() {
                        private static final long serialVersionUID = 1L;
                    }
                };
                return (java.lang.Long)call.finish(resultTypes)[0];
                 
            }
        };
        
    }





}
