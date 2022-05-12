package com.topsun.service;

import com.topsun.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataLocationService {
//	
//	//实时位置
	List<DataLocation> listBusData(Map<String, Object> map);

	Integer speedListBusDataCount(Map<String, Object> map);

	List<BusSpeed> speedListBusData(HashMap<String, Object> map);

	List<DataLocation> listBusMileage(@Param("map") Map<String, Object> map);

	List<BusSpeedTable> speedListBusTable(@Param("map")Map<String, Object> map);

	List<BusSpeed> speedListCount(HashMap<String, Object> map);

//	//离线统计
	List<DataLocation> connectionByBusID(Map<String, Object> map);

	List<DataLocation> listBusDataAll();
//
//	//实时视频下发记录
	Integer insertVideoTab(Map<String, Object> map);
//
	List<TrackBackTest> listTrackBackTest(Map<String, Object> map);
	List<TrackBackTest> listBusDataHome(Map<String, Object> map);
	String getNewVideo(Map<String, Object> map);

    Integer listBusCurrentDataCount(Map<String, Object> map);

	List<BusCurrentData> listBusCurrentData(Map<String, Object> map);

	List<TrackBackTest> listBusCurrentDataHome(Map<String, Object> map);

    List<OfflineDisplacement> outlineByBusID(HashMap<String, Object> map);
}
