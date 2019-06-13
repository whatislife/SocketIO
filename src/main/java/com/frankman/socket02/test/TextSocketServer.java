package com.frankman.socket02.test;

public class TextSocketServer extends AbstractNioCommonServer {
	public TextSocketServer(String name) {
		super(name);
	}
	private static TextSocketServer textSocketServer = null;
	@Override
	public void addChannelHandler(String type) {
	}

	public static TextSocketServer getInstance() {
		if (textSocketServer == null) {
			String serverConnectConfig = initServerConfig("123456");
			textSocketServer = new TextSocketServer(serverConnectConfig);
		}
		return textSocketServer;
	}
}
