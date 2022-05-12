package com.topsun.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.topsun.entity.BusInfo;
import com.topsun.service.BusService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bus")
public class BusController extends BaseController{
	
	@Autowired
	private BusService busService;
	
	/*
	 * 查询车辆
	 * 2019-12-11
	 */
	@RequestMapping("/listBusInfo")
	public Object listBusInfo(HttpServletRequest request){
		
		Map<String, Object> map = getSessionInfo();
		
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		String plateNO = request.getParameter("plateNO");
		String busID = request.getParameter("busID");
		
		String companyID = request.getParameter("companyID");
		String authLeave = request.getParameter("authLeave");
		@SuppressWarnings("resource")
		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page)&&StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}
		map.put("plateNO", plateNO);
		if (StringUtils.isNotEmpty(busID)) {
			map.put("busID", busID.split(","));
		}
		if (StringUtils.isNotEmpty(authLeave)&&StringUtils.isNotEmpty(companyID)) {
			if(Integer.parseInt(authLeave) >= (Integer)map.get("authLeave")) {
				map.put("companyID", companyID);
				map.put("authLeave", authLeave);
			}
		}
		List<BusInfo> list = busService.listBusInfo(map);
		
		map.clear();
		map.put("list", list);
		map.put("total", pageHelper.getTotal());
		map.put("code", 0);
		
		return map;
	}

	@RequestMapping("/getBusInfoByBusID")
	public Object getBusInfoByBusID(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();
		String busID = request.getParameter("busID");

		map.put("busID", busID);

		List<BusInfo> list = busService.getBusInfoByBusID(map);

		map.clear();
		map.put("list", list);
		map.put("code", 0);

		return map;
	}
	
}
