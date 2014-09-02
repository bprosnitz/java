
// This file was auto-generated by the veyron vdl tool.
// Source: wiretype.vdl
package com.veyron2.wiretype;

/**
 * type TypeID uint64 
 * TypeID serves as a reference to a type definition.  The TypeID is only unique
 * within a single Encoder / Decoder stream; different streams may use different
 * TypeIDs to represent the same types.
 **/
public final class TypeID implements android.os.Parcelable, java.io.Serializable {
    private long value;

    public TypeID(long value) {
        this.value = value;
    }
    public long getValue() { return this.value; }

    public void setValue(long value) { this.value = value; }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final com.veyron2.wiretype.TypeID other = (com.veyron2.wiretype.TypeID)obj;
        
        
        return this.value == other.value;
         
         
    }
    @Override
    public int hashCode() {
        return java.lang.Long.valueOf(value).hashCode();
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
   		com.veyron2.vdl.ParcelUtil.writeValue(out, value);
    }
	public static final android.os.Parcelable.Creator<TypeID> CREATOR
		= new android.os.Parcelable.Creator<TypeID>() {
		@Override
		public TypeID createFromParcel(android.os.Parcel in) {
			return new TypeID(in);
		}
		@Override
		public TypeID[] newArray(int size) {
			return new TypeID[size];
		}
	};
	private TypeID(android.os.Parcel in) {
		value = (long) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), value);
	}
}
