package com.topsun.service.impl;
import com.topsun.entity.BusInfo;
import com.topsun.mapper.BusMapper;
import com.topsun.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusMapper busMapper;

	
	public List<BusInfo> listBusInfo(Map<String, Object> map) {
		
		return busMapper.listBusInfo(map);
	}

	

	@Override
	public Integer getOnLineBusNum(Map<String, Object> map) {
		
		return busMapper.getOnLineBus(map);
	}


	@Override
	public Integer getAllBusNum(Map<String, Object> map) {
		
		return busMapper.getAllBusNum(map);
	}


	@Override
	public Integer getRunBusNum(Map<String, Object> map) {
		return busMapper.getRunBusNum(map);
	}

	@Override
	public Integer getExceptionBus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return busMapper.getExceptionBus(map);
	}


	@Override
	public Integer getNoGpsNum(Map<String, Object> map) {
		
		return busMapper.getNoGpsNum(map);
	}

//	@Override
//	public Integer getOffLineNum(Map<String, Object> map) {
//		return busMapper.getOffLineNum(map);
//	}



	@Override
	public Integer getWorkNum(Map<String, Object> map) {

		return busMapper.getWorkNum(map);
	}



	@Override
	public List<String> getAllBusID() {
		return busMapper.getAllBusID();
	}

	@Override
	public List<String> getBusIDByHeadId() {
		return busMapper.getBusIDByHeadId();
	}

	@Override
	public List<BusInfo> getBusInfoByBusID(Map<String, Object> map) {
		return busMapper.getBusInfoByBusID(map);
	}


}

