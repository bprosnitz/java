// This file was auto-generated by the veyron vdl tool.
// Source: logreader.vdl
package com.veyron2.services.mgmt.logreader;

/**
 * LogFile can be used to access log files remotely.
 */

@com.veyron2.vdl.VeyronService(serviceWrapper=com.veyron2.services.mgmt.logreader.gen_impl.LogFileServiceWrapper.class)
public interface LogFileService  {

    
    // Size returns the number of bytes in the receiving object.

    public long size(final com.veyron2.ipc.ServerContext context) throws com.veyron2.ipc.VeyronException;

    
    // ReadLog receives up to NumEntries log entries starting at the
// StartPos offset (in bytes) in the receiving object. Each stream chunk
// contains one log entry.
//
// If Follow is true, ReadLog will block and wait for more entries to
// arrive when it reaches the end of the file.
//
// ReadLog returns the position where it stopped reading, i.e. the
// position where the next entry starts. This value can be used as
// StartPos for successive calls to ReadLog.
//
// The returned error will be EOF if and only if ReadLog reached the
// end of the file and no log entries were returned.

    public long readLog(final com.veyron2.ipc.ServerContext context, final long StartPos, final int NumEntries, final boolean Follow, com.veyron2.vdl.Stream<java.lang.Void, com.veyron2.services.mgmt.logreader.types.LogEntry> stream) throws com.veyron2.ipc.VeyronException;

}