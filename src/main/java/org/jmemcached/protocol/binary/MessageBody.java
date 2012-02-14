package org.jmemcached.protocol.binary;

import java.nio.ByteBuffer;

public final class MessageBody {
	private final Key key;
	private final ByteBuffer valueData;

	public MessageBody(ByteBuffer data, int keyLength) {
		key = new Key(readKey(data, keyLength));
		valueData = data.slice();
	}

	public Key getKey() {
		return key;
	}

	public void writeValueTo(ByteBuffer buffer) {
		buffer.put(valueData.duplicate());
	}

	private static byte[] readKey(ByteBuffer data, int keyLength) {
		return BufferUtils.readBytes(data, keyLength);
	}
}                                                 
