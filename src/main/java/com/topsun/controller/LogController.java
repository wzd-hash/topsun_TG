package com.topsun.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.topsun.entity.Log;
import com.topsun.service.LogService;
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequestMapping("/getLog")
	public Object getLog(HttpServletRequest request) {
		
		Map<String, Object> map = getSessionInfo();

		String time = request.getParameter("time");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		String headID = request.getParameter("headID");
		String subID = request.getParameter("subID");
		Page<Object> startPage =null;
		if (!StringUtils.isEmpty(page)&&!StringUtils.isEmpty(limit)) {
			startPage= PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}
		
		map.put("time", time);
		map.put("headID", headID);
		map.put("subID", subID);
		List<Log> log = logService.getLog(map);
		
		map.clear();
		
		map.put("code", 1);
		map.put("list", log);
		if(startPage != null) {
			map.put("total", startPage.getTotal());
		}else {
			map.put("total", log.size());
		}
		
		return map;
		
	}
	
	@RequestMapping("/exportLog")
	public Object exportLog(HttpServletResponse response,HttpServletRequest request) {
		
		
		Map<String, Object> map = getSessionInfo();
		String time = request.getParameter("time");
		String headID = request.getParameter("headID");
		String subID = request.getParameter("subID");
		
		
		map.put("time", time);
		map.put("headID", headID);
		map.put("subID", subID);
		try {
			map.put("response", response);
			logService.exportLog(map);
			
			return null;
		
		}catch(Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("code", 4);
			map.put("msg", "服务器出错，导出失败");
		}
		
		return map;
		
	}
}
