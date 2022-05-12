package com.topsun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.topsun.entity.OfflineBus;
import com.topsun.entity.OfflineDisplacement;
import com.topsun.entity.Outline;
import com.topsun.service.BusService;
import com.topsun.service.DataLocationService;
import com.topsun.service.TimerTaskService;
import com.topsun.util.ApplicationContextUtil;
import com.topsun.util.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/test")
@RestController
public class TestController<E> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 定时查询离线位移
//	@Scheduled(cron = "0 0 8 * * ? ")
	@RequestMapping("/outLine")
	public void outLine() {

		Calendar instance = Calendar.getInstance();

		Date date = new Date();
//		String endTime = sdf.format(date);
		String endTime = "2022-01-13 08:00:00";
		instance.setTime(date);
		instance.add(Calendar.HOUR_OF_DAY, -24);
//		String startTime = sdf.format(instance.getTime());
		String startTime = "2022-01-12 08:00:00";

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

	@RequestMapping("/listOffline")
	public Object offline() {
		TimerTaskService timerTaskService = (TimerTaskService) ApplicationContextUtil.getBean("timerTaskServiceImpl");
		Map<String, Object> map = new HashMap<>();

		List<OfflineBus> list = timerTaskService.listOffline(map);

		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));

		System.out.println(jsonArray.toJSONString());
//		String result = sendPostUrlTwo("http://118.178.142.250:2222/ts/abnormalDevice", jsonArray.toJSONString());
//		JSONObject jsonObject = JSONObject.parseObject(result);
		return null;
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
