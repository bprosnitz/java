// This file was auto-generated by the veyron vdl tool.
// Source: protocol.vdl
package com.veyron2.ipc;

/**
 * type Response struct{Error error struct{Id string;Msg string};EndStreamResults bool;NumPosResults uint64;TraceResponse veyron2/vtrace.Response struct{Method veyron2/vtrace.TraceMethod int32;Trace veyron2/vtrace.TraceRecord struct{ID veyron2/uniqueid.ID [16]byte;Spans []veyron2/vtrace.SpanRecord struct{ID veyron2/uniqueid.ID;Parent veyron2/uniqueid.ID;Name string;Annotations []veyron2/vtrace.Annotation struct{When int64;Message string}}}}} 
 * Response describes the response header sent by the server to the client.  A
 * zero response header is sent before each streaming arg.  Thereafter a
 * non-zero response header is sent at the end of the RPC call, right before
 * the positional results.
 **/
public final class Response implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      @com.google.gson.annotations.SerializedName("Error")
      private com.veyron2.ipc.VeyronException error;
    
      @com.google.gson.annotations.SerializedName("EndStreamResults")
      private boolean endStreamResults;
    
      @com.google.gson.annotations.SerializedName("NumPosResults")
      private long numPosResults;
    
      @com.google.gson.annotations.SerializedName("TraceResponse")
      private com.veyron2.vtrace.Response traceResponse;
    

    
    public Response(final com.veyron2.ipc.VeyronException error, final boolean endStreamResults, final long numPosResults, final com.veyron2.vtrace.Response traceResponse) {
        
            this.error = error;
        
            this.endStreamResults = endStreamResults;
        
            this.numPosResults = numPosResults;
        
            this.traceResponse = traceResponse;
        
    }

    
    
    public com.veyron2.ipc.VeyronException getError() {
        return this.error;
    }
    public void setError(com.veyron2.ipc.VeyronException error) {
        this.error = error;
    }
    
    public boolean getEndStreamResults() {
        return this.endStreamResults;
    }
    public void setEndStreamResults(boolean endStreamResults) {
        this.endStreamResults = endStreamResults;
    }
    
    public long getNumPosResults() {
        return this.numPosResults;
    }
    public void setNumPosResults(long numPosResults) {
        this.numPosResults = numPosResults;
    }
    
    public com.veyron2.vtrace.Response getTraceResponse() {
        return this.traceResponse;
    }
    public void setTraceResponse(com.veyron2.vtrace.Response traceResponse) {
        this.traceResponse = traceResponse;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Response other = (Response)obj;

        
        
        
        if (this.error == null) {
            if (other.error != null) {
                return false;
            }
        } else if (!this.error.equals(other.error)) {
            return false;
        }
         
         
        
        
        
        if (this.endStreamResults != other.endStreamResults) {
            return false;
        }
         
         
        
        
        
        if (this.numPosResults != other.numPosResults) {
            return false;
        }
         
         
        
        
        
        if (this.traceResponse == null) {
            if (other.traceResponse != null) {
                return false;
            }
        } else if (!this.traceResponse.equals(other.traceResponse)) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (error == null ? 0 : error.hashCode());
        
        result = prime * result + java.lang.Boolean.valueOf(endStreamResults).hashCode();
        
        result = prime * result + java.lang.Long.valueOf(numPosResults).hashCode();
        
        result = prime * result + (traceResponse == null ? 0 : traceResponse.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, error);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, endStreamResults);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, numPosResults);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, traceResponse);
    	
    }
	public static final android.os.Parcelable.Creator<Response> CREATOR
		= new android.os.Parcelable.Creator<Response>() {
		@Override
		public Response createFromParcel(android.os.Parcel in) {
			return new Response(in);
		}
		@Override
		public Response[] newArray(int size) {
			return new Response[size];
		}
	};
	private Response(android.os.Parcel in) {
		
			this.error = (com.veyron2.ipc.VeyronException) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.error);
		
			this.endStreamResults = (boolean) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.endStreamResults);
		
			this.numPosResults = (long) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.numPosResults);
		
			this.traceResponse = (com.veyron2.vtrace.Response) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.traceResponse);
		
	}
}