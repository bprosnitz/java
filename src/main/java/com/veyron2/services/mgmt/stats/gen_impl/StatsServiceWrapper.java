// This file was auto-generated by the veyron vdl tool.
// Source(s):  stats.vdl
package com.veyron2.services.mgmt.stats.gen_impl;

public final class StatsServiceWrapper {

    private final com.veyron2.services.mgmt.stats.StatsService service;



    
    private final com.veyron2.services.mounttable.gen_impl.GlobbableServiceWrapper globbableWrapper;
    
    
    private final com.veyron2.services.watch.gen_impl.GlobWatcherServiceWrapper globWatcherWrapper;
    

    public StatsServiceWrapper(final com.veyron2.services.mgmt.stats.StatsService service) {
        this.service = service;
        
        
        this.globbableWrapper = new com.veyron2.services.mounttable.gen_impl.GlobbableServiceWrapper(service);
        
        this.globWatcherWrapper = new com.veyron2.services.watch.gen_impl.GlobWatcherServiceWrapper(service);
        
    }

    /**
     * Returns all tags associated with the provided method or null if the method isn't implemented
     * by this service.
     */
    public java.lang.Object[] getMethodTags(final com.veyron2.ipc.ServerCall call, final java.lang.String method) throws com.veyron2.ipc.VeyronException {
        
        if ("getMethodTags".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        if ("value".equals(method)) {
            return new java.lang.Object[] {
                
            };
        }
        
        
        try {
            return this.globbableWrapper.getMethodTags(call, method);
        } catch (com.veyron2.ipc.VeyronException e) {}  // method not found.
        
        try {
            return this.globWatcherWrapper.getMethodTags(call, method);
        } catch (com.veyron2.ipc.VeyronException e) {}  // method not found.
        
        throw new com.veyron2.ipc.VeyronException("method: " + method + " not found");
    }

     
    
    public java.lang.Object value(final com.veyron2.ipc.ServerCall call) throws com.veyron2.ipc.VeyronException {
         
         return  this.service.value( call   );
    }




    public void glob(final com.veyron2.ipc.ServerCall call, final java.lang.String pattern) throws com.veyron2.ipc.VeyronException {
        
          this.globbableWrapper.glob(call, pattern);
    }

    public void watchGlob(final com.veyron2.ipc.ServerCall call, final com.veyron2.services.watch.types.GlobRequest Req) throws com.veyron2.ipc.VeyronException {
        
          this.globWatcherWrapper.watchGlob(call, Req);
    }
 

}