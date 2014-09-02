// This file was auto-generated by the veyron vdl tool.
// Source: wiretype.vdl
package com.veyron2.vom2;

/**
 * type WireSet struct{Name string;Key veyron2/vom2.TypeID uint64} 
 * WireSet represents a set type definition.
 **/
public final class WireSet implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private java.lang.String name;
    
      private com.veyron2.vom2.TypeID key;
    

    
    public WireSet(final java.lang.String name, final com.veyron2.vom2.TypeID key) {
        
            this.name = name;
        
            this.key = key;
        
    }

    
    
    public java.lang.String getName() {
        return this.name;
    }
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    public com.veyron2.vom2.TypeID getKey() {
        return this.key;
    }
    public void setKey(com.veyron2.vom2.TypeID key) {
        this.key = key;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final WireSet other = (WireSet)obj;

        
        
        
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
         
         
        
        
        
        if (this.key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!this.key.equals(other.key)) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (name == null ? 0 : name.hashCode());
        
        result = prime * result + (key == null ? 0 : key.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, name);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, key);
    	
    }
	public static final android.os.Parcelable.Creator<WireSet> CREATOR
		= new android.os.Parcelable.Creator<WireSet>() {
		@Override
		public WireSet createFromParcel(android.os.Parcel in) {
			return new WireSet(in);
		}
		@Override
		public WireSet[] newArray(int size) {
			return new WireSet[size];
		}
	};
	private WireSet(android.os.Parcel in) {
		
			this.name = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.name);
		
			this.key = (com.veyron2.vom2.TypeID) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.key);
		
	}
}