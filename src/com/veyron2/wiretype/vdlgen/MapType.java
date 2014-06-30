
// This file was auto-generated by the veyron vdl tool.
// Source: wiretype.vdl
package com.veyron2.wiretype.vdlgen;

import java.util.ArrayList;

/**
 * MapType represents an unordered collection of Key,Elem values, where each Key
 * is unique.  Go has a single canonical map, but other languages may use the
 * tag to indicate the representation; e.g. C++ may use tags "rbtree" and
 * "hashmap" to indicate std::map and std::unordered_map respectively.
**/
public class MapType { 
	public long key;
	public long elem;
	public String name;
	public ArrayList<String> tags;
}