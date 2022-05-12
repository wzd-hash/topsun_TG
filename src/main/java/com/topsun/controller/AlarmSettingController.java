package com.topsun.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.topsun.entity.AlarmSetting;
import com.topsun.entity.BusInfo;
import com.topsun.entity.PopupSetting;
import com.topsun.service.AlarmSettingService;

@RestController
@RequestMapping("/alarmSetting")
public class AlarmSettingController extends BaseController {
	
	@Autowired
	private AlarmSettingService alarmSettingService;

	
	
	@RequestMapping("/getPopupSetting")
	public Object getPopupSetting() {
		
		Map<String, Object> map = getSessionInfo();
		
		try {
			PopupSetting popupSetting = alarmSettingService.getPopupSetting(map);
			if(popupSetting != null) {
				JSONObject parseObject = JSON.parseObject((String) popupSetting.getSetting());
				popupSetting.setSetting(parseObject);
			}
			map.clear();
			map.put("popupSetting", popupSetting);
			map.put("code", 1);
			
			
		}catch(Exception e) {
			map.clear();
			map.put("code", 2);
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	@RequestMapping("/settingPopup")
	public Object settingPopup(PopupSetting popupSetting) {
		
		Map<String, Object> map = getSessionInfo();
		
		map.put("popupSetting", popupSetting);
		
		try {
			alarmSettingService.settingPopup(map);
			map.clear();
			map.put("code", 1);
		}catch(Exception e) {
			map.clear();
			map.put("code", 2);
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	
	@RequestMapping("/getSetting")
	public Object getSetting(String type,String subID) {
		
		Map<String, Object> map = getSessionInfo();
//		map.put("type", type);
		map.put("subID", subID);
		
		try {
			List<BusInfo> plateNO = alarmSettingService.getSetting(map);
			
			map.clear();
			map.put("plateNO", plateNO);
			map.put("code", 1);
			
			
		}catch(Exception e) {
			map.clear();
			map.put("code", 2);
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	@RequestMapping("/setting")
	public Object setting(String busID,String type) {
		
		Map<String, Object> map = getSessionInfo();
		map.put("type", type);
		
		map.put("busID", busID.split(","));
		
		try {
			alarmSettingService.setting(map);
			map.clear();
			map.put("code", 1);
		}catch(Exception e) {
			map.clear();
			map.put("code", 2);
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	
	@RequestMapping("/settingTime")
	public Object settingTime(String startTime,String endTime,String subID) {
		
		Map<String, Object> map = getSessionInfo();
		map.put("startCar", startTime+","+endTime);
		map.put("subID", subID);
		
		try {
			alarmSettingService.settingTime(map);
			map.clear();
			map.put("code", 1);
		}catch(Exception e) {
			map.clear();
			map.put("code", 2);
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	
	@RequestMapping("/getSettingTime")
	public Object getSettingTime(String subID) {
		
		Map<String, Object> map = getSessionInfo();
		map.put("subID", subID);
		
		try {
			Map<String,Object> result = alarmSettingService.getSettingTime(map);
			result.put("code", 1);
			return result;
		}catch(Exception e) {
			map.clear();
			map.put("code", 2);
			e.printStackTrace();
			return map;
		}
		
		
		
	}
}
