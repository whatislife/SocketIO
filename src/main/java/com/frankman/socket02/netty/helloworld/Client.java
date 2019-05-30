package com.frankman.socket02.netty.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
/**
 * 
* @ClassName: Client  
* <p>Description: 客户端 只需要一个线程组 处理连接  </p>
* @date 2019年5月30日 上午9:23:34  
*
 */
public class Client {

	public static void main(String[] args) throws Exception{
		
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				sc.pipeline().addLast(new ClientHandler());
			}
		});
		
		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
//		ChannelFuture cf1 = b.connect("standardcs.cps.aipark.com", 3400).sync();
//		ChannelFuture cf1 = b.connect("10.100.16.212:8083", 3400).sync();
		//ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();
		//发送消息
		Thread.sleep(1000);
		//数据一定是buff类型的   =========================================================
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777songjian".getBytes()));
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("666songjian".getBytes()));
		//cf2.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
		Thread.sleep(2000);
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("888songjian".getBytes()));
		//cf2.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
		
		cf1.channel().closeFuture().sync();
		//cf2.channel().closeFuture().sync();
		group.shutdownGracefully();
		
		
		
	}
}
