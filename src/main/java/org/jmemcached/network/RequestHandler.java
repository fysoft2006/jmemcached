package org.jmemcached.network;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jmemcached.protocol.binary.MessageBody;
import org.jmemcached.protocol.binary.RequestHeader;
import org.jmemcached.protocol.binary.RequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler extends SimpleChannelUpstreamHandler {
	private static Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		RequestMessage requestMessage = (RequestMessage) e.getMessage();

		RequestHeader header = requestMessage.getHeader();
		MessageBody body = requestMessage.getBody();
		LOG.info("Received request: {}", header);
	}
}
