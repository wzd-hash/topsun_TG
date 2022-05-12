package com.topsun.service;

import java.util.List;
import java.util.Map;

import com.topsun.entity.Log;

public interface LogService {
	public boolean addLog(Log log); 
	public List<Log> getLog(Map<String, Object> map); 
	public void exportLog(Map<String, Object> map) throws Exception; 
}
