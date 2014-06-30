// This file was auto-generated by the veyron vdl tool.
// Source: arith.vdl
package com.veyron2.vdl.test_arith.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;
import com.veyron2.vdl.Stream;
import com.veyron2.vdl.test_base.vdlgen.Args;
import com.veyron2.vdl.test_base.vdlgen.NestedArgs;

/**
 * Arith is an example of an interface definition for an arithmetic service.
 * Things to note:
 *   * There must be at least 1 out-arg, and the last out-arg must be error.
**/
public interface ArithService { 
	// Add is a typical method with multiple input and output arguments.
	public int add(ServerContext context, int a, int b) throws VeyronException;
	// DivModOut packages output arguments for method DivMod.
	public class DivModOut { 
		public int quot;
		public int rem;
	}
	// DivMod shows that runs of args with the same type can use the short form,
// just like Go.
	public ArithService.DivModOut divMod(ServerContext context, int a, int b) throws VeyronException;
	// Sub shows that you can use data types defined in other packages.
	public int sub(ServerContext context, Args args) throws VeyronException;
	// Mul tries another data type defined in another package.
	public int mul(ServerContext context, NestedArgs nested) throws VeyronException;
	// GenError shows that it's fine to have no in args, and no out args other
// than "error".  In addition GenError shows the usage of tags.  Tags are a
// sequence of constants.  There's no requirement on uniqueness of types or
// values, and regular const expressions may also be used.
	public void genError(ServerContext context) throws VeyronException;
	// Count shows using only an int32 out-stream type, with no in-stream type.
	public void count(ServerContext context, int start, Stream<Integer,Void> stream) throws VeyronException;
	// StreamingAdd shows a bidirectional stream.
	public int streamingAdd(ServerContext context, Stream<Integer,Integer> stream) throws VeyronException;
	// QuoteAny shows the any built-in type, representing a value of any type.
	public Object quoteAny(ServerContext context, Object a) throws VeyronException;
}