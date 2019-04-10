package com.frankman.socket01.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
* @ClassName: Server  
* <p>Description: TODO(这里用一句话描述这个类的作用)  </p>
* @date 2019年4月10日 下午3:24:01  
*
 */
public class Server {

	final static int PROT = 8765;
	
	public static void main(String[] args) {
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(PROT);
			System.out.println(" server start .. ");
			//进行阻塞
			Socket socket = server.accept();
			//新建一个线程执行客户端的任务
			new Thread(new ServerHandler(socket)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(server != null){
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			server = null;
		}
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
