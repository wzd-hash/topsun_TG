package com.topsun.mapper;

import com.topsun.entity.BusInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BusMapper {
//
	List<BusInfo> listBusInfo(@Param("map")Map<String, Object> map);
//
	Integer getOnLineBus(Map<String, Object> map);

	Integer getAllBusNum(Map<String, Object> map);

	Integer getRunBusNum(Map<String, Object> map);

	Integer getExceptionBus(Map<String, Object> map);

	Integer getNoGpsNum(Map<String, Object> map);

	List<String> getBusIDByHeadId();

//	Integer getOffLineNum(Map<String, Object> map);
//	
	Integer getWorkNum(Map<String, Object> map);
	
	List<String> getAllBusID();

    List<BusInfo> getBusInfoByBusID(Map<String, Object> map);
}
