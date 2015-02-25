package io.v.v23.vdl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * VdlInt16 is a representation of a VDL int16.
 */
public class VdlInt16 extends VdlValue implements Parcelable {
    private final short value;

    public VdlInt16(VdlType type, short value) {
        super(type);
        assertKind(Kind.INT16);
        this.value = value;
    }

    public VdlInt16(short value) {
        this(Types.INT16, value);
    }

    public short getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof VdlInt16)) return false;
        final VdlInt16 other = (VdlInt16) obj;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Short.toString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(value);
    }

    public static final Creator<VdlInt16> CREATOR = new Creator<VdlInt16>() {
        @Override
        public VdlInt16 createFromParcel(Parcel in) {
            return new VdlInt16((short) in.readInt());
        }

        @Override
        public VdlInt16[] newArray(int size) {
            return new VdlInt16[size];
        }
    };

    protected VdlInt16(VdlType type) {
        this(type, (short) 0);
    }

    public VdlInt16() {
        this((short) 0);
    }
}