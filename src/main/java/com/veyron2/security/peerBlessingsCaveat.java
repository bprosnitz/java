// This file was auto-generated by the veyron vdl tool.
// Source: caveat.vdl
package com.veyron2.security;

/**
 * type peerBlessingsCaveat []veyron2/security.BlessingPattern string 
 * peerBlessingsCaveat represents a caveat that validates iff the peer being
 * communicated with presents a blessing that matches one of the patterns
 * included in this list.
 **/
public final class peerBlessingsCaveat implements java.util.List<com.veyron2.security.BlessingPattern>, android.os.Parcelable, java.io.Serializable {
    static final long serialVersionUID = 0L;

    private java.util.List<com.veyron2.security.BlessingPattern> impl;

    public peerBlessingsCaveat(java.util.List<com.veyron2.security.BlessingPattern> impl) {
        this.impl = impl;
    }
    public java.util.List<com.veyron2.security.BlessingPattern> getValue() { return this.impl; }

    public void setValue(java.util.List<com.veyron2.security.BlessingPattern> newImpl) {
        this.impl = newImpl;
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        final peerBlessingsCaveat other = (peerBlessingsCaveat)obj;
        if (!(this.impl.equals(other.impl))) return false;
        return true;
    }
    @Override
    public int hashCode() {
        return (impl == null ? 0 : impl.hashCode());
    }
    @Override
    public void add(int location, com.veyron2.security.BlessingPattern object) {
        impl.add(location, object);
    }
    @Override
    public boolean add(com.veyron2.security.BlessingPattern object) {
        return impl.add(object);
    }
    @Override
    public boolean addAll(int location, java.util.Collection<? extends com.veyron2.security.BlessingPattern> collection) {
        return impl.addAll(location, collection);
    }
    @Override
    public boolean addAll(java.util.Collection<? extends com.veyron2.security.BlessingPattern> collection) {
        return impl.addAll(collection);
    }
    @Override
    public void clear() {
        impl.clear();
    }
    @Override
    public boolean contains(java.lang.Object object) {
        return impl.contains(object);
    }
    @Override
    public boolean containsAll(java.util.Collection<?> collection) {
        return impl.containsAll(collection);
    }
    @Override
    public com.veyron2.security.BlessingPattern get(int location) {
        return impl.get(location);
    }
    @Override
    public int indexOf(java.lang.Object object) {
        return impl.indexOf(object);
    }
    @Override
    public boolean isEmpty() {
        return impl.isEmpty();
    }
    @Override
    public java.util.Iterator<com.veyron2.security.BlessingPattern> iterator() {
        return impl.iterator();
    }
    @Override
    public int lastIndexOf(java.lang.Object object) {
        return impl.lastIndexOf(object);
    }
    @Override
    public java.util.ListIterator<com.veyron2.security.BlessingPattern> listIterator() {
        return impl.listIterator();
    }
    @Override
    public java.util.ListIterator<com.veyron2.security.BlessingPattern> listIterator(int location) {
        return impl.listIterator(location);
    }
    @Override
    public com.veyron2.security.BlessingPattern remove(int location) {
        return impl.remove(location);
    }
    @Override
    public boolean remove(java.lang.Object object) {
        return impl.remove(object);
    }
    @Override
    public boolean removeAll(java.util.Collection<?> collection) {
        return impl.removeAll(collection);
    }
    @Override
    public boolean retainAll(java.util.Collection<?> collection) {
        return impl.retainAll(collection);
    }
    @Override
    public com.veyron2.security.BlessingPattern set(int location, com.veyron2.security.BlessingPattern object) {
        return impl.set(location, object);
    }
    @Override
    public int size() {
        return impl.size();
    }
    @Override
    public java.util.List<com.veyron2.security.BlessingPattern> subList(int start, int end) {
        return impl.subList(start, end);
    }
    @Override
    public java.lang.Object[] toArray() {
        return impl.toArray();
    }
    @Override
    public <T> T[] toArray(T[] array) {
        return impl.toArray(array);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(android.os.Parcel out, int flags) {
        com.veyron2.vdl.ParcelUtil.writeValue(out, impl);
    }
    public static final android.os.Parcelable.Creator<peerBlessingsCaveat> CREATOR = new android.os.Parcelable.Creator<peerBlessingsCaveat>() {
        @Override
        public peerBlessingsCaveat createFromParcel(android.os.Parcel in) {
            return new peerBlessingsCaveat(in);
        }
        @Override
        public peerBlessingsCaveat[] newArray(int size) {
            return new peerBlessingsCaveat[size];
        }
    };
    private peerBlessingsCaveat(android.os.Parcel in) {
        impl = (java.util.List<com.veyron2.security.BlessingPattern>) com.veyron2.vdl.ParcelUtil.readValue(in, getClass().getClassLoader(), impl);
    }
}