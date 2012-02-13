package org.jmemcached.network;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class ServerChannelPipelineFactory implements ChannelPipelineFactory {
	public static final RequestDecoder REQUEST_DECODER = new RequestDecoder();

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();

		pipeline.addLast("requestDecoder", REQUEST_DECODER);
		pipeline.addLast("handler", new RequestHandler());


		return pipeline;
	}
}
