package com.topsun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.topsun.entity.Log;
@Mapper
public interface LogMapper {

	public Integer insertLog(Log log);
	
	public List<Log> getLogList(Map<String, Object> map);
	
}
