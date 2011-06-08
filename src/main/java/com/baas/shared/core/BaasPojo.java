package com.baas.shared.core;

import java.io.Serializable;

/**
 *
 * @param <K> Key of the object
 */
public interface BaasPojo<K> extends Serializable{
	public K getKey();
	public void setKey(K key);
}
