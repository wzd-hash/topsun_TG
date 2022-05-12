package com.topsun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsun.entity.DriverInfo;
import com.topsun.mapper.DriverMapper;
import com.topsun.service.DriverService;


@Service
public class DriverServiceImpl implements DriverService {


	@Autowired
	private DriverMapper driverMapper;
	
	
	
	
	
	public List<DriverInfo> getDriver(Map<String, Object> map) {
		
		return driverMapper.getDriver(map);
		
	}

	

}
