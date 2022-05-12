package com.topsun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsun.entity.Check;
import com.topsun.mapper.CheckMapper;
import com.topsun.service.CheckService;

@Service
public class CheckServiceImpl implements CheckService {
	
	@Autowired
	private CheckMapper checkMapper;

//	@Override
//	public void addCheck(Map<String, Object> map) {
//
//		checkMapper.addCheck(map);
//		
//	}

	@Override
	public List<Check> getChecks(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return checkMapper.getChecks(map);
	}

//	@Override
//	public void updateChecks(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		checkMapper.updateChecks(map);
//		
//	}
	
}
