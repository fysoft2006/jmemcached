package org.jmemcached.protocol.binary;

import java.nio.ByteBuffer;

/**
 * Packet structure:
 * Byte/     0       |       1       |       2       |       3       |
 * /              |               |               |               |
 * |0 1 2 3 4 5 6 7|0 1 2 3 4 5 6 7|0 1 2 3 4 5 6 7|0 1 2 3 4 5 6 7|
 * +---------------+---------------+---------------+---------------+
 * 0/ HEADER                                                        /
 * /                                                               /
 * /                                                               /
 * /                                                               /
 * +---------------+---------------+---------------+---------------+
 * 24/ COMMAND-SPECIFIC EXTRAS (as needed)                           /
 * +/  (note length in the extras length header field)              /
 * +---------------+---------------+---------------+---------------+
 * m/ Key (as needed)                                               /
 * +/  (note length in key length header field)                     /
 * +---------------+---------------+---------------+---------------+
 * n/ Value (as needed)                                             /
 * +/  (note length is total body length header field, minus        /
 * +/   sum of the extras and key length body fields)               /
 * +---------------+---------------+---------------+---------------+
 */
public final class RequestMessage {
	private final RequestHeader header;
	private final byte[] extras;
	private final MessageBody body;

	public RequestMessage(RequestHeader header, ByteBuffer data) {
		this.header = header;
		extras = readExtras(data, header.getExtraLength());
		body = new MessageBody(data, header.getKeyLength());
	}

	public RequestHeader getHeader() {
		return header;
	}

	public MessageBody getBody() {
		return body;
	}

	private static byte[] readExtras(ByteBuffer data, int extraLength) {
		return BufferUtils.readBytes(data, extraLength);
	}
}
