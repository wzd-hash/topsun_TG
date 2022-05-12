package com.topsun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.topsun.entity.AlarmSetting;
import com.topsun.entity.BusInfo;
import com.topsun.entity.PopupSetting;


@Mapper
public interface AlarmSettingMapper {


	PopupSetting getPopupSetting(Map<String, Object> map);

	void settingPopup(Map<String, Object> map);

	List<BusInfo> getSetting(Map<String, Object> map);

	void setting(Map<String, Object> map);
	void setting2(Map<String, Object> map);

	void settingTime(Map<String, Object> map);

	String getSettingTime(Map<String, Object> map);

}
