package com.frankman.socket02.netty.helloworld;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
/**
 * 
* @ClassName: Server  
* <p>Description: 服务端  </p>
* @date 2019年5月30日 上午9:24:22  
*
 */
public class Server {

	public static void main(String[] args) throws Exception {
		//1 创建线两个程组 
		//一个是用于处理服务器端接收客户端连接的   接收任务 
		//一个是进行网络通信的（网络读写的）           任务处理 
		EventLoopGroup pGroup = new NioEventLoopGroup();
		EventLoopGroup cGroup = new NioEventLoopGroup();
		
		//2 创建辅助工具类，用于服务器通道的一系列配置
		ServerBootstrap b = new ServerBootstrap();
		b.group(pGroup, cGroup)		//绑定俩个线程组
		.channel(NioServerSocketChannel.class)		//指定NIO的模式
		.option(ChannelOption.SO_BACKLOG, 1024)		//设置tcp缓冲区 
		.option(ChannelOption.SO_SNDBUF, 32*1024)	//设置发送缓冲大小 发送数据大小
		.option(ChannelOption.SO_RCVBUF, 32*1024)	//这是接收缓冲大小 接受数据大小 
		.option(ChannelOption.SO_KEEPALIVE, true)	//保持连接 默认是true
		.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				/*sc.pipeline().addLast("idleStateHandler",new IdleStateHandler(
						10,
						0,
						0,TimeUnit.SECONDS));*/
				//3 在这里配置具体数据接收方法的处理
				sc.pipeline().addLast(new ServerHandler());
				//下边是心跳添加 handler中心跳超过多久没数据可以 直接断开 
				sc.pipeline().addLast("idleStateHandler",new IdleStateHandler(
						5,
						0,
						0,TimeUnit.SECONDS));
				sc.pipeline().addLast("heartbeat",new HeartbeatHandler());
			}
		});
		
		//4 进行绑定 
		ChannelFuture cf1 = b.bind(8765).sync();//异步处理的 
		//ChannelFuture cf2 = b.bind(8764).sync();
		//5 等待关闭
		cf1.channel().closeFuture().sync();
		//cf2.channel().closeFuture().sync();
		pGroup.shutdownGracefully();
		cGroup.shutdownGracefully();
		
	}
}
