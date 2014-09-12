// This file was auto-generated by the veyron vdl tool.
// Source: types.vdl
package com.veyron2.vtrace;

/**
 * type Response struct{Method veyron2/vtrace.TraceMethod int32;Trace veyron2/vtrace.TraceRecord struct{ID veyron2/uniqueid.ID [16]byte;Spans []veyron2/vtrace.SpanRecord struct{ID veyron2/uniqueid.ID;Parent veyron2/uniqueid.ID;Name string;Annotations []veyron2/vtrace.Annotation struct{When int64;Message string}}}} 
 **/
public final class Response implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      @com.google.gson.annotations.SerializedName("Method")
      private com.veyron2.vtrace.TraceMethod method;
    
      @com.google.gson.annotations.SerializedName("Trace")
      private com.veyron2.vtrace.TraceRecord trace;
    

    
    public Response(final com.veyron2.vtrace.TraceMethod method, final com.veyron2.vtrace.TraceRecord trace) {
        
            this.method = method;
        
            this.trace = trace;
        
    }

    
    
    public com.veyron2.vtrace.TraceMethod getMethod() {
        return this.method;
    }
    public void setMethod(com.veyron2.vtrace.TraceMethod method) {
        this.method = method;
    }
    
    public com.veyron2.vtrace.TraceRecord getTrace() {
        return this.trace;
    }
    public void setTrace(com.veyron2.vtrace.TraceRecord trace) {
        this.trace = trace;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Response other = (Response)obj;

        
        
        
        if (this.method == null) {
            if (other.method != null) {
                return false;
            }
        } else if (!this.method.equals(other.method)) {
            return false;
        }
         
         
        
        
        
        if (this.trace == null) {
            if (other.trace != null) {
                return false;
            }
        } else if (!this.trace.equals(other.trace)) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (method == null ? 0 : method.hashCode());
        
        result = prime * result + (trace == null ? 0 : trace.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, method);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, trace);
    	
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
		
			this.method = (com.veyron2.vtrace.TraceMethod) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.method);
		
			this.trace = (com.veyron2.vtrace.TraceRecord) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.trace);
		
	}
}