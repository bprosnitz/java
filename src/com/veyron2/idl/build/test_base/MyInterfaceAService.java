// This file was auto-generated by the veyron idl tool.
// Source: base.idl
package com.veyron2.idl.build.test_base;

import java.util.ArrayList;
import com.veyron2.idl.build.test_base.Args;
import com.veyron2.idl.build.test_base.MyInterfaceAService;
import com.veyron2.ipc.Context;
import com.veyron2.ipc.VeyronException;

/**
 * MyInterfaceA is an interface that defines two methods.
**/
public interface MyInterfaceAService  { 
	// A takes in two arguments and returns an unnamed argument and an error.
	public ArrayList<String> a(Context context, Args a, int[] b) throws VeyronException;
	// B takes in no arguments and returns an error.
	public void b(Context context) throws VeyronException;
}
