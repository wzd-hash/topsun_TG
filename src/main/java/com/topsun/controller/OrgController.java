package com.topsun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topsun.entity.BusInfo;
import com.topsun.entity.HeadCompany;
import com.topsun.service.OrgService;

@RestController
@RequestMapping("/org")
public class OrgController extends BaseController{

	@Autowired
	private OrgService orgService;
	
	
	
	/*
	 * 组织树查询第一层
	 * 总公司-分公司-线路
	 */
	@RequestMapping("/listOrgTreeRoules")
	public Object listOrgTreeRoules(HttpServletRequest request){
		
		Map<String, Object> sessionMap = getSessionInfo();
		Map<String, Object> resultMap = new HashMap<>();
		
		
		List<HeadCompany> list = orgService.listOrgTreeRoules(sessionMap);
		
		
		resultMap.put("list", list);
		return resultMap;
	}
	
	@RequestMapping("/listOrgTreeBus")
	public Object listOrgTreeBus(HttpServletRequest request){
		
		String roulesID = request.getParameter("roulesID");
		
		String plateNO = request.getParameter("plateNO");
		
		String online = request.getParameter("type");//0-全部 1-行驶 2-离线 3-停车 4-异常 5-不定位
		
		Map<String, Object> resultMap = getSessionInfo();
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(roulesID)) {
			
			resultMap.put("roulesID", roulesID.split(","));
		}
		resultMap.put("plateNO", plateNO);
		resultMap.put("online", online);
		List<BusInfo> list = orgService.listOrgTreeBus(resultMap);
		
		resultMap.clear();
		resultMap.put("list", list);
		
		return resultMap;
	}
}
