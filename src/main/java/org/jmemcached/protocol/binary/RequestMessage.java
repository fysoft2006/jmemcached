package org.jmemcached.protocol.binary;

import java.nio.ByteBuffer;

public final class RequestMessage {
	private final RequestHeader header;
    private final ByteBuffer data;

	public RequestMessage(RequestHeader header, ByteBuffer data) {
		this.data = data;
		this.header = header;
	}

	public RequestHeader getHeader() {
        return header;
    }

    public MessageBody getBody() {
        RequestHeader header = getHeader();
        header.getExtraLength();
        return null;
    }
}
