// This file was auto-generated by the veyron vdl tool.
// Source: service.vdl
package com.veyron2.services.store;

/**
 * type Conflict struct{Ty string;ID veyron2/storage.ID [16]byte;Local veyron2/services/store.Entry struct{Stat veyron2/services/store.Stat struct{ID veyron2/storage.ID;MTimeNS int64;Attrs []any};Value any};Remote veyron2/services/store.Entry;Root veyron2/services/store.Entry} 
 * Conflict represents a conflicting update in the store.
 **/
public final class Conflict {
    
    
      private java.lang.String ty;
    
      private com.veyron2.storage.ID iD;
    
      private com.veyron2.services.store.Entry local;
    
      private com.veyron2.services.store.Entry remote;
    
      private com.veyron2.services.store.Entry root;
    

    
    public Conflict(final java.lang.String ty, final com.veyron2.storage.ID iD, final com.veyron2.services.store.Entry local, final com.veyron2.services.store.Entry remote, final com.veyron2.services.store.Entry root) {
        
            this.ty = ty;
        
            this.iD = iD;
        
            this.local = local;
        
            this.remote = remote;
        
            this.root = root;
        
    }

    
    
    public java.lang.String getTy() {
        return this.ty;
    }
    public void setTy(java.lang.String ty) {
        this.ty = ty;
    }
    
    public com.veyron2.storage.ID getID() {
        return this.iD;
    }
    public void setID(com.veyron2.storage.ID iD) {
        this.iD = iD;
    }
    
    public com.veyron2.services.store.Entry getLocal() {
        return this.local;
    }
    public void setLocal(com.veyron2.services.store.Entry local) {
        this.local = local;
    }
    
    public com.veyron2.services.store.Entry getRemote() {
        return this.remote;
    }
    public void setRemote(com.veyron2.services.store.Entry remote) {
        this.remote = remote;
    }
    
    public com.veyron2.services.store.Entry getRoot() {
        return this.root;
    }
    public void setRoot(com.veyron2.services.store.Entry root) {
        this.root = root;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Conflict other = (Conflict)obj;

        
        
        if (this.ty == null) {
            if (other.ty != null) {
                return false;
            }
        } else if (!this.ty.equals(other.ty)) {
            return false;
        }
         
        
        
        if (this.iD == null) {
            if (other.iD != null) {
                return false;
            }
        } else if (!this.iD.equals(other.iD)) {
            return false;
        }
         
        
        
        if (this.local == null) {
            if (other.local != null) {
                return false;
            }
        } else if (!this.local.equals(other.local)) {
            return false;
        }
         
        
        
        if (this.remote == null) {
            if (other.remote != null) {
                return false;
            }
        } else if (!this.remote.equals(other.remote)) {
            return false;
        }
         
        
        
        if (this.root == null) {
            if (other.root != null) {
                return false;
            }
        } else if (!this.root.equals(other.root)) {
            return false;
        }
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (ty == null ? 0 : ty.hashCode());
        
        result = prime * result + (iD == null ? 0 : iD.hashCode());
        
        result = prime * result + (local == null ? 0 : local.hashCode());
        
        result = prime * result + (remote == null ? 0 : remote.hashCode());
        
        result = prime * result + (root == null ? 0 : root.hashCode());
        
        return result;
    }
}