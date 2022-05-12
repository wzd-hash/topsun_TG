package com.topsun.mapper;

import com.topsun.entity.RemoteInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface RemoteMapper {

	Integer sendCommand(RemoteInfo remoteInfo);
	
	/*
	 * 设备录像回放增加记录
	 */
	Integer insertVideoHistory(Map<String, String> map);

    RemoteInfo queryRemoteInfo(@Param("map") Map<String, Object> map);
}
