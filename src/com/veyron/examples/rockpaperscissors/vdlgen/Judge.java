// This file was auto-generated by the veyron vdl tool.
// Source: service.vdl
package com.veyron.examples.rockpaperscissors.vdlgen;

import com.veyron2.Options;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.ClientStream;

public interface Judge { 
	// CreateGame creates a new game with the given game options and returns a game
// identifier that can be used by the players to join the game.
	public GameID createGame(Context context, GameOptions opts) throws VeyronException;
	public GameID createGame(Context context, GameOptions opts, Options veyronOpts) throws VeyronException;
	// Play lets a player join an existing game and play.
	public ClientStream<PlayerAction,JudgeAction,PlayResult> play(Context context, GameID iD) throws VeyronException;
	public ClientStream<PlayerAction,JudgeAction,PlayResult> play(Context context, GameID iD, Options veyronOpts) throws VeyronException;
}