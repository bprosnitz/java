package io.v.v23.vdl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * VdlEnum is a representation of a VDL enum.
 */
public class VdlEnum extends VdlValue implements Parcelable {
    private final String name;
    private final int ordinal;

    public VdlEnum(VdlType type, String name) {
        super(type);
        assertKind(Kind.ENUM);
        this.ordinal = type.getLabels().indexOf(name);
        if (this.ordinal == -1) {
            throw new IllegalArgumentException("Undeclared enum label " + name);
        }
        this.name = name;
    }

    public String name() {
        return name;
    }

    public int ordinal() {
        return ordinal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof VdlEnum)) return false;
        final VdlEnum other = (VdlEnum) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(this);
    }

    public static final Creator<VdlEnum> CREATOR = new Creator<VdlEnum>() {
        @Override
        public VdlEnum createFromParcel(Parcel in) {
            return (VdlEnum) in.readSerializable();
        }

        @Override
        public VdlEnum[] newArray(int size) {
            return new VdlEnum[size];
        }
    };
}