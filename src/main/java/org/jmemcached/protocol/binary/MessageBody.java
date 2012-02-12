package org.jmemcached.protocol.binary;

import java.nio.ByteBuffer;

public final class MessageBody {
    private final ByteBuffer data;
    private final int keyLength;
    private final int valueLength;

    public MessageBody(ByteBuffer data, int valueLength, int keyLength) {
        this.data = data;
        this.valueLength = valueLength;
        this.keyLength = keyLength;
    }

    public byte[] getKey() {
        ByteBuffer tmpData = data.duplicate();
        byte[] key = new byte[keyLength];
        tmpData.get(key, 0, keyLength);
        return key;
    }

    public void writeTo(ByteBuffer buffer) {
        buffer.put(data);
    }
}
