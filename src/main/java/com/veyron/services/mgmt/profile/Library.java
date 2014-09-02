// This file was auto-generated by the veyron vdl tool.
// Source: profile.vdl
package com.veyron.services.mgmt.profile;

/**
 * type Library struct{Name string;MajorVersion string;MinorVersion string} 
 * Library describes a shared library that applications may use.
 **/
public final class Library implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private java.lang.String name;
    
      private java.lang.String majorVersion;
    
      private java.lang.String minorVersion;
    

    
    public Library(final java.lang.String name, final java.lang.String majorVersion, final java.lang.String minorVersion) {
        
            this.name = name;
        
            this.majorVersion = majorVersion;
        
            this.minorVersion = minorVersion;
        
    }

    
    
    public java.lang.String getName() {
        return this.name;
    }
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    public java.lang.String getMajorVersion() {
        return this.majorVersion;
    }
    public void setMajorVersion(java.lang.String majorVersion) {
        this.majorVersion = majorVersion;
    }
    
    public java.lang.String getMinorVersion() {
        return this.minorVersion;
    }
    public void setMinorVersion(java.lang.String minorVersion) {
        this.minorVersion = minorVersion;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Library other = (Library)obj;

        
        
        
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
         
         
        
        
        
        if (this.majorVersion == null) {
            if (other.majorVersion != null) {
                return false;
            }
        } else if (!this.majorVersion.equals(other.majorVersion)) {
            return false;
        }
         
         
        
        
        
        if (this.minorVersion == null) {
            if (other.minorVersion != null) {
                return false;
            }
        } else if (!this.minorVersion.equals(other.minorVersion)) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (name == null ? 0 : name.hashCode());
        
        result = prime * result + (majorVersion == null ? 0 : majorVersion.hashCode());
        
        result = prime * result + (minorVersion == null ? 0 : minorVersion.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, name);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, majorVersion);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, minorVersion);
    	
    }
	public static final android.os.Parcelable.Creator<Library> CREATOR
		= new android.os.Parcelable.Creator<Library>() {
		@Override
		public Library createFromParcel(android.os.Parcel in) {
			return new Library(in);
		}
		@Override
		public Library[] newArray(int size) {
			return new Library[size];
		}
	};
	private Library(android.os.Parcel in) {
		
			this.name = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.name);
		
			this.majorVersion = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.majorVersion);
		
			this.minorVersion = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.minorVersion);
		
	}
}