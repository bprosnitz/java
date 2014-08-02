// This file was auto-generated by the veyron vdl tool.
// Source(s): types.vdl
package com.veyron2.storage;


public final class StorageConstants {
    

    /* The following constants originate in file: types.vdl */
    
    
    /**
 * RemoveACL means to remove the ACL from any inherited ACLs.
 */

    public static final com.veyron2.storage.TagOp REMOVE_ACL = new com.veyron2.storage.TagOp((byte)0);
    
    /**
 * AddACL means to add the ACL for a single value; it is not inherited.
 */

    public static final com.veyron2.storage.TagOp ADD_ACL = new com.veyron2.storage.TagOp((byte)1);
    
    /**
 * AddInheritedACL means to add the ACL for the value and all of its
 * descendents.
 */

    public static final com.veyron2.storage.TagOp ADD_INHERITED_ACL = new com.veyron2.storage.TagOp((byte)2);
    
    /**
 * NoVersion means the entry is not present in the store.
 */

    public static final com.veyron2.storage.Version NO_VERSION = new com.veyron2.storage.Version(0L);
    
    
    

    
}