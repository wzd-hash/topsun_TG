package com.topsun.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.topsun.model.OperationModel;
import com.topsun.service.ExportService;
import com.topsun.util.EasyExcelUtil;

@RestController
@RequestMapping("/expot")
public class ExportExcelTools extends BaseController{
	
	@Autowired
	private ExportService exportService;
	
	@RequestMapping("/listOperation")
	public Object listOperation(HttpServletRequest request, HttpServletResponse response) {
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String busID = request.getParameter("busID");
		
		Map<String, Object> sessionInfo = getSessionInfo();
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(busID)) {
			sessionInfo.put("busIDs", busID.split(","));
		}
		if (StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 09:00:00");  
			Date date = calendar.getTime();
			endTime = sdf.format(date);
	      
		    calendar.add(Calendar.DATE, -1);    //得到前一天        
		    Date date1 = calendar.getTime();        
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 17:00:00");    
		    startTime = df.format(date1);
		}
	    

		//查询运维统计数据
		sessionInfo.put("startTime", startTime);
		sessionInfo.put("endTime", endTime);
		List<OperationModel> list = exportService.listOperation(sessionInfo);

		try {
			EasyExcelUtil.exportOperationExcel(response, new OperationModel(), "推送统计", list);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	@RequestMapping("/operationList")
	public Object operationList(HttpServletRequest request, HttpServletResponse response) {
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		String busID = request.getParameter("busID");
		
		Map<String, Object> sessionInfo = getSessionInfo();
		
		if (StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 09:00:00");  
			Date date = calendar.getTime();
			endTime = sdf.format(date);
	      
		    calendar.add(Calendar.DATE, -1);    //得到前一天        
		    Date date1 = calendar.getTime();        
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 17:00:00");    
		    startTime = df.format(date1);
		}
		Page<Object> startPage =null;
		if (!StringUtils.isEmpty(page)&&!StringUtils.isEmpty(limit)) {
			startPage= PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}
		//查询运维统计数据
		sessionInfo.put("startTime", startTime);
		sessionInfo.put("endTime", endTime);
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(busID)) {
			sessionInfo.put("busIDs", busID.split(","));
		}
		
		List<OperationModel> list = exportService.listOperation(sessionInfo);

		sessionInfo.clear();
		sessionInfo.put("list", list);
		sessionInfo.put("total", startPage.getTotal());
		
		return sessionInfo;
	}
	
	
}
