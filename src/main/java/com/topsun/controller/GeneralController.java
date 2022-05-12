package com.topsun.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.topsun.entity.*;
import com.topsun.model.LostConnection;
import com.topsun.service.DataLocationService;
import com.topsun.service.TimerTaskService;
import com.topsun.util.EasyExcelUtil;
import com.topsun.util.LocationUtil;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.format.*;
import jxl.write.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/statistic")
public class GeneralController<E> extends BaseController {

	@Autowired
	private TimerTaskService timerTaskService;

	@Autowired
	private DataLocationService dataLocationService;


	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private RedisTemplate redisTemplate;

	/*
	 * 车辆上下线统计 2019-12-16
	 */
	@RequestMapping("/connectionByBusID")
	public Object connectionByBusID(HttpServletRequest request) {

		Map<String, Object> map = getSessionInfo();

		String busID = request.getParameter("busID");
		String roulesID = request.getParameter("roulesID");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");

		String startTime = request.getParameter("startTime");

		String endTime = request.getParameter("endTime");

//		if (StringUtils.isEmpty(busID)) {
//			map.clear();
//			map.put("code", 1);// 缺少busID
//
//			return map;
//		}

		String page = request.getParameter("page");

		String limit = request.getParameter("limit");

		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		String[] split = busID.split(",");

		map.put("busIDs", split);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("roulesID", roulesID);
		map.put("subID", subID);
		map.put("headID", headID);

		List<Outline> list = timerTaskService.getOutLine(map);

		map.clear();
		map.put("total", pageHelper.getTotal());
		map.put("list", list);
		map.put("code", 0);

		return map;
	}

	@RequestMapping("/exportLostConnection")
	public Object exportLostConnection(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = getSessionInfo();

		String busID = request.getParameter("busID");
		String roulesID = request.getParameter("roulesID");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");

		String startTime = request.getParameter("startTime");

		String endTime = request.getParameter("endTime");

		if (StringUtils.isEmpty(busID)) {
			map.clear();
			map.put("code", 1);// 缺少busID

			return map;
		}

		String[] split = busID.split(",");

		map.put("busIDs", split);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("roulesID", roulesID);
		map.put("subID", subID);
		map.put("headID", headID);

		List<Outline> list = timerTaskService.getOutLine(map);

		if (list == null || list.size() == 0) {
			map.clear();
			map.put("code", 1);// 暂无数据导出
			return map;
		}

		String fileName = "车辆掉线统计表";

		List<List<String>> data = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			List<String> item = new ArrayList<>();

			item.add(String.valueOf(i + 1));
			item.add(list.get(i).getPlateNO());
			item.add(list.get(i).getCompanyName());
			item.add(list.get(i).getOutTime());
			item.add(list.get(i).getInTime() == null ? "-" : list.get(i).getInTime());

			if (list.get(i).getLongTime() != null) {
				Integer leadTime = Integer.parseInt(list.get(i).getLongTime());
				item.add(leadTime / 86400 + "天" + leadTime % 86400 / 3600 + "时" + leadTime % 86400 % 3600 / 60 + "分"
						+ leadTime % 86400 % 3600 % 60 + "秒");
			} else {
				item.add("-");
			}

			data.add(item);
		}

		try {
			EasyExcelUtil.writeExcel(response, new LostConnection(), fileName, data);
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 2);// 系统报错
		}

		return map;

	}

	/*
	 * 车辆上下线统计 2021-03-03
	 */
	@RequestMapping("/roundByBusID")
	public Object roundByBusID(HttpServletRequest request) {

		Map<String, Object> map = getSessionInfo();

		String busID = request.getParameter("busID");
		String roulesID = request.getParameter("roulesID");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");

		String startTime = request.getParameter("startTime");

		String endTime = request.getParameter("endTime");
		String speed = request.getParameter("speed");
		String orientation = request.getParameter("orientation");

		String page = request.getParameter("page");

		String limit = request.getParameter("limit");

		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		if (StringUtils.isNotEmpty(busID)) {
			String[] split = busID.split(",");

			map.put("busIDs", split);
		}

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("roulesID", roulesID);
		map.put("subID", subID);
		map.put("headID", headID);
		map.put("speed", speed);
		map.put("orientation", orientation);

		List<Round> list = timerTaskService.getRoundSelect(map);

		map.clear();
		map.put("total", pageHelper.getTotal());
		map.put("list", list);
		map.put("code", 0);

		return map;
	}

	@RequestMapping("/checkProgress")
	public Object checkProgress() {

		Subject subject = SecurityUtils.getSubject();

		Object progress = subject.getSession().getAttribute("progress");
		Object nowProgress = subject.getSession().getAttribute("nowProgress");

		Map<String, Object> hashMap = new HashMap<String, Object>();


		if(progress == null || nowProgress == null) {
			hashMap.put("code", 0);
			return hashMap;
		}
		hashMap.put("code", 1);
		hashMap.put("progress", (int)progress);
		hashMap.put("nowProgress", (int)nowProgress);

		return hashMap;
	}

	/**
	 * 取消位置解析
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/stopGps")
	public Object stopGps(HttpServletRequest request) {

		Subject subject = SecurityUtils.getSubject();

		HttpServletResponse response = (HttpServletResponse) subject.getSession().getAttribute("response");

		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Content-Disposition", "");
		subject.getSession().setAttribute("stop", 1);

		return 0;

	}

	@RequestMapping("/exportRoundByBusID")
	@CrossOrigin
	public Object exportRoundByBusID(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = getSessionInfo();

		String busID = request.getParameter("busID");
		String roulesID = request.getParameter("roulesID");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");
		String speed = request.getParameter("speed");
		String orientation = request.getParameter("orientation");
		String isGps = request.getParameter("isGps");

		String startTime = request.getParameter("startTime");

		String endTime = request.getParameter("endTime");



		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("nowProgress", 0);
		subject.getSession().setAttribute("progress", 0);
		subject.getSession().setAttribute("response", response);
		subject.getSession().setAttribute("stop", 2);

		if (StringUtils.isNotEmpty(busID)) {
			String[] split = busID.split(",");

			map.put("busIDs", split);
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("roulesID", roulesID);
		map.put("subID", subID);
		map.put("headID", headID);
		map.put("speed", speed);
		map.put("orientation", orientation);

		List<Round> list = timerTaskService.getRoundSelect(map);
		subject.getSession().setAttribute("progress", list.size());

		map.clear();
		try {
			export(startTime,endTime,list, response, "车辆转弯统计表", isGps,speed);
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 2);// 系统报错
		}

		return map;

	}

	@RequestMapping("/exportRoundByBusIDForMonth")
	public Object exportRoundByBusIDForMonth(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = getSessionInfo();

		String busID = request.getParameter("busID");
		String roulesID = request.getParameter("roulesID");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");
		String speed = request.getParameter("speed");
		String orientation = request.getParameter("orientation");

		String startTime = request.getParameter("time");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat msdf = new SimpleDateFormat("yyyy-MM");
		try {
			Calendar instance = Calendar.getInstance();

			Date parse = sdf.parse(startTime);

			instance.setTime(parse);
			instance.add(Calendar.MONTH, 1);
			Date time = instance.getTime();

			if (StringUtils.isNotEmpty(busID)) {
				String[] split = busID.split(",");

				map.put("busIDs", split);
			}
			map.put("startTime", startTime);
			map.put("endTime", sdf.format(time));
			map.put("roulesID", roulesID);
			map.put("subID", subID);
			map.put("headID", headID);
			map.put("speed", speed);
			map.put("orientation", orientation);

			List<Round> list = timerTaskService.getRoundSelectForMonth(map);

			exportForMonth(list, response, "月度车辆转弯统计表", msdf.format(parse), speed);
			map.clear();
			map.put("code", 0);
		} catch (Exception e) {
			map.clear();
			map.put("code", 2);// 系统报错
		}

		return map;

	}

	/**
	 * 查询实时摄像头故障车辆
	 * @author wzd
	 * @date 2022/1/6 9:54
	 * @param request
	 * @return java.lang.Object
	 */
	@RequestMapping("/listCurrentCameraAlarm")
	public Object listCurrentCameraAlarm(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();

		String headID = request.getParameter("headID");
		String subID = request.getParameter("subID");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");

		if (StringUtils.isNotEmpty(headID)){
			map.remove("authLeave");
			map.put("headID",headID);
		}
		if (StringUtils.isNotEmpty(subID)){
			map.remove("authLeave");
			map.put("subID",subID);
		}

		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		List<CameraAlarm> list = timerTaskService.listCameraAlarm(map);

		map.clear();
		map.put("code",0);
		map.put("total",pageHelper.getTotal());
		map.put("list",list);
		return map;
	}


	@RequestMapping("/exportCurrentCameraAlarm")
	public Object exportCurrentCameraAlarm(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = getSessionInfo();

		String headID = request.getParameter("headID");
		String subID = request.getParameter("subID");

		if (StringUtils.isNotEmpty(headID)) {
			map.remove("authLeave");
			map.put("headID", headID);
		}
		if (StringUtils.isNotEmpty(subID)) {
			map.remove("authLeave");
			map.put("subID", subID);
		}

		List<CameraAlarm> list = timerTaskService.listCameraAlarm(map);

		try {
			EasyExcelUtil.exportExcel(response,new CameraAlarm(),"摄像头故障",list);
			map.clear();
			map.put("code",0);
		}catch (Exception e){
			e.printStackTrace();
			map.clear();
			map.put("code",1);
		}

		return map;
	}

	/**
	 * 根据busID查询摄像头故障报警记录
	 * @author wzd
	 * @date 2022/1/6 9:59
	 * @param request
	 * @return java.lang.Object
	 */
	@RequestMapping("/listHistoryCameraAlarm")
	public Object listHistoryCameraAlarm(HttpServletRequest request) throws ParseException {

		Map<String, Object> map = getSessionInfo();
		String busID = request.getParameter("busID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		map.put("busID", busID);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("tableName","data_history_topsun_" + sdf.format(sdf.parse(startTime))+".bus_"+busID);

		List<CameraAlarm> list = timerTaskService.listHistoryCameraAlarm(map);

		map.clear();
		map.put("code",0);
		map.put("total",pageHelper.getTotal());
		map.put("list",list);
		return map;
	}


	/**
	 * 离线位移
	 * @author wzd
	 * @date 2022/1/13 11:12
	 * @param request
	 * @return java.lang.Object
	 */
	@RequestMapping("/listOfflineDisplacement")
	public Object listOfflineDisplacement(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String plateNO = request.getParameter("plateNO");
//		String roulesID = request.getParameter("roulesID");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");

		map.put("startTime",startTime);
		map.put("endTime",endTime);
		map.put("plateNO",plateNO);
		map.put("subID",subID);
		map.put("headID",headID);

		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		List<OfflineDisplacement> list = timerTaskService.selectOffline(map);

		map.clear();
		map.put("list",list);
		map.put("total",pageHelper.getTotal());
		map.put("code",0);
		return map;
	}

	/**
	 * 离线位移导出
	 * @author wzd
	 * @date 2022/1/13 11:12
	 * @param request
	 * @return java.lang.Object
	 */
	@RequestMapping("/exportOfflineDisplacement")
	public Object exportOfflineDisplacement(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = getSessionInfo();

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String plateNO = request.getParameter("plateNO");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");

		map.put("startTime",startTime);
		map.put("endTime",endTime);
		map.put("plateNO",plateNO);
		map.put("subID",subID);
		map.put("headID",headID);

		List<OfflineDisplacement> list = timerTaskService.selectOffline(map);

		try {
			EasyExcelUtil.exportExcel(response,new OfflineDisplacement(),"离线位移",list);
			map.clear();
			map.put("code",0);
		}catch (Exception e){
			e.printStackTrace();
			map.clear();
			map.put("code",1);
		}
		return map;
	}


//	@RequestMapping("/listOfflineDisplacement")
//	public Object listOfflineDisplacement(HttpServletRequest request) throws ParseException {
//		Map<String,Object> map = getSessionInfo();
//
//		String startTime = request.getParameter("startTime");
//		String endTime = request.getParameter("endTime");
//		String plateNO = request.getParameter("plateNO");
//		String roulesID = request.getParameter("roulesID");
//		String subID = request.getParameter("subID");
//		String headID = request.getParameter("headID");
//		Integer page = Integer.parseInt(request.getParameter("page"));
//		Integer limit = Integer.parseInt(request.getParameter("limit"));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//
//		map.put("startTime", startTime);
//		map.put("endTime", endTime);
//		map.put("plateNO", plateNO);
//		map.put("roulesID",roulesID);
//		map.put("subID",subID);
//		map.put("headID",headID);
//
//		String sign = startTime.replaceAll("-| |:", "")+"_"+endTime.replaceAll("-| |:", "")+headID+subID+plateNO;
//		System.out.println(sign);
//		ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//		//从redis获取菜单数据
//		List<OfflineDisplacement> data1 = (List<OfflineDisplacement>) valueOperations.get(sign);
//		List<OfflineDisplacement> data = new ArrayList<>();
//		if (CollectionUtils.isEmpty(data1)){
//			int i = 0;
//			List<OfflineDisplacement> list = timerTaskService.listOfflineDisplacement(map);
//			for(OfflineDisplacement offlineDisplacement : list){
//				List<DataLocation> offlineList = new ArrayList<>();
//				List<DataLocation> offlineList2 = new ArrayList<>();
//				Integer busID = offlineDisplacement.getBusID();
//				String time = offlineDisplacement.getStartTime();
//				String time2 = offlineDisplacement.getEndTime();
//				//离线的经纬度
//				offlineList = timerTaskService.getHistoryByBusID("data_history_topsun_" + sdf.format(sdf.parse(startTime))+".bus_"+busID, busID, time);
//				//上线的经纬度
//				offlineList2 = timerTaskService.getHistoryByBusID("data_history_topsun_" + sdf.format(sdf.parse(startTime))+".bus_"+busID, busID, time2);
//
//				//开始经纬度
//				String beginLatitude = offlineList.get(0).getLatitude();
//				String beginLongitude = offlineList.get(0).getLongitude();
//				//结束经纬度
//				String endLatitude = offlineList2.get(0).getLatitude();
//				String endLongitude = offlineList2.get(0).getLongitude();
//
//				double distance = getDistance(beginLatitude, beginLongitude, endLatitude, endLongitude);
//				if (distance > 1){
//					offlineDisplacement.setNumber(i+1+"");
//					offlineDisplacement.setAlarmType("离线位移");
//					offlineDisplacement.setBeginLongitude(beginLongitude);
//					offlineDisplacement.setBeginLatitude(beginLatitude);
//					offlineDisplacement.setEndLatitude(endLatitude);
//					offlineDisplacement.setEndLongitude(endLongitude);
//
//					data.add(offlineDisplacement);
//					i++;
//				}
//			}
//			data1 = data;
//			//将数据设置到redis中
//			valueOperations.set(sign, data1);
//		}
//
//		map.clear();
//		int fromIndex = 0;
//		int toIndex = 0;
//		if(data1 != null && data1.size() > 0) {
//			int count = data1.size();
//			if (count < limit){
//				toIndex = limit;
//			}else {
//				fromIndex = (page - 1) * limit;
//				toIndex = page * limit;
//				if (toIndex > count) {
//					toIndex = count;
//				}
//			}
//
//			map.put("total",data1.size());
//			map.put("list", data1.subList(fromIndex,toIndex));
//		}else {
//			map.put("total", 0);
//			map.put("list", data);
//		}
//
//		return map;
//	}
//
//
//	@RequestMapping("/exportOfflineDisplacement")
//	public Object exportOfflineDisplacement(HttpServletRequest request, HttpServletResponse response) throws ParseException {
//		Map<String, Object> map = getSessionInfo();
//
//		String startTime = request.getParameter("startTime");
//		String endTime = request.getParameter("endTime");
//		String plateNO = request.getParameter("plateNO");
//		String roulesID = request.getParameter("roulesID");
//		String subID = request.getParameter("subID");
//		String headID = request.getParameter("headID");
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//
//		map.put("startTime", startTime);
//		map.put("endTime", endTime);
//		map.put("plateNO", plateNO);
//		map.put("roulesID",roulesID);
//		map.put("subID",subID);
//		map.put("headID",headID);
//
//		List<OfflineDisplacement> list = timerTaskService.listOfflineDisplacement(map);
//
//		List<OfflineDisplacement> data = new ArrayList<>();
//
//		int i = 0;
//		for (OfflineDisplacement offlineDisplacement : list) {
//			List<DataLocation> offlineList = new ArrayList<>();
//			List<DataLocation> offlineList2 = new ArrayList<>();
//			Integer busID = offlineDisplacement.getBusID();
//			String time = offlineDisplacement.getStartTime();
//			String time2 = offlineDisplacement.getEndTime();
//			//离线的经纬度
//			offlineList = timerTaskService.getHistoryByBusID("data_history_topsun_" + sdf.format(sdf.parse(startTime)) + ".bus_" + busID, busID, time);
//			//上线的经纬度
//			offlineList2 = timerTaskService.getHistoryByBusID("data_history_topsun_" + sdf.format(sdf.parse(startTime)) + ".bus_" + busID, busID, time2);
//
//			//开始经纬度
//			String beginLatitude = offlineList.get(0).getLatitude();
//			String beginLongitude = offlineList.get(0).getLongitude();
//			//结束经纬度
//			String endLatitude = offlineList2.get(0).getLatitude();
//			String endLongitude = offlineList2.get(0).getLongitude();
//
//			double distance = getDistance(beginLatitude, beginLongitude, endLatitude, endLongitude);
//			if (distance > 1) {
//				offlineDisplacement.setNumber(i + 1 + "");
//				offlineDisplacement.setAlarmType("离线位移");
//				offlineDisplacement.setBeginLongitude(beginLongitude);
//				offlineDisplacement.setBeginLatitude(beginLatitude);
//				offlineDisplacement.setEndLatitude(endLatitude);
//				offlineDisplacement.setEndLongitude(endLongitude);
//				offlineDisplacement.setTimes(toDhmsStyle(Integer.parseInt(offlineDisplacement.getTimes())));
//
//				data.add(offlineDisplacement);
//				i++;
//			}
//		}
//		try {
//			EasyExcelUtil.exportExcel(response,new OfflineDisplacement(),"离线位移",data);
//			map.clear();
//			map.put("code",0);
//		}catch (Exception e){
//			e.printStackTrace();
//			map.clear();
//			map.put("code",1);
//		}
//
//		return map;
//	}


	/**
	 * 七天未上线
	 * @author wzd
	 * @date 2021/11/25 14:26
	 * @param request
	 * @return java.lang.Object
	 */
	@RequestMapping("/offline")
	public Object offline(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();

		String companyID = request.getParameter("companyID");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");


		if (null != companyID && !"".equals(companyID)){
			map.put("companyID", companyID);
		}
		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}
		List<Offline> list = timerTaskService.getOffline(map);

		map.clear();
		map.put("list", list);
		map.put("total", pageHelper.getTotal());

		return map;
	}


	@RequestMapping("/exportOffline")
	public Object exportOffline(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {
		Map<String, Object> map = getSessionInfo();

		String companyID = request.getParameter("companyID");

		if (null != companyID && !"".equals(companyID)){
			map.put("companyID", companyID);
		}

		List<Offline> list = timerTaskService.getOffline(map);

		for (Offline offline : list){
			String duration = toDhmsStyle(Integer.parseInt(offline.getDuration()));
			String location = LocationUtil.getposition(offline.getLongitude(), offline.getLatitude());
			offline.setDuration(duration);
			offline.setLocation(location);
		}

		map.clear();
		try {
			export(list,response,"七天未上线车辆");
			map.put("code", 0);
		}catch (Exception e){
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	/**
	 * 断电报警查询（实时）
	 * @author wzd
	 * @date 2021/12/1 11:28
	 * @param request
	 * @return java.lang.Object
	 */
	@RequestMapping("/listOutage")
	public Object listOutage(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();
		String companyID = request.getParameter("companyID");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");

		if (null != companyID && !"".equals(companyID)){
			map.put("companyID", companyID);
		}
		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		List<Offline> list = timerTaskService.listOutage(map);

		map.clear();
		map.put("list", list);
		map.put("total", pageHelper.getTotal());

		return map;
	}

	/**
	 * 断电报警历史
	 * @author wzd
	 * @date 2021/12/1 11:52
	 * @param request
	 * @return java.lang.Object
	 */
	@RequestMapping("/listOutageHistory")
	public Object listOutageHistory(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = getSessionInfo();
		String busID = request.getParameter("busID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		map.put("busID", busID);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("endTime", endTime);
		map.put("tableName", "data_history_topsun_" + sdf.format(sdf.parse(startTime)) + ".bus_" + busID);

		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		List<Offline> list = timerTaskService.listOutageHistory(map);

		map.clear();
		map.put("list", list);
		map.put("total", pageHelper.getTotal());

		return map;
	}

	@RequestMapping("/exportOutage")
	public Object exportOutage(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {
		Map<String, Object> map = getSessionInfo();

		String companyID = request.getParameter("companyID");
		if (null != companyID && !"".equals(companyID)){
			map.put("companyID", companyID);
		}
		List<Offline> list = timerTaskService.listOutage(map);

		for (Offline offline : list){
			String location = LocationUtil.getposition(offline.getLongitude(), offline.getLatitude());
			offline.setLocation(location);
		}

		map.clear();
		try {
			exportExcel(list,response,"断电报警");
			map.put("code", 0);
		}catch (Exception e){
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/getGpsAlarm")
	public Object getGpsAlarm(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = getSessionInfo();

		String busID = request.getParameter("busID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");

		if(StringUtils.isNotEmpty(busID)) {
			map.put("busIDs", busID.split(","));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("tableName", "alarmType_"+sdf.format(sdf.parse(startTime)));


		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		List<AlarmType> list = timerTaskService.getGpsAlarm(map);

		map.clear();
		map.put("list",list);
		map.put("total", pageHelper.getTotal());

		return map;
	}

	@RequestMapping("/exportGpsAlarm")
	public Object exportGpsAlarm(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = getSessionInfo();

		String busID = request.getParameter("busID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		if(StringUtils.isNotEmpty(busID)) {
			map.put("busID", busID.split(","));
		}

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("tableName", "alarmType_"+new SimpleDateFormat("yyyy").format(new Date()));

		List<AlarmType> list = timerTaskService.getGpsAlarm(map);
		for (AlarmType alarmType : list){
			if (alarmType.getTimes() != null){
				String times = toDhmsStyle(Integer.valueOf(alarmType.getTimes()));
				alarmType.setTimes(times);
			}
		}

		try {
			EasyExcelUtil.exportExcel(response,new AlarmType(),"GPS未接报警",list);
			map.clear();
			map.put("code",0);
		}catch (Exception e){
			e.printStackTrace();
			map.clear();
			map.put("code",1);
		}

		return map;
	}




	/**
	 * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
	 * 参数为String类型
	 * @date 2022/1/10 10:05
	 * @param lat1Str
	 * @param lng1Str
	 * @param lat2Str
	 * @param lng2Str
	 * @return double
	 */
	public static double getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {

		double EARTH_RADIUS = 6378.137;

		Double lat1 = Double.parseDouble(lat1Str);
		Double lng1 = Double.parseDouble(lng1Str);
		Double lat2 = Double.parseDouble(lat2Str);
		Double lng2 = Double.parseDouble(lng2Str);

		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double difference = radLat1 - radLat2;
		double mdifference = rad(lng1) - rad(lng2);
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)* Math.pow(Math.sin(mdifference / 2), 2)));
		distance = distance * EARTH_RADIUS;
		double distanceStr = distance;

		return distanceStr;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}


	public void export(String startTime, String endTime, List<Round> list, HttpServletResponse response, String title, String isGps,String speed) {

		try {

			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String fileName = URLEncoder.encode(StringUtils.trim(title) + ".xls", "UTF-8");
			fileName = StringUtils.replace(fileName, "+", "%20");// 空格转 %20
			response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
			response.setHeader("Access-Control-Allow-Origin","http://ztc.njtopsun.com");//允许所有来源访问
			response.setHeader("Access-Control-Allow-Method","POST,GET");//允许访问的方式
			response.setHeader("Access-Control-Allow-Credentials","true");//允许访问的方式
			WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			SheetSettings sheetSettings = sheet.getSettings();
			sheetSettings.setOrientation(PageOrientation.LANDSCAPE);
			sheetSettings.setTopMargin(0.2);

			sheetSettings.setBottomMargin(0.2);

			sheetSettings.setLeftMargin(0.4);

			sheetSettings.setRightMargin(0);

			sheetSettings.setHorizontalCentre(true);

			sheetSettings.setVerticalFreeze(3);
			sheetSettings.setPrintTitlesRow(0, 2);

//			sheetSettings.setVerticalCentre(true);
			// 设置excel的样式
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			WritableCellFormat format_title = new WritableCellFormat(wf);
			format_title.setWrap(true);
			format_title.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_title.setAlignment(Alignment.CENTRE);
			format_title.setVerticalAlignment(VerticalAlignment.CENTRE);
//			format_title.setBackground(Colour.VERY_LIGHT_YELLOW);


			WritableCellFormat format_title2 = new WritableCellFormat(wf);
			format_title2.setWrap(true);
			format_title2.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_title2.setAlignment(Alignment.RIGHT);
			format_title2.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 样式2
			WritableCellFormat format_content = new WritableCellFormat(NumberFormats.TEXT);
			format_content.setWrap(true);
			format_content.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_content.setFont(new WritableFont(WritableFont.createFont("宋体")));
			format_content.setAlignment(Alignment.CENTRE);
			format_content.setVerticalAlignment(VerticalAlignment.CENTRE);



			int row = 0;// 行
			// 列头
			sheet.mergeCells(0, row, 9, row);

			sheet.addCell(new Label(0, 0, "车辆转弯报警统计（角度参照为正北）", format_title));
			row++;
			sheet.mergeCells(0, row, 9, row);
			sheet.addCell(new Label(0, 1, "查询时间："+startTime+"至"+endTime+"             超速参数:" + speed + "km/h", format_title2));
			row++;

			sheet.addCell(new Label(0, row, "序号", format_title));
			sheet.addCell(new Label(1, row, "车牌号", format_title));
			sheet.addCell(new Label(2, row, "开始时间", format_title));
			sheet.setColumnView(2, 20);
			sheet.addCell(new Label(3, row, "结束时间", format_title));
			sheet.setColumnView(3, 20);
			sheet.addCell(new Label(4, row, "开始角度", format_title));
			sheet.addCell(new Label(5, row, "结束角度", format_title));
			sheet.addCell(new Label(6, row, "转向角度", format_title));
			sheet.addCell(new Label(7, row, "转向方向", format_title));
			sheet.addCell(new Label(8, row, "平均速度", format_title));
			sheet.addCell(new Label(9, row, "转弯位置", format_title));
			sheet.setColumnView(9, 30);

			row++;
			Subject subject = SecurityUtils.getSubject();

			if(list == null || list.size() == 0) {
				sheet.mergeCells(0, row, 9, row);
				sheet.addCell(new Label(0, row, "此时间段无报警记录", format_content));
			}else {
				for (Round round : list) {
					Object stop = subject.getSession().getAttribute("stop");

					if (stop != null && (int) stop == 1) {
						response.getOutputStream().close();
						return;
					}

					sheet.addCell(new Label(0, row, (row - 2) + "", format_content));
					sheet.addCell(new Label(1, row, round.getPlateNO(), format_content));
					sheet.addCell(new Label(2, row, round.getStartTime(), format_content));
					sheet.addCell(new Label(3, row, round.getEndTime(), format_content));
					sheet.addCell(new Label(4, row, round.getStart_direction(), format_content));
					sheet.addCell(new Label(5, row, round.getEnd_direction(), format_content));
					sheet.addCell(new Label(6, row, round.getDirection(), format_content));
					sheet.addCell(new Label(7, row, round.getOrientation(), format_content));
					sheet.addCell(new Label(8, row, round.getSpeed(), format_content));
					if ("1".equals(isGps)) {
						try {
							sheet.addCell(new Label(9, row,
									LocationUtil.getposition(round.getLongitude(), round.getLatitude()), format_content));
						} catch (MalformedURLException e) {
							e.printStackTrace();
							sheet.addCell(new Label(9, row, "未知位置", format_content));
						}
					} else {
						sheet.addCell(new Label(9, row, round.getLongitude()+","+round.getLatitude(), format_content));
					}

					subject.getSession().setAttribute("nowProgress", (row - 2));

					row++;
				}
			}



			wwb.write();
			wwb.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void exportForMonth(List<Round> list, HttpServletResponse response, String title, String time,
							   String speed) {

		try {

			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String fileName = URLEncoder.encode(StringUtils.trim(title) + ".xls", "UTF-8");
			fileName = StringUtils.replace(fileName, "+", "%20");// 空格转 %20
			response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
			WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			SheetSettings sheetSettings = sheet.getSettings();
			sheetSettings.setOrientation(PageOrientation.LANDSCAPE);
			sheetSettings.setTopMargin(0.2);

			sheetSettings.setBottomMargin(0.2);

			sheetSettings.setLeftMargin(0.4);

			sheetSettings.setRightMargin(0);

			sheetSettings.setHorizontalCentre(true);

			sheetSettings.setVerticalFreeze(3);
			sheetSettings.setPrintTitlesRow(0, 2);

//			sheetSettings.setVerticalCentre(true);
			// 设置excel的样式
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			WritableCellFormat format_title = new WritableCellFormat(wf);
			format_title.setWrap(true);
			format_title.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_title.setAlignment(Alignment.CENTRE);
			format_title.setVerticalAlignment(VerticalAlignment.CENTRE);
//			format_title.setBackground(Colour.VERY_LIGHT_YELLOW);

			WritableCellFormat format_title2 = new WritableCellFormat(wf);
			format_title2.setWrap(true);
			format_title2.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_title2.setAlignment(Alignment.RIGHT);
			format_title2.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 样式2
			WritableCellFormat format_content = new WritableCellFormat(NumberFormats.TEXT);
			format_content.setWrap(true);
			format_content.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_content.setFont(new WritableFont(WritableFont.createFont("宋体")));
			format_content.setAlignment(Alignment.CENTRE);
			format_content.setVerticalAlignment(VerticalAlignment.CENTRE);

			int row = 0;// 行
			// 列头
			sheet.mergeCells(0, row, 2, row);

			sheet.addCell(new Label(0, 0, "月度车辆转弯报警统计(" + time + ")", format_title));
			row++;
			sheet.mergeCells(0, row, 2, row);
			sheet.addCell(new Label(0, 1, "当月超速参数:" + speed + "km/h", format_title2));
			row++;

			sheet.addCell(new Label(0, row, "序号", format_title));
			sheet.setColumnView(0, 20);
			sheet.addCell(new Label(1, row, "车牌号", format_title));
			sheet.setColumnView(1, 20);
			sheet.addCell(new Label(2, row, "报警次数", format_title));
			sheet.setColumnView(2, 20);

			row++;

			for (Round round : list) {
				sheet.addCell(new Label(0, row, (row - 2) + "", format_content));
				sheet.addCell(new Label(1, row, round.getPlateNO(), format_content));
				sheet.addCell(new Label(2, row, round.getNum(), format_content));
				row++;
			}

			wwb.write();
			wwb.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 导出七天未上线
	 * @author wzd
	 * @date 2021/11/30 11:21
	 * @param list
	 * @param response
	 * @param title
	 */
	public void export(List<Offline> list, HttpServletResponse response, String title) {

		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String fileName = URLEncoder.encode(StringUtils.trim(title) + ".xls", "UTF-8");
			fileName = StringUtils.replace(fileName, "+", "%20");// 空格转 %20
			response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
			WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			SheetSettings sheetSettings= sheet.getSettings();
			sheetSettings.setOrientation(PageOrientation.LANDSCAPE);
			sheetSettings.setTopMargin(0.2);
			sheetSettings.setBottomMargin(0.2);
			sheetSettings.setLeftMargin(0.2);
			sheetSettings.setRightMargin(0);
			sheetSettings.setHorizontalCentre(true);
			sheetSettings.setVerticalFreeze(2);
			sheetSettings.setPrintTitlesRow(0, 3);

//			sheetSettings.setVerticalCentre(true);
			// 设置excel的样式
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
			WritableCellFormat format_headline = new WritableCellFormat(wf);
			format_headline.setWrap(true);
			format_headline.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_headline.setAlignment(Alignment.CENTRE);
			format_headline.setVerticalAlignment(VerticalAlignment.CENTRE);
			//样式2
			WritableCellFormat format_title = new WritableCellFormat(NumberFormats.TEXT);
			format_title.setWrap(true);
			format_title.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_title.setFont(new WritableFont(WritableFont.createFont("宋体"),10));
			format_title.setAlignment(Alignment.CENTRE);
			format_title.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 样式3
			WritableCellFormat format_content = new WritableCellFormat(NumberFormats.TEXT);
			format_content.setWrap(true);
			format_content.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_content.setFont(new WritableFont(WritableFont.createFont("宋体")));
			format_content.setAlignment(Alignment.CENTRE);
			format_content.setVerticalAlignment(VerticalAlignment.CENTRE);

			int row = 0;// 行
//			int num = 0;
			// 列头
			sheet.mergeCells(0, row, 6, row);

			sheet.addCell(new Label(0, 0,  "七天未上线车辆", format_headline));

			row++;

			sheet.setRowView(0, 500, false);
			sheet.setRowView(1, 500, false);

			for (int j = 0; j < 7; j++) {
				switch (j) {
					case 0:
						sheet.setColumnView(j, 5);
						sheet.addCell(new Label(j, row, "序号", format_title));
						break;
					case 1:
						sheet.setColumnView(j, 10);
						sheet.addCell(new Label(j, row, "车牌号", format_title));
						break;
					case 2:
						sheet.setColumnView(j, 15);
						sheet.addCell(new Label(j, row, "终端编号", format_title));
						break;
					case 3:
						sheet.setColumnView(j, 10);
						sheet.addCell(new Label(j, row, "所属公司", format_title));
						break;
					case 4:
						sheet.setColumnView(j, 20);
						sheet.addCell(new Label(j, row, "离线时间", format_title));
						break;
					case 5:
						sheet.setColumnView(j, 25);
						sheet.addCell(new Label(j, row, "离线地点", format_title));
						break;
					case 6:
						sheet.setColumnView(j, 20);
						sheet.addCell(new Label(j, row, "离线时长", format_title));
						break;
				}
			}

			row ++;// 行

			for (int n = 0; n < list.size(); n++) {
				sheet.setRowView(row, 500, false);

				sheet.addCell(new Label(0, row, (n + 1) + "", format_content));

				Offline offline = list.get(n);

				sheet.addCell(new Label(1, row, offline.getPlateNO(), format_content));

				sheet.addCell(new Label(2, row, offline.getSimCard(), format_content));

				sheet.addCell(new Label(3, row, offline.getCompanyName(), format_content));

				sheet.addCell(new Label(4, row, offline.getServer_time(), format_content));

				String location = offline.getLocation();
				if (null != location && !"".equals(location)) {
					sheet.addCell(new Label(5, row, location, format_content));
				}else {
					sheet.addCell(new Label(5, row, "---", format_content));
				}
				sheet.addCell(new Label(6, row, offline.getDuration(), format_content));

				row ++;
			}
			wwb.write();
			wwb.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出断电报警
	 * @author wzd
	 * @date 2021/11/30 14:59
	 * @param list
	 * @param response
	 * @param title
	 */
	public void exportExcel(List<Offline> list, HttpServletResponse response, String title) {

		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String fileName = URLEncoder.encode(StringUtils.trim(title) + ".xls", "UTF-8");
			fileName = StringUtils.replace(fileName, "+", "%20");// 空格转 %20
			response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
			WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			SheetSettings sheetSettings= sheet.getSettings();
			sheetSettings.setOrientation(PageOrientation.LANDSCAPE);
			sheetSettings.setTopMargin(0.2);
			sheetSettings.setBottomMargin(0.2);
			sheetSettings.setLeftMargin(0.2);
			sheetSettings.setRightMargin(0);
			sheetSettings.setHorizontalCentre(true);
			sheetSettings.setVerticalFreeze(2);
			sheetSettings.setPrintTitlesRow(0, 3);

//			sheetSettings.setVerticalCentre(true);
			// 设置excel的样式
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
			WritableCellFormat format_headline = new WritableCellFormat(wf);
			format_headline.setWrap(true);
			format_headline.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_headline.setAlignment(Alignment.CENTRE);
			format_headline.setVerticalAlignment(VerticalAlignment.CENTRE);
			//样式2
			WritableCellFormat format_title = new WritableCellFormat(NumberFormats.TEXT);
			format_title.setWrap(true);
			format_title.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_title.setFont(new WritableFont(WritableFont.createFont("宋体"),10));
			format_title.setAlignment(Alignment.CENTRE);
			format_title.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 样式3
			WritableCellFormat format_content = new WritableCellFormat(NumberFormats.TEXT);
			format_content.setWrap(true);
			format_content.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_content.setFont(new WritableFont(WritableFont.createFont("宋体")));
			format_content.setAlignment(Alignment.CENTRE);
			format_content.setVerticalAlignment(VerticalAlignment.CENTRE);

			int row = 0;// 行
			// 列头
			sheet.mergeCells(0, row, 6, row);
			sheet.addCell(new Label(0, 0,  "断电报警车辆", format_headline));
			row++;
			sheet.setRowView(0, 500, false);
			sheet.setRowView(1, 500, false);

			for (int j = 0; j < 7; j++) {
				switch (j) {
					case 0:
						sheet.setColumnView(j, 5);
						sheet.addCell(new Label(j, row, "序号", format_title));
						break;
					case 1:
						sheet.setColumnView(j, 10);
						sheet.addCell(new Label(j, row, "车牌号", format_title));
						break;
					case 2:
						sheet.setColumnView(j, 15);
						sheet.addCell(new Label(j, row, "终端编号", format_title));
						break;
					case 3:
						sheet.setColumnView(j, 10);
						sheet.addCell(new Label(j, row, "所属公司", format_title));
						break;
					case 4:
						sheet.setColumnView(j, 20);
						sheet.addCell(new Label(j, row, "报警类型", format_title));
						break;
					case 5:
						sheet.setColumnView(j, 25);
						sheet.addCell(new Label(j, row, "报警时间", format_title));
						break;
					case 6:
						sheet.setColumnView(j, 20);
						sheet.addCell(new Label(j, row, "离线地点", format_title));
						break;
				}
			}
			row ++;// 行
			for (int n = 0; n < list.size(); n++) {
				sheet.setRowView(row, 500, false);

				sheet.addCell(new Label(0, row, (n + 1) + "", format_content));

				Offline offline = list.get(n);
				sheet.addCell(new Label(1, row, offline.getPlateNO(), format_content));

				sheet.addCell(new Label(2, row, offline.getSimCard(), format_content));

				sheet.addCell(new Label(3, row, offline.getCompanyName(), format_content));

				sheet.addCell(new Label(4, row, offline.getAlarmType(), format_content));

				sheet.addCell(new Label(5, row, offline.getServer_time(), format_content));

				String location = offline.getLocation();
				if (null != location && !"".equals(location)) {
					sheet.addCell(new Label(6, row, location, format_content));
				}else {
					sheet.addCell(new Label(6, row, "---", format_content));
				}
				row ++;
			}
			wwb.write();
			wwb.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static String toDhmsStyle(Integer allSeconds) {
		String DateTimes = null;

		long days = allSeconds / (60 * 60 * 24);
		long hours = (allSeconds % (60 * 60 * 24)) / (60 * 60);
		long minutes = (allSeconds % (60 * 60)) / 60;
		long seconds = allSeconds % 60;

		if (days > 0) {
			DateTimes = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
		} else if (hours > 0) {
			DateTimes = hours + "小时" + minutes + "分钟" + seconds + "秒";
		} else if (minutes > 0) {
			DateTimes = minutes + "分钟" + seconds + "秒";
		} else {
			DateTimes = seconds + "秒";
		}

		return DateTimes;
	}


	@RequestMapping("/listBusMileage")
	public Object listBusMileage(HttpServletRequest request) throws ParseException {

		Map<String, Object> map = getSessionInfo();

		String busID = request.getParameter("busID");

		String startTime = request.getParameter("startTime");

		String endTime = request.getParameter("endTime");
		Date d = sdf1.parse(startTime);

		Calendar c = Calendar.getInstance();

		c.setTime(d);
		int year = c.get(Calendar.YEAR);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("year","data_history_topsun_"+year);
		String[] bStrings = busID.split(",");
		List<DataLocation> list = new ArrayList<DataLocation>();
		for(int i=0;i<bStrings.length;i++){
			map.put("busID", bStrings[i]);
			map.put("tableName", "bus_"+bStrings[i]);
			List<DataLocation> list2 = dataLocationService.listBusMileage(map);
			list.addAll(list2);
		}
		map.clear();
		map.put("list", list);
		map.put("code", 0);

		return map;
	}
}
