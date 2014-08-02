// This file was auto-generated by the veyron vdl tool.
// Source(s):  repository.vdl
package com.veyron.services.mgmt.repository.gen_impl;

public final class ProfileServiceWrapper {

    private final com.veyron.services.mgmt.repository.ProfileService service;



    
    private final com.veyron2.services.mgmt.repository.gen_impl.ProfileServiceWrapper profileWrapper;
    

    public ProfileServiceWrapper(final com.veyron.services.mgmt.repository.ProfileService service) {
        this.service = service;
        
        
        this.profileWrapper = new com.veyron2.services.mgmt.repository.gen_impl.ProfileServiceWrapper(service);
        
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
        
        if ("put".equals(method)) {
            return new java.lang.Object[] {
                 new com.veyron2.security.Label(2), 
            };
        }
        
        if ("remove".equals(method)) {
            return new java.lang.Object[] {
                 new com.veyron2.security.Label(2), 
            };
        }
        
        if ("specification".equals(method)) {
            return new java.lang.Object[] {
                 new com.veyron2.security.Label(1), 
            };
        }
        
        
        try {
            return this.profileWrapper.getMethodTags(call, method);
        } catch (com.veyron2.ipc.VeyronException e) {}  // method not found.
        
        throw new com.veyron2.ipc.VeyronException("method: " + method + " not found");
    }

     
    
    public com.veyron.services.mgmt.profile.Specification specification(final com.veyron2.ipc.ServerCall call) throws com.veyron2.ipc.VeyronException {
         
         return  this.service.specification( call   );
    }

    public void put(final com.veyron2.ipc.ServerCall call, final com.veyron.services.mgmt.profile.Specification Specification) throws com.veyron2.ipc.VeyronException {
         
         this.service.put( call , Specification  );
    }

    public void remove(final com.veyron2.ipc.ServerCall call) throws com.veyron2.ipc.VeyronException {
         
         this.service.remove( call   );
    }




    public java.lang.String label(final com.veyron2.ipc.ServerCall call) throws com.veyron2.ipc.VeyronException {
        
        return  this.profileWrapper.label(call);
    }

    public java.lang.String description(final com.veyron2.ipc.ServerCall call) throws com.veyron2.ipc.VeyronException {
        
        return  this.profileWrapper.description(call);
    }
 

}