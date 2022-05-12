package com.topsun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topsun.entity.AlarmSetting;
import com.topsun.entity.BusInfo;
import com.topsun.entity.PopupSetting;
import com.topsun.mapper.AlarmSettingMapper;
import com.topsun.service.AlarmSettingService;

@Service
public class AlarmSettingServiceImpl<V> implements AlarmSettingService {

	
	@Autowired
	private AlarmSettingMapper alarmSettingMapper;
	
	
	@Override
	public PopupSetting getPopupSetting(Map<String, Object> map) {
		return alarmSettingMapper.getPopupSetting(map);
	}

	@Override
	public void settingPopup(Map<String, Object> map) {
		alarmSettingMapper.settingPopup(map);
	}

	@Override
	public List<BusInfo> getSetting(Map<String, Object> map) {
		
		return alarmSettingMapper.getSetting(map);
	}

	@Override
	public void setting(Map<String, Object> map) {
		alarmSettingMapper.setting(map);
		
	}

	@Override
	public void settingTime(Map<String, Object> map) {
		
		
		alarmSettingMapper.settingTime(map);
		
	}

	@Override
	public Map<String, Object> getSettingTime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String time = alarmSettingMapper.getSettingTime(map);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String[] split = time.split(",");
		hashMap.put("startTime", split[0]);
		hashMap.put("endTime", split[1]);
		return hashMap;
	}

}
