package com.frankman.socket02.netty.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
/**
 * 
* @ClassName: ServerHandler  
* <p>Description: 继承 ChannelHandlerAdapter 实现其中的方法、处理数据   channelHandlerApapter
* 或者实现ChannelHandler 这个接口也是可以的 
*  </p>
* @date 2019年5月30日 上午9:26:18  
*
 */
public class ServerHandler extends ChannelHandlerAdapter {


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("————————————————————————————————————channelActive方法——————————————————————————————————————————");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("————————————————————————————————————channelInactive方法——————————————————————————————————————————");
		ctx.close();
	}
	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		System.out.println("————————————————————————————————————close方法——————————————————————————————————————————");
		super.close(ctx, promise);
	}
	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		System.out.println("————————————————————————————————————disconnect方法——————————————————————————————————————————");
		super.disconnect(ctx, promise);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("————————————————————————————————————channelRead方法——————————————————————————————————————————");
			ByteBuf buf = (ByteBuf) msg;
			byte[] req = new byte[buf.readableBytes()];
			buf.readBytes(req);
			String body = new String(req, "utf-8");
			//System.out.println("Server :" + body );
			String response = "进行返回给客户端的响应：" + body ;
			ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
			//.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx)
			throws Exception {
//		System.out.println("读完了");
		System.out.println("————————————————————————————————————channelReadComplete方法——————————————————————————————————————————");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable t)
			throws Exception {
		System.out.println("————————————————————————————————————exceptionCaught方法——————————————————————————————————————————");
		ctx.close();
	}
	//心跳添加 
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if(evt instanceof IdleStateEvent){
			IdleStateEvent e = (IdleStateEvent) evt; 
			if(e.state() == IdleState.READER_IDLE){
				System.out.println("1111111111111111111");
			}else if(e.state() == IdleState.WRITER_IDLE){
				System.out.println("2222222222222222222");
			}	
		}
	}

}
