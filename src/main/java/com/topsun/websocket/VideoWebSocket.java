package com.topsun.websocket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.topsun.entity.BusInfo;
import com.topsun.entity.TerminalInfo;
import com.topsun.service.BusService;
import com.topsun.service.DataLocationService;
import com.topsun.service.ReceiveQueryTerminalParamWebService;
import com.topsun.service.impl.ReceiveQueryTerminalParamWebServiceService;
import com.topsun.util.ApplicationContextUtil;
import com.topsun.util.MathUtil;

@ServerEndpoint("/videoWebSocket")
@Component
@Scope("prototype")
public class VideoWebSocket<V> {
	
	private static int onlineCount = 0; 
	/*
	 * 读取配置文件
	 */
	public static Properties getVideoInfo(){
		
		Properties props = null;
		InputStream in = null;
        try {
        	 props = new Properties();
//        	 File file = new File("/application.properties");
//        	 in = new FileInputStream(file);
        	 in = VideoWebSocket.class.getResourceAsStream("/application.properties");
    		 //加载文件
    		 props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
       return props;
	}
	
	//busMap  key - busID  value - Set<session> 
	public static ConcurrentHashMap<String,CopyOnWriteArraySet<Session>> busCount = new ConcurrentHashMap<>();
	//sessionMap  key - session   value - Set<busID>
	public static ConcurrentHashMap<Session,CopyOnWriteArraySet<BusInfo>> sessionCount = new ConcurrentHashMap<>();

	private static DataLocationService dataLocationService = (DataLocationService) ApplicationContextUtil.getBean("dataLocationService");
	private static BusService busService = (BusService) ApplicationContextUtil.getBean("busServiceImpl");
	static Properties prop =  getVideoInfo();
	
	//连接建立成功
	 @OnOpen
	 public void onOpen(Session session) throws IOException{
		 
		 System.err.println("websocket建立连接"+session);
		 
		 String url = prop.getProperty("video.outputurl");
		 
		 System.err.println("url----"+url);
		 
		 sendMessage(url, session);
		 
		 addOnlineCount();
	 }
	 
	//连接关闭
	 @OnClose
	 public void onClose(Session session){
		 
		 System.err.println("websocket关闭连接"+session);
		 //关闭该session操作的所有busID
		 CopyOnWriteArraySet<BusInfo> busList = sessionCount.get(session);
		 if (busList!=null&&busList.size()>0) {
			 for (BusInfo busInfo : busList) {
				 CopyOnWriteArraySet<Session> sessionList = busCount.get(busInfo.getBusID().toString());
				 if (sessionList!=null&&sessionList.size()>0) {
					 sessionList.remove(session);
					 busCount.put(busInfo.getBusID().toString(), sessionList);
					 sendMsg(busInfo, "2");
				}
				
			 }
		}
		 subOnlineCount();
	 }
	
	 
	 //收到客户端消息后调用的方法
	 @OnMessage
	 public void onMessage(String message,Session session){

		  System.out.println("来自客户端的消息:" + message); 

		  JSONObject jsonObject = JSONObject.parseObject(message);
		  
		  
		  String plateNO = jsonObject.getString("plateNO");
		  
		  String busID = null;
		  String channelCount = null;
		  String simCard = null;
		  
		  if(plateNO != null) {
			  //查询车辆
			  Map< String, Object> map = new HashMap<>();
			  map.put("plateNO", plateNO);
			  List<BusInfo> listBusInfo = busService.listBusInfo(map);
			  BusInfo busInfo = listBusInfo.get(0);
			  
			  busID = busInfo.getBusID().toString();
			  channelCount = busInfo.getTerminalInfo().getChannelCount().toString();
			  simCard = busInfo.getTerminalInfo().getSimCard().toString();
			  
			  
		  }else {
			//车辆ID
			  busID = jsonObject.getString("busID");
			  //通道数
			  channelCount = jsonObject.getString("channelCount");
			  //sim卡号
			  simCard = jsonObject.getString("simCard");
		  }
		  
		  
		  
		  //操作  1-开始  2-断开
		  String optType = jsonObject.getString("optType");
		  
		  TerminalInfo terminalInfo = new TerminalInfo();
		  terminalInfo.setChannelCount(Integer.parseInt(channelCount));
		  terminalInfo.setSimCard(simCard);
		  
		  BusInfo busInfo = new BusInfo();
		  busInfo.setBusID(Integer.parseInt(busID));
		  busInfo.setTerminalInfo(terminalInfo);
		  
		 
		  CopyOnWriteArraySet<Session> sessionList = busCount.get(busID);
		  if (sessionList==null) {
			  sessionList = new CopyOnWriteArraySet<>();
		  }
		  CopyOnWriteArraySet<BusInfo> busList = sessionCount.get(session);
		  if (busList==null) {
			  busList = new CopyOnWriteArraySet<>();
		  }
		  switch (optType) {
		  	case "1"://点击车辆查看视频
		  		
		  		sendMsg(busInfo, optType);
		  		
		  		//busMap增加一条  busID  -- session
		  		sessionList.add(session);
		  		busCount.put(busID, sessionList);
		  		
		  		//sessionMap增加一条   session  -- busID
		  		busList.add(busInfo);
		  		sessionCount.put(session, busList);
		  		
			break;
		  	case "2"://取消查看实时视频
		  		//busMap减少一条  busID  -- session
		  		
		  		if (sessionList!=null&&sessionList.size()>0) {
		  			sessionList.remove(session);
			  		busCount.put(busID, sessionList);
			  		
			  		//sessionMap减少一条   session  -- busID
			  		busList.remove(busInfo);
			  		sessionCount.put(session, busList);
			  		sendMsg(busInfo, optType);
				}
		  		
		  	break;
		  }
		  
		  
		  
	 }
	 
	 //判断是否进行下发操作
	 public static void sendMsg(BusInfo busInfo,String optType){
		 
		 CopyOnWriteArraySet<Session> sessionList = busCount.get(busInfo.getBusID().toString());
		 
		 ReceiveQueryTerminalParamWebServiceService mywebService = new ReceiveQueryTerminalParamWebServiceService();
		 ReceiveQueryTerminalParamWebService rq = mywebService.getReceiveQueryTerminalParamWebServicePort();
			
		 String content = "";
		 
		 
		 
		String ip = MathUtil.toHexString(prop.getProperty("video.inputurl.ip"));
		String length = String.format("%02x", ip.length()/2);
		String port =String.format("%x", Integer.parseInt(prop.getProperty("video.inputurl.port")));
		 
		Map<String, Object> param = new HashMap<>();
		param.put("busID", busInfo.getBusID());
		param.put("simCard", busInfo.getTerminalInfo().getSimCard());
		param.put("optType", optType);
		
		 if (optType.equals("1")&&(sessionList==null||sessionList.size()==0)) {//开启操作
			 
			 for(int i=1;i<=busInfo.getTerminalInfo().getChannelCount();i++){
				 
				 try {
					 content = length+ip+port+"0000"+String.format("%02x", i)+"0001";
					 
					 param.put("content", content);
					 param.put("channelNO", i);
					 
					 Integer result = dataLocationService.insertVideoTab(param);
					 
					 int state = Integer.parseInt(rq.sendParamQueryCmdSeq(busInfo.getTerminalInfo().getSimCard()+",37121,"+content));
					 
					 System.err.println("state--"+state);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
				 
				 
			 }

			 
		 }
		 
		 
		 if (optType.equals("2")&&(sessionList==null||sessionList.size()==0)) {
			 try {
				 for(int i=1;i<=busInfo.getTerminalInfo().getChannelCount();i++){
						content = String.format("%02x", i)+"000001";
						param.put("content", content);
						param.put("channelNO", i);
						Integer result = dataLocationService.insertVideoTab(param);
						int state = Integer.parseInt(rq.sendParamQueryCmdSeq(busInfo.getTerminalInfo().getSimCard()+",37122,"+content));
						System.err.println("state--"+state);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		}
		 
	 }
	 
	 
	 
	 //发生错误时调用
	 @OnError
	 public void onError(Session session, Throwable error){
		 System.err.println("websocket发生错误"+session);
		 
		 //error.printStackTrace();
		 onClose(session);
	 }
	 
	 public static void sendMessage(String message,Session session) throws IOException{
		 
		 session.getBasicRemote().sendText(message);
		 
	 }
	 
    public static synchronized int getOnlineCount() {
	     
		 return onlineCount;
	 }
     public static synchronized void addOnlineCount() {
    	 VideoWebSocket.onlineCount++;
     }
     public static synchronized void subOnlineCount() {
    	 VideoWebSocket.onlineCount--;
     }
}
