package com.frankman.socket02.test;

public abstract class AbstractNioCommonServer implements NioServerDef {

	public static int PORT = DEFAULT_PORT;

	public AbstractNioCommonServer(String name) {
	}
	public static String initServerConfig(String name) {
		return name ; 
	}
	public abstract void addChannelHandler(String name);
	public void addBootstrapHandler(String type) {

	}
	@Override
	public void runServer() {
	}
	protected  void shutdown() {
	}
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				runServer();
			}
		}).start();
	}
	public void stop() {
		shutdown();
	}
}
