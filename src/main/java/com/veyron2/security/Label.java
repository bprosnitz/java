
// This file was auto-generated by the veyron vdl tool.
// Source: types.vdl
package com.veyron2.security;

/**
 * type Label uint32 
 * Label is an access control right, like Read, Write, Admin, etc.
 **/
public final class Label {
    private int value;

    public Label(int value) {
        this.value = value;
    }
    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final com.veyron2.security.Label other = (com.veyron2.security.Label)obj;
        
        return this.value == other.value;
        
    }
    @Override
    public int hashCode() {
        return value;
    }
}