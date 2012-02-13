package org.jmemcached.network;

public class DataException extends NetworkException {
	private static final long serialVersionUID = -1951325934693587672L;

	public DataException(Throwable cause) {
		super(cause);
	}

	public DataException(String message) {
		super(message);
	}

	public DataException(String message, Throwable cause) {
		super(message, cause);
	}
}
