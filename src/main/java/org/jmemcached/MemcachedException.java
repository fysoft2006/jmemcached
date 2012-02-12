package org.jmemcached;

public class MemcachedException extends RuntimeException {
	private static final long serialVersionUID = -8559300945417059496L;

	public MemcachedException(Throwable cause) {
		super(cause);
	}

	public MemcachedException(String message) {
		super(message);
	}

	public MemcachedException(String message, Throwable cause) {
		super(message, cause);
	}
}
