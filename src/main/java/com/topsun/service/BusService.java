package com.topsun.service;

import com.topsun.entity.BusInfo;

import java.util.List;
import java.util.Map;

public interface BusService {
	
	List<BusInfo> listBusInfo(Map<String, Object> map);
	Integer getOnLineBusNum(Map<String, Object> map);
	Integer getAllBusNum(Map<String, Object> map);

	Integer getRunBusNum(Map<String, Object> map);

	Integer getExceptionBus(Map<String, Object> map);
	Integer getNoGpsNum(Map<String, Object> map);
//	Integer getOffLineNum(Map<String, Object> map);
	Integer getWorkNum(Map<String, Object> map);
	List<String> getAllBusID();

	List<String> getBusIDByHeadId();

    List<BusInfo> getBusInfoByBusID(Map<String, Object> map);
}
