
// This file was auto-generated by the veyron vdl tool.
// Source: node.vdl
package com.veyron2.services.mgmt.node.vdlgen;

import java.util.HashSet;

/**
 * Description enumerates the profiles that a Node supports.
**/
public class Description { 
	// Profiles is a set of names of supported profiles.	Each name can
// either be a veyron name that resolves to a Profile, or can be the
// profile's label, e.g.:
//   "profiles/google/cluster/diskfull"
//   "linux-media"
//
// Profiles for nodes can be provided by hand, but they can also be
// automatically derived by examining the node.
public HashSet<String> profiles;
}