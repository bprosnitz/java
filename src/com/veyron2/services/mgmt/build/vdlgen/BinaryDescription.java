
// This file was auto-generated by the veyron vdl tool.
// Source: build.vdl
package com.veyron2.services.mgmt.build.vdlgen;

import java.util.HashMap;

/**
 * Binaries are named and have been determined to run on some set of
 * profiles. The mechanism for determing profiles is specifically not
 * specified and left to the implementation of the Build interface.
**/
public class BinaryDescription { 
	// Name is the Veyron name of the application binary that can be
// used to fetch the actual binary from a content server.
public String name;
	// Profiles is a set of names of compatible profiles.  Each name can either
// be a Veyron name that resolves to a Profile, or can be the profile's
// label, e.g.:
//   "profiles/google/cluster/diskfull"
//   "linux-media"
//
// Application developers can specify compatible profiles by hand, but we also
// want to be able to automatically derive the matching profiles from
// examining the binary itself (e.g. that's what Build.Describe() does).
public HashMap<String, Boolean> profiles;
}