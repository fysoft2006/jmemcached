package org.jmemcached.protocol.binary;

import java.nio.ByteBuffer;

public class BufferUtils {
	private BufferUtils() {
	}

	public static byte[] readBytes(ByteBuffer buffer, int length) {
		byte[] bytes = new byte[length];
		buffer.get(bytes);
		return bytes;
	}
}
