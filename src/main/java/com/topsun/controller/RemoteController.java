package com.topsun.controller;

import com.alibaba.fastjson.JSONObject;
import com.topsun.service.RemoteService;
import com.topsun.service.impl.RemoteThread;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;


@RestController
@RequestMapping("/remote")
public class RemoteController extends BaseController{

	@Autowired
	private RemoteService remoteService;
	
//	public ExecutorService pool = Executors.newFixedThreadPool(5);
	ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(5,
			new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
	
	@RequestMapping("/sendCommand")
	public Object sendCommand(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, Object> sessionInfo = getSessionInfo();

		String busID = request.getParameter("busID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String channelID = request.getParameter("channelID");
		String cmdID = request.getParameter("cmdID");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("startTime", startTime);
		jsonObject.put("endTime", endTime);
		jsonObject.put("channelID", channelID);

		String sendMsg = URLDecoder.decode(jsonObject.toString(), "UTF-8");
		ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.putAll(sessionInfo);
		ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

		map.put("sendInfo", sendMsg);
		map.put("cmdID", cmdID.trim());
		final CountDownLatch latch=new CountDownLatch(busID.split(",").length);
		if (StringUtils.isNotEmpty(busID)) {
			for (String busid : busID.split(",")) {
				executorService.execute(new RemoteThread(map, Integer.parseInt(busid),resultMap,latch));
			}
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

//	/*
//	 * 信息服务新增接口
//	 * by wangting
//	 * 2019-04-15
//	 */
//	@RequestMapping("/addInfoService")
//	public @ResponseBody
//    Object addInfoService(HttpServletRequest request){
//
//		Integer result = null;
//
//		String informationContent = request.getParameter("informationContent");
//
//		String informationName = request.getParameter("informationName");
//
//		String subCompanyID = request.getParameter("subCompanyID");
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("informationContent", informationContent);
//		map.put("informationName", informationName);
//		map.put("subCompanyID", subCompanyID);
//
//		List<DemandMenu> list = remoteService.queryDemandMenu(map);
//
//		if (list!= null && list.size()>0) {
//			map.put("Id", list.get(0).getId());
//			map.put("delStatus", 0);
//			result = remoteService.updateInfoType(map);
//		}else{
//			result = remoteService.insertInfoType(map);
//			list = remoteService.queryDemandMenu(map);
//		}
//		if (result>0) {
//
//			try {
//				ReceiveQueryTerminalParamWebServiceService mywebService = new ReceiveQueryTerminalParamWebServiceService();
//				ReceiveQueryTerminalParamWebService rq = mywebService.getReceiveQueryTerminalParamWebServicePort();
//
//				Integer state = Integer.parseInt(rq.sendParamQueryCmd(list.get(0).getId().toString(), "33540", "", ""));
//
//
//			} catch (Exception e) {
//				map.clear();
//				map.put("code", 1);
//				map.put("msg", "fail");
//				return map;
//			}
//
//			map.clear();
//			map.put("code", 0);
//			map.put("msg", "success");
//		}else {
//			map.clear();
//			map.put("code", 1);
//			map.put("msg", "fail");
//		}
//
//		return map;
//	}
//
//	//信息服务查询接口
//	@RequestMapping("/listInfoService")
//	public @ResponseBody
//    Object listInfoService(HttpServletRequest request){
//
//		String subCompanyID = request.getParameter("subCompanyID");
//
//		Map<String, Object> map = new HashMap<>();
//
//		if (StringUtils.isNotEmpty(subCompanyID)) {
//			map.put("subCompanyID", subCompanyID);
//		}
//
//		List<DemandMenu> list = remoteService.queryDemandMenu(map);
//		map.clear();
//		map.put("list", list);
//
//		return map;
//	}
	
}
