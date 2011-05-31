package com.perso.baas.shared;

/**
 *
 * @param <K> Key of the object
 */
public interface BaasPojo<K> {
	public K getKey();
	public void setKey(K key);
}
