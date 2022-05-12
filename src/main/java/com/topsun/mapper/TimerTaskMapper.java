package com.topsun.mapper;

import com.topsun.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TimerTaskMapper {

	Outline getLatestOutLineLog(String string);

	void delLatestOutLineLog(HashMap<String, Object> map);

	void insertOutLine(@Param("list")List<DataLocation> result);

	List<Outline> getOutLine(Map<String, Object> map);

	void insertAlarmStatistics(@Param("list")List<AlarmStatistics> list);

	void delAlarmStatistics(HashMap<String, Object> map);

	List<AlarmStatistics> getAlarmStatistics(Map<String, Object> map);

	List<Round> getRoundSelect(Map<String, Object> map);

	List<Round> getRoundSelectForMonth(Map<String, Object> map);

    List<Offline> getOffline(Map<String, Object> map);

    List<Offline> listOutage(Map<String, Object> map);

    List<Offline> listOutageHistory(Map<String, Object> map);

	void insertSpeedSelect(@Param("list") List<BusSpeed> list);

	void delSpeedSelectLog(HashMap<String, Object> map);

	List<AlarmType> getGpsAlarm(Map<String, Object> map);

    List<CameraAlarm> listCameraAlarm(Map<String, Object> map);

	List<CameraAlarm> listHistoryCameraAlarm(Map<String, Object> map);

	List<OfflineDisplacement> listOfflineDisplacement(Map<String, Object> map);

	List<DataLocation> getHistoryByBusID(@Param("tableName") String tableName,@Param("busID") Integer busID, @Param("time") String time);

	void insertOfflineDisplacement(List<OfflineDisplacement> result);

	Outline listLatestOutLineLog(@Param("busID") String string);

	void deleteLatestOutLineLog(HashMap<String, Object> map);

	List<OfflineDisplacement> selectOffline(Map<String, Object> map);

    List<OfflineBus> listOffline(Map<String, Object> map);
}
