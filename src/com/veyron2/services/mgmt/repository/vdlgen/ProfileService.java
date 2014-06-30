// This file was auto-generated by the veyron vdl tool.
// Source: repository.vdl
package com.veyron2.services.mgmt.repository.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;

/**
 * Profile abstracts a device's ability to run binaries, and hides
 * specifics such as the operating system, hardware architecture, and
 * the set of installed libraries. Profiles describe binaries and
 * devices, and are used to match them.
**/
public interface ProfileService { 
	// Label is the human-readable profile key for the profile,
// e.g. "linux-media". The label can be used to uniquely identify
// the profile (for the purpose of matching application binaries and
// nodes).
	public String label(ServerContext context) throws VeyronException;
	// Description is a free-text description of the profile, meant for
// human consumption.
	public String description(ServerContext context) throws VeyronException;
}
