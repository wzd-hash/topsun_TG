package com.topsun.websocket;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class UDPHandle extends Thread{
	
	private static Logger logger = LoggerFactory.getLogger(UDPHandle.class);
	public static LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();

	private byte[] data;
	private int i;
	
	public UDPHandle(byte[] data,int i) {
		this.data = data;
		this.i = i;
	}
	
	
	@Override
	public void run() {
		
		String rec = null;
		
		try {
			rec = new String(data,0,i,"UTF-8");//将接收的信息转换为String
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//logger.info("接收到数据==="+rec);

		System.err.println("接收到数据==="+rec);
		//放入data队列
		queue.offer(rec);

	}


	
}
