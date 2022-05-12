package com.topsun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topsun.entity.AlarmInfo;
import com.topsun.entity.AlarmInfoInTack;
import com.topsun.entity.AlarmNum;
import com.topsun.entity.AlarmStatistics;
import com.topsun.entity.MediaInfo;
import com.topsun.entity.TextTemplate;
import com.topsun.model.AlarmModel;


public interface AlarmMapper {

//	
	List<AlarmInfo> listBusAlarm(@Param("map")Map<String, Object> map);
	Integer listBusAlarmCount(@Param("map")Map<String, Object> map);
	
	List<AlarmModel> listBusAlarmModel(@Param("map")Map<String, Object> map);
//	//查询图片和视频
	List<MediaInfo> listMediaInfo(@Param("map")Map<String, Object> map);
//
//	//修改报警处理状态
	Integer updateAlarmHandle(@Param("map")Map<String, Object> map);
//	
	List<AlarmNum> listAlarmCount(@Param("map")Map<String, Object> map);
//	
//	
	//新增文本模板
	Integer insertTextTemplate(TextTemplate template);
	
	//编辑文本模板
	Integer updateTextTemplate(TextTemplate template);
	
	//查询文本模板
	List<TextTemplate> listTextTemplate(Map<String, Object> map);
//
	public AlarmInfoInTack getAlarmInfoInTack(Map<String, Object> map);
//	
//	//判断表是否存在
	public Integer tableIfExists(Map<String, Object> map);
	AlarmStatistics getStatistics(Map<String, Object> map);
	
}

