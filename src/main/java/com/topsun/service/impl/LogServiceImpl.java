package com.topsun.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsun.entity.Log;
import com.topsun.mapper.LogMapper;
import com.topsun.model.LogModel;
import com.topsun.service.LogService;
import com.topsun.util.EasyExcelUtil;
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logMapper;
	
	public boolean addLog(Log log) {
		
		Integer insertLog = logMapper.insertLog(log);
		
		if(insertLog == 1) {
			return true;
		}
		
		return false;
	}

	public List<Log> getLog(Map<String, Object> map) {
		
		List<Log> logList = logMapper.getLogList(map);
		
		return logList;
	}

	public void exportLog(Map<String, Object> map) throws Exception {
		
		//获取日志信息
		List<Log> logList = logMapper.getLogList(map);
		//封装成list<list<String>>类型
		List<List<String>> data = getData(logList);
		
		
		
		EasyExcelUtil.writeExcel((HttpServletResponse)map.get("response"), new LogModel(), "日志信息", data);
		


	}

	private List<List<String>> getData(List<Log> logList) {


		List<List<String>> data = new ArrayList<>();
		
		for (int i = 0 ; i < logList.size();i++) {
			List<String> item = new ArrayList<>();
			
			item.add(String.valueOf(i+1));
			item.add(logList.get(i).getUserName());
			if(logList.get(i).getSubID() == null || logList.get(i).getSubID() == 0) {
				item.add(logList.get(i).getHeadName());
			}else {
				item.add(logList.get(i).getSubName());
			}
			item.add(logList.get(i).getContent());
			item.add(logList.get(i).getCrateTime());
			switch(logList.get(i).getOptType()){
				case 1 : item.add("登陆");break;
				case 2 : item.add("增加");break;
				case 3 : item.add("修改");break;
				case 4 : item.add("删除");break;
			}
			item.add(logList.get(i).getLoginIP());
			
			
			data.add(item);
		}
		
		
		return data;
	}

	

}
