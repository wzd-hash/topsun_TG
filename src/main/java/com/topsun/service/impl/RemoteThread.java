package com.topsun.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.topsun.entity.RemoteInfo;
import com.topsun.entity.ResponseMsg;
import com.topsun.service.ReceiveQueryTerminalParamWebService;
import com.topsun.service.RemoteService;
import com.topsun.util.ApplicationContextUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class RemoteThread extends Thread {

	private ConcurrentHashMap<String, Object> map;
	
	private Integer busID;
	
	private ConcurrentHashMap<String, Object> resultMap;
	
	private CountDownLatch latch;
	
	private RemoteService remoteService = (RemoteService) ApplicationContextUtil.getBean("remoteService");
	
	public RemoteThread(ConcurrentHashMap<String, Object> map, Integer busID, ConcurrentHashMap<String, Object> resultMap, CountDownLatch latch) {
		super();
		this.map = map;
		this.busID = busID;
		this.resultMap = resultMap;
		this.latch = latch;
	}

	public static List<ResponseMsg> getMsg(String json) {
		List<ResponseMsg> msgList = new ArrayList<>();

		JSONArray parseArray = JSONArray.parseArray(json.replaceAll("=",":"));
		for (int i = 0; i < parseArray.size(); i++) {
			ResponseMsg responseMsg = new ResponseMsg();
			String string = JSON.toJSONString(parseArray.get(i));
			JSONObject jsonObject = JSON.parseObject(string);
			responseMsg.setChannelNO(jsonObject.get("channelNO")+"");
			responseMsg.setStartTime(stringToDate(jsonObject.get("startTime")+""));
			responseMsg.setEndTime(stringToDate(jsonObject.get("endTime")+""));
			responseMsg.setSize(jsonObject.get("size")+"");
			responseMsg.setResourceType(jsonObject.get("resourceType")+"");

			msgList.add(responseMsg);
		}

		return msgList;
	}

	public static String stringToDate(String date) {

		SimpleDateFormat sdf =  new SimpleDateFormat("yyMMddHHmmss");
		SimpleDateFormat sdf1 =  new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		try {

			Date parse = sdf.parse(date);
			String format = sdf1.format(parse);

			return "20"+format;
		} catch (ParseException e) {
			e.printStackTrace();
			return "20"+date;
		}
	}

	@Override
	public void run() {

		RemoteInfo remoteInfo = new RemoteInfo();
		remoteInfo.setBusID(busID);
		remoteInfo.setCmdID((String)map.get("cmdID"));
		remoteInfo.setSendInfo((String)map.get("sendInfo"));
		
		Integer result = remoteService.sendCommand(remoteInfo);
		try {
			ReceiveQueryTerminalParamWebServiceService mywebService = new ReceiveQueryTerminalParamWebServiceService();
			ReceiveQueryTerminalParamWebService rq = mywebService.getReceiveQueryTerminalParamWebServicePort();
			
			//System.err.println(remoteInfo.getId()+"-----"+remoteInfo.getCmdID());
			
			Integer state = Integer.parseInt(rq.sendParamQueryCmd(remoteInfo.getId().toString(), remoteInfo.getCmdID(), "", ""));
			if (state == 1) {//下发数据成功
				map.put("id", remoteInfo.getId());
				for(int i=0;i<5;i++){
					remoteInfo = remoteService.queryRemoteInfo(map);
					
					if (remoteInfo.getCmdState()==2) {//终端回复数据
						if ("[]".equals(remoteInfo.getResponseInfo())){
							resultMap.put(remoteInfo.getBusID().toString(), remoteInfo.getResponseInfo().replaceAll("\r|\n", ""));
						}
						List<ResponseMsg> msg = getMsg(remoteInfo.getResponseInfo().replaceAll("\r|\n", ""));
						resultMap.put(remoteInfo.getBusID().toString(), msg);
						break;
					}else{
						resultMap.put(remoteInfo.getBusID().toString(), 1);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}				
			}else{
				resultMap.put(busID.toString(), -1);//服务器异常
			}
		} catch (Exception e) {
			e.getMessage();
			resultMap.put(busID.toString(), -1);//服务器异常
		}
		
		latch.countDown();
	}
}
