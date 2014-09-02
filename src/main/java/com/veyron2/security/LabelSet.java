
// This file was auto-generated by the veyron vdl tool.
// Source: types.vdl
package com.veyron2.security;

/**
 * type LabelSet veyron2/security.Label uint32 
 * LabelSet is a set of access control labels, represented as a bitmask.
 **/
public final class LabelSet implements android.os.Parcelable, java.io.Serializable {
    private com.veyron2.security.Label value;

    public LabelSet(com.veyron2.security.Label value) {
        this.value = value;
    }
    public com.veyron2.security.Label getValue() { return this.value; }

    public void setValue(com.veyron2.security.Label value) { this.value = value; }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final com.veyron2.security.LabelSet other = (com.veyron2.security.LabelSet)obj;
        
        
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
	public static final android.os.Parcelable.Creator<LabelSet> CREATOR
		= new android.os.Parcelable.Creator<LabelSet>() {
		@Override
		public LabelSet createFromParcel(android.os.Parcel in) {
			return new LabelSet(in);
		}
		@Override
		public LabelSet[] newArray(int size) {
			return new LabelSet[size];
		}
	};
	private LabelSet(android.os.Parcel in) {
		value = (com.veyron2.security.Label) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), value);
	}
}
