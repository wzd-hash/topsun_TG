package com.topsun.controller;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topsun.entity.*;
import com.topsun.service.*;
import com.topsun.util.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topsun.model.AlarmModel;
import com.topsun.service.impl.ReceiveQueryTerminalParamWebServiceService;

@RestController
@RequestMapping("/alarm")
public class AlarmController extends BaseController{
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private RemoteService remoteService;

	@Autowired
	private DataLocationService dataLocationService;
	
	@Autowired
	private LogUtil logUtil;
	
	/*
	 * 安全监管
	 * 2019-12-17
	 */
	@RequestMapping("/listBusAlarm")
	public Object listBusAlarm(HttpServletRequest request){
		
		Map<String, Object> map = getSessionInfo();
		
		String companyID = request.getParameter("companyID");
		
		String authLeave = request.getParameter("authLeave");
		//线路ID
		String roulesID = request.getParameter("roulesID");
		//车辆ID
		String busID = request.getParameter("busID");
		//司机ID
		String driverID = request.getParameter("driverID");
		//开始时间
		String startTime = request.getParameter("startTime");
		//结束时间
		String endTime = request.getParameter("endTime");
		//报警级别
		String alarmLevel = request.getParameter("alarmLevel");
		//报警类型
		String alarmType = request.getParameter("alarmType");
		//处理状态
		String handleState = request.getParameter("handleState");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		
		if (StringUtils.isNotEmpty(busID)) {
			map.put("busID", busID.split(","));
		}
		
		if (StringUtils.isNotEmpty(roulesID)) {
			map.put("roulesID", roulesID.split(","));
		}
		
		if (StringUtils.isNotEmpty(companyID)&&StringUtils.isNotEmpty(authLeave)) {
			map.put("companyID", companyID);
			map.put("authLeave", authLeave);
		}
		
		if (StringUtils.isEmpty(startTime)) {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		}
		map.put("tableName", "bus_alarm_"+DateUtils.getFirstLastDay(startTime));
		map.put("databaseName", "alarm_topsun");
		
		if (alarmService.tableIfExists(map)==0) {
			map.clear();
			map.put("list", new ArrayList<>());
			map.put("total", 0);
			map.put("code", 0);	
			return map;
		}
		
		map.put("driverID", driverID);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("alarmLevel", alarmLevel);
		if (StringUtils.isNotEmpty(alarmType)) {
			String[] alarm = alarmType.split(",");
			List<String> list = new ArrayList<String>();
			List<String> x64 = new ArrayList<String>();
			List<String> x65 = new ArrayList<String>();
			List<String> x66 = new ArrayList<String>();
			List<String> x67 = new ArrayList<String>();
			
			for (int i=0;i<alarm.length;i++) {
				if (32>Integer.parseInt(alarm[i])) {
					list.add( "alarm_"+alarm[i]);
					continue;
				}
				if (alarm[i].substring(0,2).contains("64")) {
					x64.add(alarm[i].substring(2));
					continue;
				}
				if (alarm[i].substring(0,2).contains("65")) {
					x65.add(alarm[i].substring(2));
					continue;
				}
				if (alarm[i].substring(0,2).contains("66")) {
					x66.add(alarm[i].substring(2));
					continue;
				}
				if (alarm[i].substring(0,2).contains("67")) {
					x67.add(alarm[i].substring(2));
					continue;
				}
			}
			
			map.put("list", list);
			map.put("x64", x64);
			map.put("x65", x65);
			map.put("x66", x66);
			map.put("x67", x67);
		}
		map.put("handleState", handleState);

		
		Integer count = alarmService.listBusAlarmCount(map);
		
		if (StringUtils.isNotEmpty(page)&&StringUtils.isNotEmpty(limit)) {
			
			map.put("page", (Integer.parseInt(page)-1)*Integer.parseInt(limit));
			map.put("limit", Integer.parseInt(limit));
		}
		
		List<AlarmInfo> list = alarmService.listBusAlarm(map);
		
		map.clear();
		map.put("list", list);
		map.put("total", count);
		map.put("code", 0);		
		
		return map;
	}
	
	@RequestMapping("/export")
	public Object export(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = getSessionInfo();
		String companyID = request.getParameter("companyID");
		
		String authLeave = request.getParameter("authLeave");
		//车辆ID
		String busID = request.getParameter("busID");
		//司机ID
		String driverID = request.getParameter("driverID");
		//开始时间
		String startTime = request.getParameter("startTime");
		//结束时间
		String endTime = request.getParameter("endTime");
		//报警级别
		String alarmLevel = request.getParameter("alarmLevel");
		//报警类型
		String alarmType = request.getParameter("alarmType");
		//报警类型
		String handleState = request.getParameter("handleState");
		if (StringUtils.isNotEmpty(busID)) {
			map.put("busID", busID.split(","));
		}
		map.put("companyID", companyID);
		map.put("authLeave", authLeave);
		map.put("driverID", driverID);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("alarmLevel", alarmLevel);
		
		if (StringUtils.isEmpty(startTime)) {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		}
		map.put("tableName", "bus_alarm_"+DateUtils.getFirstLastDay(startTime));
		map.put("databaseName", "alarm_topsun");
		
		if (alarmService.tableIfExists(map)==0) {
			map.clear();
			map.put("code", 2);
			return map;
		}
		if (StringUtils.isNotEmpty(alarmType)) {
			String[] alarm = alarmType.split(",");
			List<String> list = new ArrayList<String>();
			List<String> x64 = new ArrayList<String>();
			List<String> x65 = new ArrayList<String>();
			List<String> x66 = new ArrayList<String>();
			List<String> x67 = new ArrayList<String>();
			
			for (int i=0;i<alarm.length;i++) {
				if (32>Integer.parseInt(alarm[i])) {
					list.add( "alarm_"+alarm[i]); 
				}
				if (alarm[i].contains("64")) {
					x64.add(alarm[i].substring(2));
				}
				if (alarm[i].contains("65")) {
					x65.add(alarm[i].substring(2));
				}
				if (alarm[i].contains("66")) {
					x66.add(alarm[i].substring(2));
				}
				if (alarm[i].contains("67")) {
					x67.add(alarm[i].substring(2));
				}
			}
			
			map.put("list", list);
			map.put("x64", x64);
			map.put("x65", x65);
			map.put("x66", x66);
			map.put("x67", x67);
		}
		map.put("handleState", handleState);
		List<AlarmModel> list = alarmService.listBusAlarmModel(map);
		
		if (list==null||list.size()==0) {
			map.clear();
			map.put("code", 1);//暂无数据导出
			return map;
		}
		
		for (AlarmModel alarmModel : list) {
			try {
				alarmModel.setPosition(LocationUtil.getposition( alarmModel.getLongitude(),alarmModel.getLatitude()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		String fileName = "安全监管信息表";
		
		try {
			EasyExcelUtil.exportExcel(response, new AlarmModel(), fileName, list);
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 2);//系统报错
		}
		
		return map;
	}
	
	/*
	 * 查看音频详情
	 * 2019-12-18
	 */
	@RequestMapping("/listMediaInfo")
	public Object listMediaInfo(HttpServletRequest request){
		Map<String, Object> map = getSessionInfo();
		
		//报警时间
		String alarmTime = request.getParameter("alarmTime");
		//外设ID
		String perID = request.getParameter("perID");
		//车辆ID
		String busID = request.getParameter("busID");
		//GPS时间
		String gpsTime = request.getParameter("gpsTime");
		
		map.put("busID", busID);
		map.put("alarmTime", alarmTime);
		map.put("gpsTime", gpsTime);
		if ("65".equals(perID)) {//DSM--通道6
			map.put("channelID", 6);
		}
		if ("67".equals(perID)) {//BSD--通道5
			map.put("channelID", 5);
		}
		map.put("attachment_type", 1);
		
		String startTime = "";
		
		if (StringUtils.isNotEmpty(gpsTime)) {
			startTime = gpsTime;
		}else{
			startTime = alarmTime;
		}
		
		
		map.put("tableName", "bus_alarm_"+DateUtils.getFirstLastDay(startTime));
		map.put("databaseName", "alarm_topsun");
		
		if (alarmService.tableIfExists(map)==0) {
			map.clear();
			map.put("list", new ArrayList<>());
			map.put("code", 0);	
			return map;
		}
		
		List<MediaInfo> list = alarmService.listMediaInfo(map);
		map.clear();
		map.put("list", list);
		map.put("code", 0);
		return map;
	}
	
	/*
	 * 报警处理
	 * 2019-12-18
	 */
	@RequestMapping("/handleAlarm")
	public Object handleAlarm(HttpServletRequest request){
		
		Map<String, Object> map = getSessionInfo();
		
		String busID = request.getParameter("busID");
		//报警时间
		String alarmTime = request.getParameter("alarmTime");
		//外设ID
		String perID = request.getParameter("perID");
		//发送内容
	    String sendMsg = request.getParameter("sendMsg");
		
		map.put("busID", busID);
		map.put("alarmTime", alarmTime);
		map.put("perID", perID);
		

		
		try {
			RemoteInfo remoteInfo = new RemoteInfo();
			remoteInfo.setBusID(Integer.parseInt(busID));
			remoteInfo.setCmdID("33536");
			remoteInfo.setSendInfo(sendMsg);
			
			remoteService.sendCommand(remoteInfo);
			
			ReceiveQueryTerminalParamWebServiceService mywebService = new ReceiveQueryTerminalParamWebServiceService();
			ReceiveQueryTerminalParamWebService rq = mywebService.getReceiveQueryTerminalParamWebServicePort();
			
			Integer state = Integer.parseInt(rq.sendParamQueryCmd(remoteInfo.getId().toString(), remoteInfo.getCmdID(), "", ""));
			if (state==1||state==2) {
				map.put("handleState", 1);
				map.put("tableName", "bus_alarm_"+DateUtils.getFirstLastDay(alarmTime));
				map.put("databaseName", "alarm_topsun");
				Integer result = alarmService.updateAlarmHandle(map);
				
				map.clear();
				if (result>0) {
					map.put("code", 0);//成功
				}else{
					map.put("code", 1);
				}
			}else{
				map.clear();
				map.put("code", 1);
			}
			
			
		} catch (Exception e) {
			map.clear();
			map.put("code", 1);//系统报错
		}
		
		return map;
	}
	
	/*
	 * 首页报警提示
	 * 2019-12-30
	 */
	@RequestMapping("/listAlarmCount")
	public Object listAlarmCount(HttpServletRequest request){
		
		Map<String, Object> map = getSessionInfo();
		
		
		map.put("tableName", "bus_alarm_"+DateUtils.getFirstLastDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		
		//根据报警类型
		List<AlarmNum> list = alarmService.listAlarmCount(map);
		
		//根据车辆
		map.put("optType", 1);
		List<AlarmNum> busList = alarmService.listAlarmCount(map);
		
		
		map.clear();
		map.put("AlarmTypelist", list);
		map.put("busAlarmList", busList);
		return map;
	}
	
	/*
	 * 创建/修改文本模板
	 */
	@RequestMapping("/editTextTemplate")
	public Object editTextTemplate(TextTemplate template){
		
		Map<String, Object> sessionMap = getSessionInfo();
		
		try {
			if (template.getTextID()!=null) {//编辑
				
				alarmService.updateTextTemplate(template);
				logUtil.addLog("新增文本模板:"+template.getContent(), 2,sessionMap.get("ip"));
			}else{//新增
				
				alarmService.insertTextTemplate(template);
				logUtil.addLog("编辑文本模板:"+template.getContent(), 3,sessionMap.get("ip"));
			}
			
			sessionMap.clear();
			sessionMap.put("code", 0);
		} catch (Exception e) {
			sessionMap.clear();
			sessionMap.put("code", 1);
			e.printStackTrace();
		}
		
		return sessionMap;
	}
	
	/*
	 * 文本信息模板搜索
	 */
	@RequestMapping("/listTextTemplate")
	public Object listTextTemplate(HttpServletRequest request){
		
		Map<String, Object> sessionMap = getSessionInfo();
		
		String subID = request.getParameter("subID");
		
		if (StringUtils.isNotEmpty(subID)) {
			sessionMap.clear();
			sessionMap.put("subID", subID);
		}else{
			Integer authLeave = (Integer)sessionMap.get("authLeave");
			
			if (authLeave==1) {				
				sessionMap.put("headID", sessionMap.get("companyID"));
			}
			if (authLeave==2) {
				sessionMap.put("subID", sessionMap.get("companyID"));
			}
		}
		
		List<TextTemplate> list = alarmService.listTextTemplate(sessionMap);
		
		sessionMap.clear();
		sessionMap.put("list", list);
		
		return sessionMap;
	}
	
	@RequestMapping("/setAlarmStatus")
	public Object setAlarmStatus(HttpServletRequest request){
		
		Map<String, Object> map = new HashMap<>();
		
		String busID = request.getParameter("busID");
		//报警时间
		String alarmTime = request.getParameter("alarmTime");
		//外设ID
		String perID = request.getParameter("perID");
		
		String handleState = request.getParameter("handleState");
		map.put("busID", busID);
		map.put("alarmTime", alarmTime);
		map.put("perID", perID);
		map.put("handleState", handleState);
		
		Integer result = alarmService.updateAlarmHandle(map);
		
		map.clear();
		if (result>0) {
			map.put("code", 0);
		}else{
			map.put("code", 1);
		}
		
		return map;
	}

	/**
	 * 查询超速报警
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/speedSelect")
	public Object roundSelect() throws ParseException {
		HashMap<String, Object> map = getHashSessionInfo();
		String busIds = request.getParameter("busID");
        String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String plateNO = request.getParameter("plateNO");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");

		if(StringUtils.isNotEmpty(busIds)) {
			map.put("busID", busIds.split(","));
		}

		if (StringUtils.isNotEmpty(page)&&StringUtils.isNotEmpty(limit)) {

			map.put("page", (Integer.parseInt(page)-1)*Integer.parseInt(limit));
			map.put("limit", Integer.parseInt(limit));
		}
		map.put("plateNO", plateNO);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
		if(null==startTime || null == endTime||"".equals(startTime)||"".equals(endTime)){
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf2.format(new Date());
			map.put("time", time);
		}
				List<BusSpeed> list = dataLocationService.speedListBusData(map);

				int speedNum = dataLocationService.speedListBusDataCount(map);
		map.clear();
		map.put("list",list);
		if(null==list){
			map.put("total", 0);
		}else {
			map.put("total", speedNum);
		}

		map.put("code", 0);
		return map;
	}

	/**
	 * 查询超速报警
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/speedTable")
	public Object speedTable()  {
		HashMap<String, Object> map = getHashSessionInfo();
		String busIds = request.getParameter("busID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(StringUtils.isNotEmpty(busIds)) {
			map.put("busID", busIds.split(","));
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		if(null==startTime || null == endTime||"".equals(startTime)||"".equals(endTime)){
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf2.format(new Date());
			map.put("time", time);
		}
		startPage();
		List<BusSpeed> busSpeed = dataLocationService.speedListCount(map);

		map.clear();
		map.put("busSpeed",busSpeed);
		if(null==busSpeed){
			map.put("total", 0);
		}else {
			map.put("total", new PageInfo(busSpeed).getTotal());
		}
		map.put("code", 0);
		return map;
	}

	@RequestMapping("/exportSpeedTable")
	@CrossOrigin
	public Object exportSpeedTable(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = getHashSessionInfo();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String busIds = request.getParameter("busID");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		if(StringUtils.isNotEmpty(busIds)) {
			map.put("busID", busIds.split(","));
		}
		List<BusSpeed> busSpeed = dataLocationService.speedListCount(map);

		map.clear();
		try {
			EasyExcelUtil.exportExcel(response, new BusSpeedTable(), "超速报警统计", busSpeed);
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 2);//系统报错
		}
		return map;
	}

	@RequestMapping("/exportsSpeedSelect")
	@CrossOrigin
	public Object exportsSpeedSelect(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat sdf_round = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, Object> map = getHashSessionInfo();
		String busIds = request.getParameter("busID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
/*		String headID = request.getParameter("headID");
		String subID = request.getParameter("subID");*/
	/*	String roulesID = request.getParameter("roulesID");
		String companyID = request.getParameter("companyID");
		String authLeave = request.getParameter("authLeave");*/

		if(StringUtils.isNotEmpty(busIds)) {
			map.put("busID", busIds.split(","));
		}

/*		if (StringUtils.isNotEmpty(roulesID)) {
			map.put("roulesID", roulesID.split(","));
		}

		if (StringUtils.isNotEmpty(subID)) {
			map.put("subID", subID.split(","));
		}

		if (StringUtils.isNotEmpty(headID)) {
			map.put("headID", headID.split(","));
		}
		if (StringUtils.isNotEmpty(companyID)&&StringUtils.isNotEmpty(authLeave)) {
			map.put("companyID", companyID);
			map.put("authLeave", authLeave);
		}*/

//		String[] busIDs = busIds.split(",");

//		int count = 0;
// 		List<DataLocation> listAll = new ArrayList<>();
//		for (String string : busIDs) {
//			HashMap<String, Object> tableMap = new HashMap<String, Object>();
//			tableMap.put("databaseName","data_history_topsun_"+ new SimpleDateFormat("yyyy").format(new Date()));
//			tableMap.put("tableName","bus_"+string);
//			//判断有没有这个数据库
//			int table = alarmService.tableIfExists(tableMap);
//			if(table>0){
//				map.put("tableName",
//						"data_history_topsun_" + new SimpleDateFormat("yyyy").format(new Date()) + ".bus_" + string);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<BusSpeed> list = dataLocationService.speedListBusData(map);

//				if(!list.isEmpty()){
//					listAll.addAll(list);
//				}
//				int speedNum = dataLocationService.speedListBusDataCount(map);
//				count+=speedNum;
//			}
//
//		}

		map.clear();
		try {
			for (BusSpeed busSpeed :list) {
				busSpeed.setLocation(LocationUtil.getposition(busSpeed.getLongitude(), busSpeed.getLatitude()));
			}
			EasyExcelUtil.exportExcel(response, new BusSpeed(), "超速报警", list);
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 2);//系统报错
		}
		return map;
	}

	/**
	 * 超速报警统计
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/busSpeedTables")
	public Object busSpeedTables()  {
		HashMap<String, Object> map = getHashSessionInfo();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		if(null==startTime || null == endTime||"".equals(startTime)||"".equals(endTime)){
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf2.format(new Date());
			map.put("time", time);
		}
		startPage();
		List<BusSpeedTable> busSpeedTables = dataLocationService.speedListBusTable(map);

		map.clear();
		map.put("busSpeed",busSpeedTables);
		if(null==busSpeedTables){
			map.put("total", 0);
		}else {
			map.put("total", new PageInfo(busSpeedTables).getTotal());
		}
		map.put("code", 0);
		return map;
	}

	@RequestMapping("/exportBusSpeedTables")
	@CrossOrigin
	public Object exportBusSpeedTables(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = getHashSessionInfo();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<BusSpeedTable> list = dataLocationService.speedListBusTable(map);

		map.clear();
		try {
			EasyExcelUtil.exportExcel(response, new BusSpeedTable(), "超速报警统计", list);
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 2);//系统报错
		}
		return map;
	}
}
