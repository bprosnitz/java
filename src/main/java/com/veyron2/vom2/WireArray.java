// This file was auto-generated by the veyron vdl tool.
// Source: wiretype.vdl
package com.veyron2.vom2;

/**
 * type WireArray struct{Name string;Elem veyron2/vom2.TypeID uint64;Len uint64} 
 * WireArray represents an array type definition.
 **/
public final class WireArray implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private java.lang.String name;
    
      private com.veyron2.vom2.TypeID elem;
    
      private long len;
    

    
    public WireArray(final java.lang.String name, final com.veyron2.vom2.TypeID elem, final long len) {
        
            this.name = name;
        
            this.elem = elem;
        
            this.len = len;
        
    }

    
    
    public java.lang.String getName() {
        return this.name;
    }
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    public com.veyron2.vom2.TypeID getElem() {
        return this.elem;
    }
    public void setElem(com.veyron2.vom2.TypeID elem) {
        this.elem = elem;
    }
    
    public long getLen() {
        return this.len;
    }
    public void setLen(long len) {
        this.len = len;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final WireArray other = (WireArray)obj;

        
        
        
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
         
         
        
        
        
        if (this.elem == null) {
            if (other.elem != null) {
                return false;
            }
        } else if (!this.elem.equals(other.elem)) {
            return false;
        }
         
         
        
        
        
        if (this.len != other.len) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (name == null ? 0 : name.hashCode());
        
        result = prime * result + (elem == null ? 0 : elem.hashCode());
        
        result = prime * result + java.lang.Long.valueOf(len).hashCode();
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, name);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, elem);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, len);
    	
    }
	public static final android.os.Parcelable.Creator<WireArray> CREATOR
		= new android.os.Parcelable.Creator<WireArray>() {
		@Override
		public WireArray createFromParcel(android.os.Parcel in) {
			return new WireArray(in);
		}
		@Override
		public WireArray[] newArray(int size) {
			return new WireArray[size];
		}
	};
	private WireArray(android.os.Parcel in) {
		
			this.name = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.name);
		
			this.elem = (com.veyron2.vom2.TypeID) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.elem);
		
			this.len = (long) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.len);
		
	}
}