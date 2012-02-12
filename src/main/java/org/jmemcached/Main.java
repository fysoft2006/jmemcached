package org.jmemcached;

import org.jmemcached.network.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	private static Logger LOG = LoggerFactory.getLogger(Main.class);

	private static final int DEFAULT_PORT = 11211;

	public static void main(String[] args) {
		Server server = new Server(DEFAULT_PORT);
		server.run();
	}
}
