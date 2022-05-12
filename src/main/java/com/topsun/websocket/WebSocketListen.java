package com.topsun.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.topsun.controller.BaseController;
import com.topsun.util.LocationUtil;


@ServerEndpoint("/websocket")
@Component
@Scope("prototype")
public class WebSocketListen extends BaseController{
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。  
	private static int onlineCount = 0;  
	
	 public static ConcurrentHashMap<String,CopyOnWriteArrayList<Session>> subSession = new ConcurrentHashMap<String, CopyOnWriteArrayList<Session>>();
	 
	 public static ConcurrentHashMap<String,CopyOnWriteArrayList<Session>> headSession = new ConcurrentHashMap<String, CopyOnWriteArrayList<Session>>();
	 
	 public static ConcurrentHashMap<String,Integer> busCount = new ConcurrentHashMap<String,Integer>();
	 
	 
	 public String authLeave;
	 public String companyID;
	 //连接建立成功
	 @OnOpen
	 public void onOpen(Session session) throws IOException{
		 
//		 webSocketSet.add(this);
		 System.err.println("建立连接"+session);
		 addOnlineCount();
	 }
	 
	 //连接关闭
	 @OnClose
	 public void onClose(Session session){
		 
		 System.err.println("websocket关闭连接"+session);

		 removeSession(session);

		 
		 subOnlineCount();
	 }

	 //收到客户端消息后调用的方法
	 @OnMessage
	 public void onMessage(String message,Session session){

		  System.out.println("来自客户端的消息:" + message); 

		  handleSession(message,session);

		  
	 }
	 
	 //发生错误时调用
	 @OnError
	 public void onError(Session session, Throwable error){
		 System.err.println("websocket发生错误");
		 removeSession(session);
		 
		 error.printStackTrace();
		 
	 }
	 
	 
	 public static void sendMessage(String message,Session session) throws IOException{
		 
		 session.getBasicRemote().sendText(message);
		 
	 }
	 
	 public static synchronized int getOnlineCount() {
	     
		 return onlineCount;
	 }
     public static synchronized void addOnlineCount() {
    	 WebSocketListen.onlineCount++;
     }
     public static synchronized void subOnlineCount() {
    	 WebSocketListen.onlineCount--;
     }
     
     public void handleSession(String message,Session session){
    	 
    	 JSONObject object = JSONObject.parseObject(message);
    	 
    	 authLeave = object.getString("authLeave");
    	 
    	 companyID = object.getString("companyID");
    	 
    	 if ("1".equals(authLeave)||"0".equals(authLeave)) {//总公司
    		 CopyOnWriteArrayList<Session> headList = headSession.get(companyID);
    		 if(headList == null) {
    			 headList = new CopyOnWriteArrayList<Session>();
    		 }
    		 headList.add(session);
    		 headSession.put(companyID, headList);
    		 System.err.println("新增总公司===="+companyID);
    	 }
    	 if ("2".equals(authLeave)) {//分公司
    		 CopyOnWriteArrayList<Session> subList = subSession.get(companyID);
    		 if(subList == null) {
    			 subList = new CopyOnWriteArrayList<Session>();
    		 }
    		 subList.add(session);
    		 subSession.put(companyID, subList);
    		 System.err.println("新增分公司===="+companyID);
    	 }
    	 
    	 
     }
	
	
	 //群发
    public static synchronized void send(String headCompanyID,String subCompanyID,JSONObject message) {

    	
    	//超级账户
		CopyOnWriteArrayList<Session> copyOnWriteArrayList = headSession.get("1");
		if(copyOnWriteArrayList != null) {
			for (Session session : copyOnWriteArrayList) {
				try {
					//获取地理位置
					if(message.get("alarmType") != null) {
						try {
							String getposition = LocationUtil.getposition(message.getString("longitude"),message.getString("latitude"));
							message.put("location", getposition);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					session.getBasicRemote().sendText(message.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
    	
    	//总公司
		CopyOnWriteArrayList<Session> copyOnWriteArrayList1 = headSession.get(headCompanyID);
		if(copyOnWriteArrayList1 != null) {
			for (Session session : copyOnWriteArrayList1) {
				
				try {
					
					//获取地理位置
					if(message.get("alarmType") != null && message.get("location") == null) {
						try {
							String getposition = LocationUtil.getposition(message.getString("longitude"),message.getString("latitude"));
							message.put("location", getposition);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					session.getBasicRemote().sendText(message.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		//分公司
		CopyOnWriteArrayList<Session> copyOnWriteArrayList2 = subSession.get(subCompanyID);
		if(copyOnWriteArrayList2 != null) {
			for (Session session : copyOnWriteArrayList2) {
				try {
					
					
					//获取地理位置
					if(message.get("alarmType") != null && message.get("location") == null) {
						try {
							String getposition = LocationUtil.getposition(message.getString("longitude"),message.getString("latitude"));
							message.put("location", getposition);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					session.getBasicRemote().sendText(message.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
   	 
   	 
    }
	
	
	public void removeSession(Session session) {
		 
		 if ("1".equals(authLeave)||"0".equals(authLeave)) {//总公司
    		 CopyOnWriteArrayList<Session> headList = headSession.get(companyID);
    		 headList.remove(session);
    		 if(headList.size() == 0) {
    			 headSession.remove(companyID);
    		 }
    		 
    		 System.err.println("移除总公司===="+companyID);
    	 }
    	 if ("2".equals(authLeave)) {//分公司
    		 CopyOnWriteArrayList<Session> subList = subSession.get(companyID);
    		 subList.remove(session);
    		 if(subList.size() == 0) {
    			 subSession.remove(companyID);
    		 }
    		 System.err.println("移除分公司===="+companyID);
    	 }
	}
     

}
