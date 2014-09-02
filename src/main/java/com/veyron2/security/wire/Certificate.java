// This file was auto-generated by the veyron vdl tool.
// Source: wire.vdl
package com.veyron2.security.wire;

/**
 * type Certificate struct{Name string;PublicKey veyron2/security/wire.PublicKey struct{Curve veyron2/security/wire.KeyCurve byte;XY []byte};Caveats []veyron2/security/wire.Caveat struct{Service veyron2/security.BlessingPattern string;Bytes []byte};Signature veyron2/security.Signature struct{Hash veyron2/security.Hash string;R []byte;S []byte}} 
 * Certificate is a signed assertion binding a name to a public key under a certain set
 * of caveats. The issuer of a Certificate is the principal that possesses the private key
 * under which the Certificate was signed. The Certificate's signature is over the contents
 * of the Certificate along with the Signature of the issuer.
 **/
public final class Certificate implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private java.lang.String name;
    
      private com.veyron2.security.wire.PublicKey publicKey;
    
      private java.util.List<com.veyron2.security.wire.Caveat> caveats;
    
      private com.veyron2.security.Signature signature;
    

    
    public Certificate(final java.lang.String name, final com.veyron2.security.wire.PublicKey publicKey, final java.util.List<com.veyron2.security.wire.Caveat> caveats, final com.veyron2.security.Signature signature) {
        
            this.name = name;
        
            this.publicKey = publicKey;
        
            this.caveats = caveats;
        
            this.signature = signature;
        
    }

    
    
    public java.lang.String getName() {
        return this.name;
    }
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    public com.veyron2.security.wire.PublicKey getPublicKey() {
        return this.publicKey;
    }
    public void setPublicKey(com.veyron2.security.wire.PublicKey publicKey) {
        this.publicKey = publicKey;
    }
    
    public java.util.List<com.veyron2.security.wire.Caveat> getCaveats() {
        return this.caveats;
    }
    public void setCaveats(java.util.List<com.veyron2.security.wire.Caveat> caveats) {
        this.caveats = caveats;
    }
    
    public com.veyron2.security.Signature getSignature() {
        return this.signature;
    }
    public void setSignature(com.veyron2.security.Signature signature) {
        this.signature = signature;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Certificate other = (Certificate)obj;

        
        
        
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
         
         
        
        
        
        if (this.publicKey == null) {
            if (other.publicKey != null) {
                return false;
            }
        } else if (!this.publicKey.equals(other.publicKey)) {
            return false;
        }
         
         
        
        
        
        if (this.caveats == null) {
            if (other.caveats != null) {
                return false;
            }
        } else if (!this.caveats.equals(other.caveats)) {
            return false;
        }
         
         
        
        
        
        if (this.signature == null) {
            if (other.signature != null) {
                return false;
            }
        } else if (!this.signature.equals(other.signature)) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (name == null ? 0 : name.hashCode());
        
        result = prime * result + (publicKey == null ? 0 : publicKey.hashCode());
        
        result = prime * result + (caveats == null ? 0 : caveats.hashCode());
        
        result = prime * result + (signature == null ? 0 : signature.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, name);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, publicKey);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, caveats);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, signature);
    	
    }
	public static final android.os.Parcelable.Creator<Certificate> CREATOR
		= new android.os.Parcelable.Creator<Certificate>() {
		@Override
		public Certificate createFromParcel(android.os.Parcel in) {
			return new Certificate(in);
		}
		@Override
		public Certificate[] newArray(int size) {
			return new Certificate[size];
		}
	};
	private Certificate(android.os.Parcel in) {
		
			this.name = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.name);
		
			this.publicKey = (com.veyron2.security.wire.PublicKey) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.publicKey);
		
			this.caveats = (java.util.List<com.veyron2.security.wire.Caveat>) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.caveats);
		
			this.signature = (com.veyron2.security.Signature) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.signature);
		
	}
}