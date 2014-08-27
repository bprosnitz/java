// This file was auto-generated by the veyron vdl tool.
// Source: types.vdl
package com.veyron2.security;

/**
 * type Entries struct{Principals map[veyron2/security.BlessingPattern string]veyron2/security.LabelSet uint32} 
 * Entries describes a set of principals.
 **/
public final class Entries {
    
    
      private java.util.HashMap<com.veyron2.security.BlessingPattern, com.veyron2.security.LabelSet> principals;
    

    
    public Entries(final java.util.HashMap<com.veyron2.security.BlessingPattern, com.veyron2.security.LabelSet> principals) {
        
            this.principals = principals;
        
    }

    
    
    public java.util.HashMap<com.veyron2.security.BlessingPattern, com.veyron2.security.LabelSet> getPrincipals() {
        return this.principals;
    }
    public void setPrincipals(java.util.HashMap<com.veyron2.security.BlessingPattern, com.veyron2.security.LabelSet> principals) {
        this.principals = principals;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Entries other = (Entries)obj;

        
        
        if (this.principals == null) {
            if (other.principals != null) {
                return false;
            }
        } else if (!this.principals.equals(other.principals)) {
            return false;
        }
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (principals == null ? 0 : principals.hashCode());
        
        return result;
    }
}