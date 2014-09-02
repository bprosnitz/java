// This file was auto-generated by the veyron vdl tool.
// Source: types.vdl
package com.veyron.services.mgmt.stats;

/**
 * type HistogramBucket struct{LowBound int64;Count int64} 
 * HistogramBucket is one histogram bucket.
 **/
public final class HistogramBucket implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private long lowBound;
    
      private long count;
    

    
    public HistogramBucket(final long lowBound, final long count) {
        
            this.lowBound = lowBound;
        
            this.count = count;
        
    }

    
    
    public long getLowBound() {
        return this.lowBound;
    }
    public void setLowBound(long lowBound) {
        this.lowBound = lowBound;
    }
    
    public long getCount() {
        return this.count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final HistogramBucket other = (HistogramBucket)obj;

        
        
        
        if (this.lowBound != other.lowBound) {
            return false;
        }
         
         
        
        
        
        if (this.count != other.count) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + java.lang.Long.valueOf(lowBound).hashCode();
        
        result = prime * result + java.lang.Long.valueOf(count).hashCode();
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, lowBound);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, count);
    	
    }
	public static final android.os.Parcelable.Creator<HistogramBucket> CREATOR
		= new android.os.Parcelable.Creator<HistogramBucket>() {
		@Override
		public HistogramBucket createFromParcel(android.os.Parcel in) {
			return new HistogramBucket(in);
		}
		@Override
		public HistogramBucket[] newArray(int size) {
			return new HistogramBucket[size];
		}
	};
	private HistogramBucket(android.os.Parcel in) {
		
			this.lowBound = (long) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.lowBound);
		
			this.count = (long) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.count);
		
	}
}