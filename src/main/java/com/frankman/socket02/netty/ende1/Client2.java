package com.frankman.socket02.netty.ende1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 * 
* @ClassName: Client  
* <p>Description: zh </p>
* @date 2019年5月30日 上午10:18:29  
*
 */
public class Client2 {

	public static void main(String[] args) throws Exception {
		
		EventLoopGroup group = new NioEventLoopGroup();
		
		Bootstrap b = new Bootstrap();
		b.group(group)
		 .channel(NioSocketChannel.class)
		 .handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				//
				ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
			//	ByteBuf buf = Unpooled.copiedBuffer("*".getBytes());
				
				sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				sc.pipeline().addLast(new StringDecoder());
				sc.pipeline().addLast(new ClientHandler());
			}
		});
		
		ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();
//		ChannelFuture cf = b.connect("standardcs.cps.aipark.com", 3400).sync();
		//ChannelFuture cf = b.connect("localhost", 3400).sync();
		
//		cf.channel().writeAndFlush(Unpooled.wrappedBuffer("bbbb$_".getBytes()));
		//cf.channel().writeAndFlush(Unpooled.wrappedBuffer("cccc$_".getBytes()));
		//cf.channel().writeAndFlush(Unpooled.wrappedBuffer("heartbeat*".getBytes()));
//		cf.channel().writeAndFlush(Unpooled.wrappedBuffer("ccccsongjian*".getBytes()));
		
		
		//等待客户端端口关闭
		cf.channel().closeFuture().sync();
		group.shutdownGracefully();
		
	}
}
