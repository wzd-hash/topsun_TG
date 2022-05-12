package com.topsun.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.topsun.entity.AlarmInfo;
import com.topsun.entity.AlarmInfoInTack;
import com.topsun.entity.AlarmNum;
import com.topsun.entity.AlarmStatistics;
import com.topsun.entity.MediaInfo;
import com.topsun.entity.TextTemplate;
import com.topsun.model.AlarmModel;

public interface AlarmService {
//	
	//安全监管
	List<AlarmInfo> listBusAlarm(Map<String, Object> map);
	Integer listBusAlarmCount(Map<String, Object> map);
//	
	//安全监管导出
	List<AlarmModel> listBusAlarmModel(Map<String, Object> map);
//
	
	
	//查询图片和视频
	List<MediaInfo> listMediaInfo(Map<String, Object> map);
//	
	//修改报警处理状态
	Integer updateAlarmHandle(Map<String, Object> map);
//	
	List<AlarmNum> listAlarmCount(Map<String, Object> map);
//	//新增文本模板
	Integer insertTextTemplate(TextTemplate template);
//	
//	//编辑文本模板
	Integer updateTextTemplate(TextTemplate template);
//	
	List<TextTemplate> listTextTemplate(Map<String, Object> map);
//
	public AlarmInfoInTack getAlarmInfoInTack(Map<String, Object> map);
//	
	//判断表是否存在
	public Integer tableIfExists(Map<String, Object> map);
	List<AlarmStatistics> getStatistics(Map<String, Object> sessionInfo);
	Integer exportStatistics(Map<String, Object> sessionInfo);
	
}
