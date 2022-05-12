package com.topsun.mapper;

import com.topsun.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataLocationMapper {

	//查询车辆最后一次在线时间
	List<DataLocation> listBusData(@Param("map")Map<String, Object> map);

	List<BusSpeed> speedListBusData(@Param("map")HashMap<String, Object> map);

	List<DataLocation> listBusMileage(@Param("map") Map<String, Object> map);

	List<BusSpeed> speedListCount(@Param("map")HashMap<String, Object> map);

	Integer speedListBusDataCount(@Param("map")Map<String, Object> map);

	List<DataLocation> listBusDataAll();

	List<DataLocation> connectionByBusID(@Param("map")Map<String, Object> map);

	List<BusSpeedTable> speedListBusTable(@Param("map")Map<String, Object> map);
//
//	//实时视频下发记录
	Integer insertVideoTab(@Param("map")Map<String, Object> map);
//
	List<TrackBackTest> listTrackBackTest(Map<String, Object> map);

	List<TrackBackTest> listBusDataHome(@Param("map")Map<String, Object> map);

	String getNewVideo(Map<String, Object> map);

    Integer listBusCurrentDataCount(@Param("map")Map<String, Object> map);

	List<BusCurrentData> listBusCurrentData(@Param("map")Map<String, Object> map);

	List<TrackBackTest> listBusCurrentDataHome(@Param("map")Map<String, Object> map);

    List<OfflineDisplacement> outlineByBusID(HashMap<String, Object> map);
}
