package com.topsun.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsun.entity.AlarmInfo;
import com.topsun.entity.AlarmInfoInTack;
import com.topsun.entity.AlarmNum;
import com.topsun.entity.AlarmStatistics;
import com.topsun.entity.MediaInfo;
import com.topsun.entity.TextTemplate;
import com.topsun.mapper.AlarmMapper;
import com.topsun.mapper.TimerTaskMapper;
import com.topsun.model.AlarmModel;
import com.topsun.model.OperationModel;
import com.topsun.model.StatisticsModel;
import com.topsun.service.AlarmService;
import com.topsun.util.EasyExcelUtil;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmMapper alarmMapper;
	
	@Autowired
	private  TimerTaskMapper timerTaskMapper;

	@Override
	public List<AlarmInfo> listBusAlarm(Map<String, Object> map) {

		return alarmMapper.listBusAlarm(map);
	}

	@Override
	public List<AlarmModel> listBusAlarmModel(Map<String, Object> map) {

		return alarmMapper.listBusAlarmModel(map);
	}

	@Override
	public List<MediaInfo> listMediaInfo(Map<String, Object> map) {

		return alarmMapper.listMediaInfo(map);
	}

	@Override
	public Integer updateAlarmHandle(Map<String, Object> map) {

		return alarmMapper.updateAlarmHandle(map);
	}

	@Override
	public List<AlarmNum> listAlarmCount(Map<String, Object> map) {

		return alarmMapper.listAlarmCount(map);
	}

	@Override
	public Integer insertTextTemplate(TextTemplate template) {

		return alarmMapper.insertTextTemplate(template);
	}

	@Override
	public Integer updateTextTemplate(TextTemplate template) {

		return alarmMapper.updateTextTemplate(template);
	}

	@Override
	public List<TextTemplate> listTextTemplate(Map<String, Object> map) {

		return alarmMapper.listTextTemplate(map);
	}

	@Override
	public AlarmInfoInTack getAlarmInfoInTack(Map<String, Object> map) {

		return alarmMapper.getAlarmInfoInTack(map);
	}

	@Override
	public Integer listBusAlarmCount(Map<String, Object> map) {

		return alarmMapper.listBusAlarmCount(map);
	}

	@Override
	public Integer tableIfExists(Map<String, Object> map) {

		return alarmMapper.tableIfExists(map);
	}

	@Override
	public List<AlarmStatistics> getStatistics(Map<String, Object> map) {

		List<String> busID = (List<String>) map.get("busID");

		List<AlarmStatistics> list = new ArrayList<AlarmStatistics>();

		for (String id : busID) {
			
			map.put("tableName", "data_history_topsun_"+new SimpleDateFormat("yyyy").format(new Date())+".bus_"+id);
			AlarmStatistics alarmStatistics = alarmMapper.getStatistics(map);
			
			if(alarmStatistics != null) {
				BigDecimal b = new BigDecimal(alarmStatistics.getGPS()*100);
				alarmStatistics.setGPS( b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				BigDecimal b1 = new BigDecimal(alarmStatistics.getDSM()*100);
				alarmStatistics.setDSM(b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				BigDecimal b2 = new BigDecimal(alarmStatistics.getBSD()*100);
				alarmStatistics.setBSD(b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				BigDecimal b3 = new BigDecimal(alarmStatistics.getMOVE()*100);
				alarmStatistics.setMOVE(b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				list.add(alarmStatistics);
			}
			
			
		}

		return list;
	}

	@Override
	public Integer exportStatistics(Map<String, Object> map) {
		
		try {
			HttpServletResponse Response = (HttpServletResponse) map.get("Response");

			List<List<String>> list = new ArrayList<List<String>>();
			
			List<AlarmStatistics> alarmStatistics = timerTaskMapper.getAlarmStatistics(map);
			

			for (AlarmStatistics item : alarmStatistics) {

				if(item != null) {
					BigDecimal b = new BigDecimal(item.getGPS());
					BigDecimal b1 = new BigDecimal(item.getDSM());
					BigDecimal b2 = new BigDecimal(item.getBSD());
					Double move = item.getMOVE();
					BigDecimal b3 = new BigDecimal(move == null? 0 :move);
					
					
					List<String> alarmlist = new ArrayList<String>();
					alarmlist.add(item.getPlateNO());
					alarmlist.add(item.getDriverName());
					alarmlist.add((b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%");
					alarmlist.add((b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%");
					alarmlist.add((b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%");
					alarmlist.add((b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%");

					list.add(alarmlist);
				}
				
				
			}
			
			
			
			if(list.size() == 0) {
				return 1;
			}
			
			EasyExcelUtil.writeExcel(Response, new StatisticsModel(), "运营汇总报表", list);
			return 0;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		
	}

}
