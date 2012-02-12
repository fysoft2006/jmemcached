package org.jmemcached.network;

import org.jmemcached.MemcachedException;

public class NetworkException extends MemcachedException {
	private static final long serialVersionUID = -8523307282226895626L;

	public NetworkException(Throwable cause) {
		super(cause);
	}

	public NetworkException(String message) {
		super(message);
	}

	public NetworkException(String message, Throwable cause) {
		super(message, cause);
	}
}
