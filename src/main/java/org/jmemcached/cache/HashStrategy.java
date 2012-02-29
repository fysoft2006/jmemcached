package org.jmemcached.cache;

public interface HashStrategy {
	int hash(byte[] data);
}
