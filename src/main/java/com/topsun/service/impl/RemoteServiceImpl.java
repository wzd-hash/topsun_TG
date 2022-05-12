package com.topsun.service.impl;

import com.topsun.entity.RemoteInfo;
import com.topsun.mapper.RemoteMapper;
import com.topsun.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service("remoteService")
public class RemoteServiceImpl extends Thread implements RemoteService{

	@Autowired
	private RemoteMapper remoteMapper;
	
	@Override
	public Integer sendCommand(RemoteInfo map) {
		
		return remoteMapper.sendCommand(map);
	}


	@Override
	public Integer insertVideoHistory(Map<String, String> map) {
		
		return remoteMapper.insertVideoHistory(map);
	}

	@Override
	public RemoteInfo queryRemoteInfo(Map<String, Object> map) {

		return remoteMapper.queryRemoteInfo(map);
	}


}
