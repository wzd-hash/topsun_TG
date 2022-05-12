package com.topsun.service.impl;

import com.topsun.entity.*;
import com.topsun.mapper.TimerTaskMapper;
import com.topsun.service.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimerTaskServiceImpl implements TimerTaskService {

	@Autowired
	private  TimerTaskMapper timerTaskMapper;
	
	@Override
	public Outline getLatestOutLineLog(String string) {
		return timerTaskMapper.getLatestOutLineLog(string);
	}

	@Override
	public void delLatestOutLineLog(HashMap<String, Object> map) {

		timerTaskMapper.delLatestOutLineLog(map);
	}

	@Override
	public void insertOutLine(List<DataLocation> result) {
		timerTaskMapper.insertOutLine(result);
	}

	@Override
	public List<Outline> getOutLine(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return timerTaskMapper.getOutLine(map);
	}

	@Override
	public void insertAlarmStatistics(List<AlarmStatistics> list) {

		timerTaskMapper.insertAlarmStatistics(list);
		
	}

	@Override
	public void delAlarmStatistics(HashMap<String, Object> map) {
		
		timerTaskMapper.delAlarmStatistics(map);
		
	}

	@Override
	public List<AlarmStatistics> getAlarmStatistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		
		return timerTaskMapper.getAlarmStatistics(map);
	}
	
	
	@Override
	public List<Round> getRoundSelect(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return timerTaskMapper.getRoundSelect(map);
	}

	@Override
	public List<Round> getRoundSelectForMonth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return timerTaskMapper.getRoundSelectForMonth(map);
	}

	@Override
	public List<Offline> getOffline(Map<String, Object> map) {
		return timerTaskMapper.getOffline(map);
	}

	@Override
	public List<Offline> listOutage(Map<String, Object> map) {
		return timerTaskMapper.listOutage(map);
	}

	@Override
	public List<Offline> listOutageHistory(Map<String, Object> map) {
		return timerTaskMapper.listOutageHistory(map);
	}

	@Override
	public void insertSpeedSelect(List<BusSpeed> list) {
		timerTaskMapper.insertSpeedSelect(list);
	}

	@Override
	public void delSpeedSelectLog(HashMap<String, Object> map) {
		timerTaskMapper.delSpeedSelectLog(map);
	}

	@Override
	public List<AlarmType> getGpsAlarm(Map<String, Object> map) {
		return timerTaskMapper.getGpsAlarm(map);
	}

	@Override
	public List<CameraAlarm> listCameraAlarm(Map<String, Object> map) {
		return timerTaskMapper.listCameraAlarm(map);
	}

	@Override
	public List<CameraAlarm> listHistoryCameraAlarm(Map<String, Object> map) {
		return timerTaskMapper.listHistoryCameraAlarm(map);
	}

	@Override
	public List<OfflineDisplacement> listOfflineDisplacement(Map<String, Object> map) {
		return timerTaskMapper.listOfflineDisplacement(map);
	}

	@Override
	public List<DataLocation> getHistoryByBusID(String tableName, Integer busID, String time) {
		return timerTaskMapper.getHistoryByBusID(tableName,busID,time);
	}

	@Override
	public void insertOfflineDisplacement(List<OfflineDisplacement> result) {
		timerTaskMapper.insertOfflineDisplacement(result);
	}

	@Override
	public Outline listLatestOutLineLog(String string) {
		return timerTaskMapper.listLatestOutLineLog(string);
	}

	@Override
	public void deleteLatestOutLineLog(HashMap<String, Object> map) {
		timerTaskMapper.deleteLatestOutLineLog(map);
	}

	@Override
	public List<OfflineDisplacement> selectOffline(Map<String, Object> map) {

		return timerTaskMapper.selectOffline(map);
	}

	@Override
	public List<OfflineBus> listOffline(Map<String, Object> map) {
		return timerTaskMapper.listOffline(map);
	}

}
