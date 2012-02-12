package org.jmemcached.network;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

public class Server {
	private final InetAddress address;
	private final int port;

	public Server(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	public Server(String host, int port) {
		this(resolveAddress(host), port);
	}

	public Server(int port) {
		this((InetAddress) null, port);
	}

	public void run() {
		// Configure the server.
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		// Set up the pipeline factory.
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(
						new RequestDecoder());
			}
		});

		// Bind and start to accept incoming connections.
		bootstrap.bind(new InetSocketAddress(address, port));
	}

	private static InetAddress resolveAddress(String host) {
		try {
			return InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			throw new NetworkException(e);
		}
	}
}
