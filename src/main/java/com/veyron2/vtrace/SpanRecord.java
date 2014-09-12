// This file was auto-generated by the veyron vdl tool.
// Source: types.vdl
package com.veyron2.vtrace;

/**
 * type SpanRecord struct{ID veyron2/uniqueid.ID [16]byte;Parent veyron2/uniqueid.ID;Name string;Annotations []veyron2/vtrace.Annotation struct{When int64;Message string}} 
 * A SpanRecord is the wire format for a Span.
 **/
public final class SpanRecord implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      @com.google.gson.annotations.SerializedName("ID")
      private com.veyron2.uniqueid.ID iD;
    
      @com.google.gson.annotations.SerializedName("Parent")
      private com.veyron2.uniqueid.ID parent;
    
      @com.google.gson.annotations.SerializedName("Name")
      private java.lang.String name;
    
      @com.google.gson.annotations.SerializedName("Annotations")
      private java.util.List<com.veyron2.vtrace.Annotation> annotations;
    

    
    public SpanRecord(final com.veyron2.uniqueid.ID iD, final com.veyron2.uniqueid.ID parent, final java.lang.String name, final java.util.List<com.veyron2.vtrace.Annotation> annotations) {
        
            this.iD = iD;
        
            this.parent = parent;
        
            this.name = name;
        
            this.annotations = annotations;
        
    }

    
    
    public com.veyron2.uniqueid.ID getID() {
        return this.iD;
    }
    public void setID(com.veyron2.uniqueid.ID iD) {
        this.iD = iD;
    }
    
    public com.veyron2.uniqueid.ID getParent() {
        return this.parent;
    }
    public void setParent(com.veyron2.uniqueid.ID parent) {
        this.parent = parent;
    }
    
    public java.lang.String getName() {
        return this.name;
    }
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    public java.util.List<com.veyron2.vtrace.Annotation> getAnnotations() {
        return this.annotations;
    }
    public void setAnnotations(java.util.List<com.veyron2.vtrace.Annotation> annotations) {
        this.annotations = annotations;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final SpanRecord other = (SpanRecord)obj;

        
        
        
        if (this.iD == null) {
            if (other.iD != null) {
                return false;
            }
        } else if (!this.iD.equals(other.iD)) {
            return false;
        }
         
         
        
        
        
        if (this.parent == null) {
            if (other.parent != null) {
                return false;
            }
        } else if (!this.parent.equals(other.parent)) {
            return false;
        }
         
         
        
        
        
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
         
         
        
        
        
        if (this.annotations == null) {
            if (other.annotations != null) {
                return false;
            }
        } else if (!this.annotations.equals(other.annotations)) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (iD == null ? 0 : iD.hashCode());
        
        result = prime * result + (parent == null ? 0 : parent.hashCode());
        
        result = prime * result + (name == null ? 0 : name.hashCode());
        
        result = prime * result + (annotations == null ? 0 : annotations.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, iD);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, parent);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, name);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, annotations);
    	
    }
	public static final android.os.Parcelable.Creator<SpanRecord> CREATOR
		= new android.os.Parcelable.Creator<SpanRecord>() {
		@Override
		public SpanRecord createFromParcel(android.os.Parcel in) {
			return new SpanRecord(in);
		}
		@Override
		public SpanRecord[] newArray(int size) {
			return new SpanRecord[size];
		}
	};
	private SpanRecord(android.os.Parcel in) {
		
			this.iD = (com.veyron2.uniqueid.ID) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.iD);
		
			this.parent = (com.veyron2.uniqueid.ID) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.parent);
		
			this.name = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.name);
		
			this.annotations = (java.util.List<com.veyron2.vtrace.Annotation>) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.annotations);
		
	}
}