// This file was auto-generated by the veyron vdl tool.
// Source: base.vdl
package com.veyron2.vdl.test_base;

/**
 * type NamedStruct struct{A bool;B string;C int32} 
 **/
public final class NamedStruct implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private boolean a;
    
      private java.lang.String b;
    
      private int c;
    

    
    public NamedStruct(final boolean a, final java.lang.String b, final int c) {
        
            this.a = a;
        
            this.b = b;
        
            this.c = c;
        
    }

    
    
    public boolean getA() {
        return this.a;
    }
    public void setA(boolean a) {
        this.a = a;
    }
    
    public java.lang.String getB() {
        return this.b;
    }
    public void setB(java.lang.String b) {
        this.b = b;
    }
    
    public int getC() {
        return this.c;
    }
    public void setC(int c) {
        this.c = c;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final NamedStruct other = (NamedStruct)obj;

        
        
        
        if (this.a != other.a) {
            return false;
        }
         
         
        
        
        
        if (this.b == null) {
            if (other.b != null) {
                return false;
            }
        } else if (!this.b.equals(other.b)) {
            return false;
        }
         
         
        
        
        
        if (this.c != other.c) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + java.lang.Boolean.valueOf(a).hashCode();
        
        result = prime * result + (b == null ? 0 : b.hashCode());
        
        result = prime * result + c;
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, a);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, b);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, c);
    	
    }
	public static final android.os.Parcelable.Creator<NamedStruct> CREATOR
		= new android.os.Parcelable.Creator<NamedStruct>() {
		@Override
		public NamedStruct createFromParcel(android.os.Parcel in) {
			return new NamedStruct(in);
		}
		@Override
		public NamedStruct[] newArray(int size) {
			return new NamedStruct[size];
		}
	};
	private NamedStruct(android.os.Parcel in) {
		
			this.a = (boolean) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.a);
		
			this.b = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.b);
		
			this.c = (int) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.c);
		
	}
}