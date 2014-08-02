// This file was auto-generated by the veyron vdl tool.
// Source: wiretype.vdl
package com.veyron2.vom2;

/**
 * type WireArray struct{Name string;Elem veyron2/vom2.TypeID uint64;Len uint64} 
 * WireArray represents an array type definition.
 **/
public final class WireArray {
    
    
      private java.lang.String name;
    
      private com.veyron2.vom2.TypeID elem;
    
      private long len;
    

    
    public WireArray(final java.lang.String name, final com.veyron2.vom2.TypeID elem, final long len) {
        
            this.name = name;
        
            this.elem = elem;
        
            this.len = len;
        
    }

    
    
    public java.lang.String getName() {
        return this.name;
    }
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    public com.veyron2.vom2.TypeID getElem() {
        return this.elem;
    }
    public void setElem(com.veyron2.vom2.TypeID elem) {
        this.elem = elem;
    }
    
    public long getLen() {
        return this.len;
    }
    public void setLen(long len) {
        this.len = len;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final WireArray other = (WireArray)obj;

        
        
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
         
        
        
        if (this.elem == null) {
            if (other.elem != null) {
                return false;
            }
        } else if (!this.elem.equals(other.elem)) {
            return false;
        }
         
        
        
        if (this.len != other.len) {
            return false;
        }
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (name == null ? 0 : name.hashCode());
        
        result = prime * result + (elem == null ? 0 : elem.hashCode());
        
        result = prime * result + java.lang.Long.valueOf(len).hashCode();
        
        return result;
    }
}