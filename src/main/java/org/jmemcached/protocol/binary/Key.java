package org.jmemcached.protocol.binary;

import java.util.Arrays;

public final class Key {
	private final byte[] data;

	public Key(byte[] data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Key key = (Key) o;
		return Arrays.equals(data, key.data);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(data);
	}
}
