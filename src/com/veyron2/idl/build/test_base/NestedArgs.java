
// This file was auto-generated by the veyron idl tool.
// Source: base.idl
package com.veyron2.idl.build.test_base;

import com.veyron2.idl.build.test_base.Args;
import com.veyron2.idl.build.test_base.NestedArgs;

/**
 * NestedArgs is defined before Args; that's allowed in regular Go, and also
 * allowed in our idl files.  The compiler will re-order dependent types to ease
 * code generation in other languages.
**/
public class NestedArgs { 
	public Args args;
}
