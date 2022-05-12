package com.topsun.service.impl;

import com.topsun.entity.*;
import com.topsun.mapper.DataLocationMapper;
import com.topsun.service.DataLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("dataLocationService")
public class DataLocationServiceImpl implements DataLocationService{

	@Autowired
	private DataLocationMapper dataLocationMapper;
	
	@Override
	public List<DataLocation> listBusData(Map<String, Object> map) {
		
		return dataLocationMapper.listBusData(map);
	}

	@Override
	public Integer speedListBusDataCount(Map<String, Object> map) {
		return dataLocationMapper.speedListBusDataCount(map);
	}

	@Override
	public List<BusSpeed> speedListBusData(HashMap<String, Object> map) {
		return dataLocationMapper.speedListBusData(map);
	}

	@Override
	public List<DataLocation> listBusMileage(Map<String, Object> map) {
		return dataLocationMapper.listBusMileage(map);
	}

	@Override
	public List<BusSpeedTable> speedListBusTable(Map<String, Object> map) {
		return dataLocationMapper.speedListBusTable(map);
	}

	@Override
	public List<BusSpeed> speedListCount(HashMap<String, Object> map) {
		return dataLocationMapper.speedListCount(map);
	}

	@Override
	public List<DataLocation> connectionByBusID(Map<String, Object> map) {
		
		return dataLocationMapper.connectionByBusID(map);
	}

	@Override
	public List<DataLocation> listBusDataAll() {
		return dataLocationMapper.listBusDataAll();
	}


	@Override
	public Integer insertVideoTab(Map<String, Object> map) {
		
		return dataLocationMapper.insertVideoTab(map);
	}

	
	@Override
	public List<TrackBackTest> listTrackBackTest(Map<String, Object> map) {
		String busID = (String) map.get("busID");
		String startTime = (String) map.get("startTime");
		map.put("table", "data_history_topsun_"+startTime.substring(0, 4)+".bus_"+busID);
		return dataLocationMapper.listTrackBackTest(map);
	}

	@Override
	public List<TrackBackTest> listBusDataHome(Map<String, Object> map) {
		return dataLocationMapper.listBusDataHome(map);
	}

	@Override
	public String getNewVideo(Map<String, Object> map) {
		return dataLocationMapper.getNewVideo(map);
	}

	@Override
	public Integer listBusCurrentDataCount(Map<String, Object> map) {
		return dataLocationMapper.listBusCurrentDataCount(map);
	}

	@Override
	public List<BusCurrentData> listBusCurrentData(Map<String, Object> map) {
		return dataLocationMapper.listBusCurrentData(map);
	}

	@Override
	public List<TrackBackTest> listBusCurrentDataHome(Map<String, Object> map) {
		return dataLocationMapper.listBusCurrentDataHome(map);
	}

	@Override
	public List<OfflineDisplacement> outlineByBusID(HashMap<String, Object> map) {
		return dataLocationMapper.outlineByBusID(map);
	}


}
