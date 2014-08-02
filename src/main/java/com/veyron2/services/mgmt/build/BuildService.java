// This file was auto-generated by the veyron vdl tool.
// Source: build.vdl
package com.veyron2.services.mgmt.build;

/**
 * Build describes an interface for building binaries from source.
 */

@com.veyron2.vdl.VeyronService(serviceWrapper=com.veyron2.services.mgmt.build.gen_impl.BuildServiceWrapper.class)
public interface BuildService  {

    
    // Build streams sources to the build server, which then attempts to
// build the sources and streams back the compiled binaries.

    public java.util.ArrayList<java.lang.Byte> build(final com.veyron2.ipc.ServerContext context, final com.veyron2.services.mgmt.build.Architecture Arch, final com.veyron2.services.mgmt.build.OperatingSystem OS, com.veyron2.vdl.Stream<com.veyron2.services.mgmt.build.File, com.veyron2.services.mgmt.build.File> stream) throws com.veyron2.ipc.VeyronException;

    
    // Describe generates a description for a binary identified by
// the given Object name.

    public com.veyron2.services.mgmt.binary.Description describe(final com.veyron2.ipc.ServerContext context, final java.lang.String Name) throws com.veyron2.ipc.VeyronException;

}