package com.topsun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.topsun.entity.DriverInfo;
@Mapper
public interface DriverMapper {

	public List<DriverInfo> getDriver(Map<String, Object> map);
//	
	
}
