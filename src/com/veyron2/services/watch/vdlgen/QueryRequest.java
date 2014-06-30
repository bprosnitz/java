
// This file was auto-generated by the veyron vdl tool.
// Source: service.vdl
package com.veyron2.services.watch.vdlgen;

import com.veyron2.query.vdlgen.Query;
import java.util.ArrayList;

/**
 * QueryRequest specifies which entities should be watched and, optionally,
 * how to resume from a previous Watch call.
**/
public class QueryRequest { 
	// Query specifies the subset of the children of the root entity
// for which the client wants updates.
public Query query;
	// ResumeMarker specifies how to resume from a previous Watch call.
// See the ResumeMarker type for detailed comments.
public ArrayList<Byte> resumeMarker;
}
