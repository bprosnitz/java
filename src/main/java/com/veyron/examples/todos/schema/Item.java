// This file was auto-generated by the veyron vdl tool.
// Source: schema.vdl
package com.veyron.examples.todos.schema;

/**
 * type Item struct{Text string;Done bool;Tags []string} 
 * Item is a single task to be done.
 **/
public final class Item implements android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    
    
      private java.lang.String text;
    
      private boolean done;
    
      private java.util.List<java.lang.String> tags;
    

    
    public Item(final java.lang.String text, final boolean done, final java.util.List<java.lang.String> tags) {
        
            this.text = text;
        
            this.done = done;
        
            this.tags = tags;
        
    }

    
    
    public java.lang.String getText() {
        return this.text;
    }
    public void setText(java.lang.String text) {
        this.text = text;
    }
    
    public boolean getDone() {
        return this.done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    
    public java.util.List<java.lang.String> getTags() {
        return this.tags;
    }
    public void setTags(java.util.List<java.lang.String> tags) {
        this.tags = tags;
    }
    

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final Item other = (Item)obj;

        
        
        
        if (this.text == null) {
            if (other.text != null) {
                return false;
            }
        } else if (!this.text.equals(other.text)) {
            return false;
        }
         
         
        
        
        
        if (this.done != other.done) {
            return false;
        }
         
         
        
        
        
        if (this.tags == null) {
            if (other.tags != null) {
                return false;
            }
        } else if (!this.tags.equals(other.tags)) {
            return false;
        }
         
         
         
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        
        result = prime * result + (text == null ? 0 : text.hashCode());
        
        result = prime * result + java.lang.Boolean.valueOf(done).hashCode();
        
        result = prime * result + (tags == null ? 0 : tags.hashCode());
        
        return result;
    }
    @Override
    public int describeContents() {
    	return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, text);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, done);
    	
    		com.veyron2.vdl.ParcelUtil.writeValue(out, tags);
    	
    }
	public static final android.os.Parcelable.Creator<Item> CREATOR
		= new android.os.Parcelable.Creator<Item>() {
		@Override
		public Item createFromParcel(android.os.Parcel in) {
			return new Item(in);
		}
		@Override
		public Item[] newArray(int size) {
			return new Item[size];
		}
	};
	private Item(android.os.Parcel in) {
		
			this.text = (java.lang.String) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.text);
		
			this.done = (boolean) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.done);
		
			this.tags = (java.util.List<java.lang.String>) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), this.tags);
		
	}
}