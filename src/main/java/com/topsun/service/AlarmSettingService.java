package com.topsun.service;

import java.util.List;
import java.util.Map;

import com.topsun.entity.AlarmSetting;
import com.topsun.entity.BusInfo;
import com.topsun.entity.PopupSetting;



public interface AlarmSettingService {


	PopupSetting getPopupSetting(Map<String, Object> map);

	void settingPopup(Map<String, Object> map);

	List<BusInfo> getSetting(Map<String, Object> map);

	void setting(Map<String, Object> map);

	void settingTime(Map<String, Object> map);

	Map<String, Object> getSettingTime(Map<String, Object> map);

}
