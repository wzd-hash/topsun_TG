package com.topsun.websocket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UDPReceive extends Thread{

	private InetAddress ip;
	
	private String port;
	
	public static ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
	
	//线程池
	private static final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
	
	public void getMessage() {
		//获取IP和端口
		getUdpInfo();
		
		for (int i = 0; i < 2; i++) {
			newFixedThreadPool.execute(new WebSocketCenter());
		}
		
		try {
			MulticastSocket multicastSocket = new MulticastSocket(Integer.parseInt(port));
			multicastSocket.joinGroup(ip);
			//创建接收消息的套接字，端口和ip要与服务端指定的一致
//			Socket = new DatagramSocket(Integer.parseInt(port));
			
			
			while (true) {
				byte[] bytes = new byte[2048];
				DatagramPacket Packet = new DatagramPacket(bytes,bytes.length); //创建一个数据包来接收消息 
				multicastSocket.receive(Packet);//接收数据包 
			    newFixedThreadPool.execute(new UDPHandle(Packet.getData(),Packet.getLength()));
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void getUdpInfo(){
		
		Properties props = null;
		InputStream in = null;
        try {
        	 props = new Properties();
        	 File file = new File("application.properties");
//        	 in = new FileInputStream(file);
        	 in = this.getClass().getResourceAsStream("/application.properties");
    		 //加载文件
    		 props.load(in);
    		 //读取信息
    	     port = props.getProperty("udp.port");
    	     String property = props.getProperty("udp.ip");
    	     ip = InetAddress.getByName(property);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
       
	}


	@Override
	public void run() {
		getMessage();
	}
	
	
	
	
	
	
}
