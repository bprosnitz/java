// This file was auto-generated by the veyron vdl tool.
// Source: service.vdl
package com.veyron.examples.rockpaperscissors.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;

/**
 * ScoreKeeper receives the outcome of games from Judges.
**/
public interface ScoreKeeperService { 
		public void record(ServerContext context, ScoreCard score) throws VeyronException;
}
