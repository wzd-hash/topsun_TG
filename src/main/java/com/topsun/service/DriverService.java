package com.topsun.service;

import java.util.List;
import java.util.Map;

import com.topsun.entity.DriverInfo;

public interface DriverService {

	public List<DriverInfo> getDriver(Map<String, Object> map);
}
