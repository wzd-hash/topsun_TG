package com.topsun.websocket;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component
public class WebSocketCenter extends Thread{


		public void sendOnLine() {
		
			
			while(true) {

				try {
					String poll = (String)UDPHandle.queue.take();
					
					
					JSONObject parseObject = JSON.parseObject(poll);
					
					
					WebSocketListen.send(parseObject.getString("headCompanyID"), parseObject.getString("subCompanyID"), parseObject);
						
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		
	}
		
		@Override
		public void run() {
			System.err.println("开启线程");
			sendOnLine();
		}


}
