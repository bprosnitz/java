package io.v.v23.vdl;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * VdlAny is a representation of a VDL any.
 */
public final class VdlAny extends VdlValue implements Parcelable {
    private final Serializable elem;
    private final VdlType elemType;

    public VdlAny(VdlType vdlType, Serializable value) {
        super(Types.ANY);
        elem = value;
        elemType = vdlType;
    }

    public VdlAny(Type type, Serializable value) {
        this(Types.getVdlTypeFromReflect(type), value);
    }

    public VdlAny(VdlValue value) {
        this(value.vdlType(), value);
    }

    public VdlAny() {
        this((VdlType) null, null);
    }

    public Serializable getElem() {
        return elem;
    }

    public VdlType getElemType() {
        return elemType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final VdlAny other = (VdlAny) obj;
        return elem == null ? other.elem == null : elem.equals(other.elem);
    }

    @Override
    public int hashCode() {
        return elem == null ? 0 : elem.hashCode();
    }

    @Override
    public String toString() {
        return elem == null ? null : elem.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(this);
    }

    public static final Creator<VdlAny> CREATOR = new Creator<VdlAny>() {
        @Override
        public VdlAny createFromParcel(Parcel in) {
            return (VdlAny) in.readSerializable();
        }

        @Override
        public VdlAny[] newArray(int size) {
            return new VdlAny[size];
        }
    };
}