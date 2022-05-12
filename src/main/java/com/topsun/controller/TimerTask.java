package com.topsun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.topsun.entity.*;
import com.topsun.service.AlarmService;
import com.topsun.service.BusService;
import com.topsun.service.DataLocationService;
import com.topsun.service.TimerTaskService;
import com.topsun.util.ApplicationContextUtil;
import com.topsun.util.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TimerTask<E> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 定时查询掉线统计
	@Scheduled(cron = "0 0 8 * * ? ")
	private void outLine() {

		Calendar instance = Calendar.getInstance();

		Date date = new Date();
		String endTime = sdf.format(date);
		instance.setTime(date);
		instance.add(Calendar.HOUR_OF_DAY, -24);
		String startTime = sdf.format(instance.getTime());

		// 获取所有车辆信息busID
		DataLocationService dataLocationService = (DataLocationService) ApplicationContextUtil
				.getBean("dataLocationService");
		BusService busService = (BusService) ApplicationContextUtil.getBean("busServiceImpl");
		TimerTaskService timerTaskService = (TimerTaskService) ApplicationContextUtil.getBean("timerTaskServiceImpl");

		List<String> busIDs = busService.getBusIDByHeadId();

		HashMap<String, Object> map = new HashMap<String, Object>();
		List<DataLocation> result = new ArrayList<>();

		for (String string : busIDs) {

			// 获取最新的一条的上线时间
			Outline ol = timerTaskService.getLatestOutLineLog(string);
			if (ol != null) {
				if (ol.getInTime() != null) {
					startTime = ol.getInTime();
				} else {
					startTime = ol.getOutTime();
					map.put("id", ol.getId());
					timerTaskService.delLatestOutLineLog(map);
				}
			}

			map.put("tableName",
					"data_history_topsun_" + new SimpleDateFormat("yyyy").format(new Date()) + ".bus_" + string);
			map.put("startTime", startTime);
			map.put("endTime", endTime);

			List<DataLocation> list = dataLocationService.connectionByBusID(map);
			if (list == null || list.size() == 0) {
				// 此时间段全部掉线
				DataLocation dataLocation = new DataLocation();
				dataLocation.setBusID(Integer.parseInt(string));
//				dataLocation.setLeadTime(DateUtils.getTimeStampDiff(endTime, startTime) + "");
				dataLocation.setLeadTime(null);
				dataLocation.setLoginTime(null);
				dataLocation.setLogoutTime(startTime);
				result.add(dataLocation);
			} else {
				for (int i = 0; i < list.size() - 1; i++) {

					Integer timeStampDiff = DateUtils.getTimeStampDiff(list.get(i + 1).getLoginTime(),
							list.get(i).getLogoutTime());

					if (timeStampDiff > 1200) {
						DataLocation dataLocation = list.get(i);
						dataLocation.setLeadTime(timeStampDiff + "");
						dataLocation.setLoginTime(list.get(i + 1).getLoginTime());
						dataLocation.setLogoutTime(list.get(i).getLogoutTime());
						result.add(dataLocation);
					}

					if (i + 1 == list.size() - 1) {
						Integer timeStampDiff2 = DateUtils.getTimeStampDiff(endTime, list.get(i + 1).getLogoutTime());
						if (timeStampDiff2 > 1200) {
							DataLocation dataLocation = list.get(i + 1);
//							dataLocation.setLeadTime(timeStampDiff2 + "");
							dataLocation.setLeadTime(null);
							dataLocation.setLoginTime(null);
							dataLocation.setLogoutTime(list.get(i + 1).getLogoutTime());
							result.add(dataLocation);
						}
					}

				}
			}
			
		}
		
		
		//存入表格
		timerTaskService.insertOutLine(result);
		
		//删除七天前数据
/*		map.clear();
		instance.add(Calendar.DAY_OF_MONTH, -6);
		
		map.put("time", sdf.format(instance.getTime()));
		timerTaskService.delLatestOutLineLog(map);*/

	}


	/*@Scheduled(cron = "0 5 11 * * ? ")
	@Transactional
	void speedSelect() throws ParseException {
		Calendar instance = Calendar.getInstance();
		System.out.println("开始超速报警");
		Date date = new Date();

//		date = sdf_round.parse("2021-06-28 10:10:00");

		String endTime = sdf.format(date);
		instance.setTime(date);
		instance.add(Calendar.HOUR_OF_DAY, -24);
		String startTime = sdf.format(instance.getTime());

		// 获取所有车辆信息busID
		DataLocationService dataLocationService = (DataLocationService) ApplicationContextUtil
				.getBean("dataLocationService");
		BusService busService = (BusService) ApplicationContextUtil.getBean("busServiceImpl");
		TimerTaskService timerTaskService = (TimerTaskService) ApplicationContextUtil.getBean("timerTaskServiceImpl");
		AlarmService alarmService = (AlarmService) ApplicationContextUtil.getBean("alarmServiceImpl");
		List<String> busIDs = busService.getBusIDByHeadId();

		HashMap<String, Object> map = new HashMap<String, Object>();
//		List<Round> result = new ArrayList<>();

//		int i = 0;

		for (String string : busIDs) {
			HashMap<String, Object> tableMap = new HashMap<String, Object>();
			tableMap.put("databaseName","data_history_topsun_"+ new SimpleDateFormat("yyyy").format(new Date()));
			tableMap.put("tableName","bus_"+string);
			//判断有没有这个数据库
			int table = alarmService.tableIfExists(tableMap);
			if(table>0) {
				map.put("tableName",
						"data_history_topsun_" + new SimpleDateFormat("yyyy").format(new Date()) + ".bus_" + string);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				List<BusSpeed> list = dataLocationService.speedListBusData(map);
				if (list != null && list.size() != 0 && !list.isEmpty()) {
					timerTaskService.insertSpeedSelect(list);
				}
			}
		}

		//删除3月前数据
		map.clear();
		instance.add(Calendar.MONTH, -3);
		map.put("time", sdf.format(instance.getTime()));
		timerTaskService.delSpeedSelectLog(map);
		System.out.println("完成超速报警");

	}*/
	
	
	// 定时查询运营统计
		//@Scheduled(cron = "0 0 0 * * ? ")
		private void alarmTask() {

			Calendar instance = Calendar.getInstance();

			Date date = new Date();
			String endTime = sdf.format(date);
			instance.setTime(date);
			instance.add(Calendar.HOUR_OF_DAY, -24);
			String startTime = sdf.format(instance.getTime());

			// 获取所有车辆信息busID
			AlarmService alarmService = (AlarmService) ApplicationContextUtil
					.getBean("alarmServiceImpl");
			BusService busService = (BusService) ApplicationContextUtil.getBean("busServiceImpl");
			TimerTaskService timerTaskService = (TimerTaskService) ApplicationContextUtil.getBean("timerTaskServiceImpl");

			List<String> busIDs = busService.getAllBusID();

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("busID", busIDs);
			
			
			List<AlarmStatistics> list =  alarmService.getStatistics(map);
			
			
			//存入表格
			timerTaskService.insertAlarmStatistics(list);
			
			//删除数据
			map.clear();
			instance.add(Calendar.YEAR, -1);
			
			map.put("time", sdf.format(instance.getTime()));
			timerTaskService.delAlarmStatistics(map);

		}

	// 定时查询离线位移
	@Scheduled(cron = "0 0 7 * * ? ")
	public void offLineDisplacement() {

		Calendar instance = Calendar.getInstance();

		Date date = new Date();
		String endTime = sdf.format(date);
		instance.setTime(date);
		instance.add(Calendar.HOUR_OF_DAY, -24);
		String startTime = sdf.format(instance.getTime());

		// 获取所有车辆信息busID
		DataLocationService dataLocationService = (DataLocationService) ApplicationContextUtil
				.getBean("dataLocationService");
		BusService busService = (BusService) ApplicationContextUtil.getBean("busServiceImpl");
		TimerTaskService timerTaskService = (TimerTaskService) ApplicationContextUtil.getBean("timerTaskServiceImpl");

		List<String> busIDs = busService.getBusIDByHeadId();

		HashMap<String, Object> map = new HashMap<String, Object>();
		List<OfflineDisplacement> result = new ArrayList<>();

		for (String string : busIDs) {
			// 获取最新的一条的上线时间
			Outline ol = timerTaskService.listLatestOutLineLog(string);
			if (ol != null) {
				if (ol.getInTime() != null) {
					startTime = ol.getInTime();
				} else {
					startTime = ol.getOutTime();
					map.put("Id", ol.getId());
					timerTaskService.deleteLatestOutLineLog(map);
				}
			}
			map.put("tableName",
					"data_history_topsun_" + new SimpleDateFormat("yyyy").format(new Date()) + ".bus_" + string);
			map.put("startTime", startTime);
			map.put("endTime", endTime);

			List<OfflineDisplacement> list = dataLocationService.outlineByBusID(map);

			if (list == null || list.size() == 0) {
				// 此时间段全部掉线
				OfflineDisplacement offlineDisplacement = new OfflineDisplacement();
				offlineDisplacement.setBusID(Integer.parseInt(string));
				offlineDisplacement.setTimes(null);
				offlineDisplacement.setEndTime(null);
				offlineDisplacement.setStartTime(startTime);
				offlineDisplacement.setAlarmType("1");
				result.add(offlineDisplacement);
			} else {
				for (int i = 0; i < list.size() - 1; i++) {

					Integer timeStampDiff = DateUtils.getTimeStampDiff(list.get(i + 1).getEndTime(),
							list.get(i).getStartTime());

					if (timeStampDiff > 1200) {
						OfflineDisplacement offlineDisplacement = list.get(i);
						offlineDisplacement.setTimes(timeStampDiff + "");
						offlineDisplacement.setEndTime(list.get(i + 1).getEndTime());
						offlineDisplacement.setStartTime(list.get(i).getStartTime());
						offlineDisplacement.setBeginLatitude(list.get(i).getBeginLatitude());
						offlineDisplacement.setBeginLongitude(list.get(i).getBeginLongitude());
						offlineDisplacement.setEndLatitude(list.get(i+1).getEndLatitude());
						offlineDisplacement.setEndLongitude(list.get(i+1).getEndLongitude());
						offlineDisplacement.setAlarmType("1");
						result.add(offlineDisplacement);
					}

					if (i + 1 == list.size() - 1) {
						Integer timeStampDiff2 = DateUtils.getTimeStampDiff(endTime, list.get(i + 1).getStartTime());
						if (timeStampDiff2 > 1200) {
							OfflineDisplacement offlineDisplacement = list.get(i + 1);
							offlineDisplacement.setBeginLongitude(list.get(i+1).getBeginLongitude());
							offlineDisplacement.setBeginLatitude(list.get(i+1).getBeginLatitude());
							offlineDisplacement.setStartTime(list.get(i + 1).getStartTime());
							offlineDisplacement.setEndTime(null);
							offlineDisplacement.setEndLatitude(null);
							offlineDisplacement.setEndLongitude(null);
							offlineDisplacement.setAlarmType(null);
							result.add(offlineDisplacement);
						}
					}
				}
			}
		}

		//存入表格
		timerTaskService.insertOfflineDisplacement(result);

	}

	//每两小时执行一次
	//@Scheduled(cron = "0 0 */2 * * ?")
	public void offline() {
		TimerTaskService timerTaskService = (TimerTaskService) ApplicationContextUtil.getBean("timerTaskServiceImpl");
		Map<String, Object> map = new HashMap<>();

		List<OfflineBus> list = timerTaskService.listOffline(map);

		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));

		try {
			String result = sendPostUrlTwo("http://118.178.142.250:2222/ts/abnormalDevice", jsonArray.toJSONString());
			JSONObject jsonObject = JSONObject.parseObject(result);
			System.out.println(jsonObject);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public static String sendPostUrlTwo(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);

			conn.setDoInput(true);

//			conn.setRequestProperty("token", token);//设置请求头所需token验证
			conn.setRequestProperty("Content-Type", "application/json");
			// 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 获取请求返回数据（设置返回数据编码为UTF-8）
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
