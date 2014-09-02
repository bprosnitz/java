
// This file was auto-generated by the veyron vdl tool.
// Source: base.vdl
package com.veyron2.vdl.test_base;

/**
 * type NamedString string 
 **/
public final class NamedString implements android.os.Parcelable, java.io.Serializable {
    private java.lang.String value;

    public NamedString(java.lang.String value) {
        this.value = value;
    }
    public java.lang.String getValue() { return this.value; }

    public void setValue(java.lang.String value) { this.value = value; }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final com.veyron2.vdl.test_base.NamedString other = (com.veyron2.vdl.test_base.NamedString)obj;
        
        
        if (this.value == null) {
            return other.value == null;
        }
        return this.value.equals(other.value);
         
         
    }
    @Override
    public int hashCode() {
        return (value == null ? 0 : value.hashCode());
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
   		com.veyron2.vdl.ParcelUtil.writeValue(out, value);
    }
	public static final android.os.Parcelable.Creator<NamedString> CREATOR
		= new android.os.Parcelable.Creator<NamedString>() {
		@Override
		public NamedString createFromParcel(android.os.Parcel in) {
			return new NamedString(in);
		}
		@Override
		public NamedString[] newArray(int size) {
			return new NamedString[size];
		}
	};
	private NamedString(android.os.Parcel in) {
		value = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), value);
	}
}
