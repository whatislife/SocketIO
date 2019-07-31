package com.frankman.socket02.netty.ende1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler2 extends ChannelHandlerAdapter {


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(" server channel active... ");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Server2 :" + msg);
		String reponsess = "数据开始1";
		ctx.writeAndFlush(Unpooled.copiedBuffer(reponsess.getBytes()));
		Thread.sleep(60000);
		String response = "服务器响应2：" + msg + "$_";
		ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
//		for(int i = 0 ; i< 1000 ; i ++){
//			String str = "{'code':'CPS3035','data':{'actualPay':0,'agioMoney':0,'bdParkId':323615699161509888,'carColor':'蓝','carId':0,'carType':'小车','chargeType':'','couponMoney':0,'createDate':'2019-06-17 17:06:00','delayMoney':0,'discountPeriodMoney':0,'entryTime':'2019-06-17 17:06:00','inCross':'入口8','inCrossType':0,'inIdentity':'临停车','orderId':0,'outIdentity':'','parkId':323615699161509888,'parkRecordId':2168486527,'parkingArea':'P2','parkingareaId':28,'plateNumber':'京T2R003','psParkSpaceId':0,'realPay':0,'recordCode':'75741cb4-15aa-4ce0-9e41-49ccad9af5ef','recordType':0,'remark':'','shouldPay':0,'sortNum':17170630,'terminalId':2,'terminalName':'T2','uploadSource':2,'userId':0},'ip':'cps.aipark.com','oprNum':'2f750fbf-36b6-4b10-9b20-fdfc3685d4df'}";
//			String response = "服务器响应：" + str + "$_";
//			ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
//		}
		
		
		
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) throws Exception {
		ctx.close();
	}




}
