package com.topsun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.topsun.entity.Check;

@Mapper
public interface CheckMapper {

//	void addCheck(Map<String, Object> map);

	List<Check> getChecks(Map<String, Object> map);

//	void updateChecks(Map<String, Object> map);

}
