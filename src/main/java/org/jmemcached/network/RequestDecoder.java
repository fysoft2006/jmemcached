package org.jmemcached.network;

import com.google.common.base.Stopwatch;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jmemcached.protocol.binary.RequestHeader;
import org.jmemcached.protocol.binary.RequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class RequestDecoder extends FrameDecoder {
	private static Logger LOG = LoggerFactory.getLogger(RequestDecoder.class);

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		if (isHeaderNotReady(buffer)) {
			return null;
		}

		Stopwatch stopwatch = new Stopwatch().start();
		// Mark buffer position if we will need to reset it
		buffer.markReaderIndex();

		RequestHeader header = readHeader(buffer);
		LOG.debug("Read header in {}", stopwatch.stop());

		if (isBodyNotReady(buffer, header)) {
			buffer.resetReaderIndex();
			return null;
		}

		stopwatch.reset().start();
		ByteBuffer data = readData(buffer, header);
		LOG.debug("Read data in {}", stopwatch.stop());

		return new RequestMessage(header, data);
	}

	private boolean isHeaderNotReady(ChannelBuffer buffer) {
		return buffer.readableBytes() < RequestHeader.HEADER_LENGTH;
	}

	private boolean isBodyNotReady(ChannelBuffer buffer, RequestHeader header) {
		return buffer.readableBytes() < header.getTotalBodyLength();
	}

	private RequestHeader readHeader(ChannelBuffer buffer) {
		byte[] header = readBytes(buffer, RequestHeader.HEADER_LENGTH);
		return new RequestHeader(header);
	}

	private ByteBuffer readData(ChannelBuffer buffer, RequestHeader header) {
		return buffer.toByteBuffer(buffer.readerIndex(),  header.getTotalBodyLength());
	}

	private byte[] readBytes(ChannelBuffer buffer, int length) {
		byte[] bytes = new byte[length];
		buffer.readBytes(bytes);
		return bytes;
	}
}
