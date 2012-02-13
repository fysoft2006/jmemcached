package org.jmemcached.protocol.binary;

import java.nio.ByteBuffer;

public final class MessageBody {
	private final byte[] key;
	private final ByteBuffer valueData;

	public MessageBody(ByteBuffer data, int keyLength) {
		key = readKey(data, keyLength);
		valueData = data.slice();
	}

	public byte[] getKey() {
		return key;
	}

	public void writeValueTo(ByteBuffer buffer) {
		buffer.put(valueData.duplicate());
	}

	private static byte[] readKey(ByteBuffer data, int keyLength) {
		return BufferUtils.readBytes(data, keyLength);
	}
}                                                 
