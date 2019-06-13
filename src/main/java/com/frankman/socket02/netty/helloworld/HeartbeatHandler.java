/**
 * 
 */
package com.frankman.socket02.netty.helloworld;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author ningquan
 *
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter  {
	
	private static Logger logger =Logger.getLogger(HeartbeatHandler.class.getSimpleName());
	
	
	/**
	 * tcplog文件路径
	 */
	private final String LOG_PATH_KEY = "tcp.log.defaultPath";
	private final String LOG_FILE_PREFIX="tcp";
	private final String LOG_FILE_SUFFIX=".log";
	private final DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
	private final DateFormat dfts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public HeartbeatHandler() {
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if(evt instanceof IdleStateEvent){
			IdleStateEvent e = (IdleStateEvent) evt; 
			System.out.println("=================="+e.state());
			if(e.state() == IdleState.READER_IDLE){
				System.out.println("1111111222222222");
			}else if(e.state() == IdleState.WRITER_IDLE){
				System.out.println("5555555555555555555555");
				ByteBuf buff = ctx.alloc().buffer();  
                buff.writeBytes("writetimeout*".getBytes("UTF-8"));  
                ChannelFuture writeAndFlush = ctx.writeAndFlush(buff);  
			}
		}
		
	}
}
