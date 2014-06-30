// This file was auto-generated by the veyron vdl tool.
// Source: collection_test.vdl
package com.veyron.services.mounttable.lib.vdlgen;

import com.veyron2.Options;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;
import java.util.ArrayList;

public interface Collection { 
	// Export sets the value for a name.  Overwrite controls the behavior when
// an entry exists, if Overwrite is true, then the binding is replaced,
// otherwise the call fails with an error.  The Val must be no larger than
// MaxSize bytes.
	public void export(Context context, String val, boolean overwrite) throws VeyronException;
	public void export(Context context, String val, boolean overwrite, Options veyronOpts) throws VeyronException;
	// Lookup retrieves the value associated with a name.  Returns an error if
// there is no such binding.
	public ArrayList<Byte> lookup(Context context) throws VeyronException;
	public ArrayList<Byte> lookup(Context context, Options veyronOpts) throws VeyronException;
}
