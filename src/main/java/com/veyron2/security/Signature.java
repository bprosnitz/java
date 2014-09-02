// This file was auto-generated by the veyron vdl tool.
// Source: types.vdl
package com.veyron2.security;

/**
 * type Signature struct{Hash veyron2/security.Hash string;R []byte;S []byte} 
 * Signature represents an ECDSA signature.
 **/
public final class Signature implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private com.veyron2.security.Hash hash;
    
      private byte[] r;
    
      private byte[] s;
    

    
    public Signature(final com.veyron2.security.Hash hash, final byte[] r, final byte[] s) {
        
            this.hash = hash;
        
            this.r = r;
        
            this.s = s;
        
    }

    
    
    public com.veyron2.security.Hash getHash() {
        return this.hash;
    }
    public void setHash(com.veyron2.security.Hash hash) {
        this.hash = hash;
    }
    
    public byte[] getR() {
        return this.r;
    }
    public void setR(byte[] r) {
        this.r = r;
    }
    
    public byte[] getS() {
        return this.s;
    }
    public void setS(byte[] s) {
        this.s = s;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Signature other = (Signature)obj;

        
        
        
        if (this.hash == null) {
            if (other.hash != null) {
                return false;
            }
        } else if (!this.hash.equals(other.hash)) {
            return false;
        }
         
         
        
        
        if (!java.util.Arrays.equals(this.r, other.r)) {
            return false;
        }
         
        
        
        if (!java.util.Arrays.equals(this.s, other.s)) {
            return false;
        }
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (hash == null ? 0 : hash.hashCode());
        
        result = prime * result + (r == null ? 0 : r.hashCode());
        
        result = prime * result + (s == null ? 0 : s.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, hash);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, r);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, s);
    	
    }
	public static final android.os.Parcelable.Creator<Signature> CREATOR
		= new android.os.Parcelable.Creator<Signature>() {
		@Override
		public Signature createFromParcel(android.os.Parcel in) {
			return new Signature(in);
		}
		@Override
		public Signature[] newArray(int size) {
			return new Signature[size];
		}
	};
	private Signature(android.os.Parcel in) {
		
			this.hash = (com.veyron2.security.Hash) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.hash);
		
			this.r = (byte[]) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.r);
		
			this.s = (byte[]) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.s);
		
	}
}