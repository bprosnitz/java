// This file was auto-generated by the veyron idl tool.
// Source: base.idl
package com.veyron2.idl.build.test_base;

import java.util.ArrayList;
import com.veyron2.idl.build.test_base.Args;
import com.veyron2.idl.build.test_base.MyInterfaceA;
import com.veyron2.ipc.Client;
import com.veyron2.ipc.VeyronException;

/**
 * MyInterfaceA is an interface that defines two methods.
**/
public interface MyInterfaceA  { 
	// A takes in two arguments and returns an unnamed argument and an error.
	public ArrayList<String> a(Args a, int[] b, Client.CallOption... opts) throws VeyronException;
	// B takes in no arguments and returns an error.
	public void b(Client.CallOption... opts) throws VeyronException;
}
