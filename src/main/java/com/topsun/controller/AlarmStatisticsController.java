package com.topsun.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.topsun.entity.AlarmStatistics;
import com.topsun.service.AlarmService;
import com.topsun.service.TimerTaskService;

@RestController
@RequestMapping("/alarmStatistics")
public class AlarmStatisticsController extends BaseController {

	
	@Autowired
	private TimerTaskService timerTaskService;
	
	@Autowired
	private AlarmService alarmService;

	@RequestMapping("/getStatistics")
	public Object getStatistics(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();

		String time = request.getParameter("time");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		String busID = request.getParameter("busID");
		String roulesID = request.getParameter("roulesID");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");

		map.put("time", time);
		map.put("page", page);
		map.put("limit", limit);
		map.put("busIDs", busID.split(","));
		map.put("roulesID", roulesID);
		map.put("subID", subID);
		map.put("headID", headID);

		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}
		
		List<AlarmStatistics> list = timerTaskService.getAlarmStatistics(map);
		
		for (AlarmStatistics alarmStatistics : list) {
			if(alarmStatistics != null) {
				BigDecimal b = new BigDecimal(alarmStatistics.getGPS());
				alarmStatistics.setGPS( b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				BigDecimal b1 = new BigDecimal(alarmStatistics.getDSM());
				alarmStatistics.setDSM(b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				BigDecimal b2 = new BigDecimal(alarmStatistics.getBSD());
				alarmStatistics.setBSD(b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				Double move = alarmStatistics.getMOVE();
				BigDecimal b3 = new BigDecimal(move == null? 0 :move);
				alarmStatistics.setMOVE(b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		
		
		map.clear();
		map.put("total", pageHelper.getTotal());
		map.put("list", list);

		return map;

	}

	@RequestMapping("/exportStatistics")
	public Object exportStatistics(HttpServletRequest request,
			HttpServletResponse httpServletResponse) {
		Map<String, Object> map = getSessionInfo();

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String busID = request.getParameter("busID");
		String roulesID = request.getParameter("roulesID");
		String subID = request.getParameter("subID");
		String headID = request.getParameter("headID");
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("busIDs", busID.split(","));
		map.put("roulesID", roulesID);
		map.put("subID", subID);
		map.put("headID", headID);
		map.put("Response", httpServletResponse);

		Integer exportStatistics = alarmService.exportStatistics(map);

		map.clear();
		map.put("code", exportStatistics);

		return map;

	}

}
