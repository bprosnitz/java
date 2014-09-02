// This file was auto-generated by the veyron vdl tool.
// Source: base.vdl
package com.veyron2.vdl.test_base;

/**
 * type NestedArgs struct{Args veyron2/vdl/test_base.Args struct{A int32;B int32}} 
 * NestedArgs is defined before Args; that's allowed in regular Go, and also
 * allowed in our vdl files.  The compiler will re-order dependent types to ease
 * code generation in other languages.
 **/
public final class NestedArgs implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private com.veyron2.vdl.test_base.Args args;
    

    
    public NestedArgs(final com.veyron2.vdl.test_base.Args args) {
        
            this.args = args;
        
    }

    
    
    public com.veyron2.vdl.test_base.Args getArgs() {
        return this.args;
    }
    public void setArgs(com.veyron2.vdl.test_base.Args args) {
        this.args = args;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final NestedArgs other = (NestedArgs)obj;

        
        
        
        if (this.args == null) {
            if (other.args != null) {
                return false;
            }
        } else if (!this.args.equals(other.args)) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (args == null ? 0 : args.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, args);
    	
    }
	public static final android.os.Parcelable.Creator<NestedArgs> CREATOR
		= new android.os.Parcelable.Creator<NestedArgs>() {
		@Override
		public NestedArgs createFromParcel(android.os.Parcel in) {
			return new NestedArgs(in);
		}
		@Override
		public NestedArgs[] newArray(int size) {
			return new NestedArgs[size];
		}
	};
	private NestedArgs(android.os.Parcel in) {
		
			this.args = (com.veyron2.vdl.test_base.Args) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.args);
		
	}
}