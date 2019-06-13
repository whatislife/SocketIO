package com.frankman.socket02.test;


public interface NioServerDef {
	/**服务端server配置*/
	public static final String FILE_NAME = "netty_config";
	
	/**默认的端口号*/
	public static int DEFAULT_PORT=8888;
	
	/**默认的分隔符*/
	public static String DEFAULT_DELIMITER = "\r\n";
	
	/**
	 * 解码器code
	 */
	public static final String DECODER = "decoder";
	
	/**
	 * 编码器code
	 */
	public static final String ENCODER = "encoder";
	/**
	 *执行server
	 */
	public void runServer();
}
