package com.topsun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationUtil {
	
	/*
	 * 百度和高德地图api
	 * 根据经纬度获取地理位置
	 * by wangting
	 * 2019-04-22
	 * 从高德开放平台申请的个人key值
	 */
	public static String getposition(String longitude,String latitude) throws MalformedURLException {
		BufferedReader in = null;
//		 URL tirc = new URL("http://api.map.baidu.com/geocoder?location="+
//		 latitude+","+longitude+"&output=json&key="+"E4805d16520de693a3fe707cdc962045");
//		URL tirc = new URL("https://restapi.amap.com/v3/geocode/regeo?location=" + latitude + "," + longitude
//				+ "&output=json&key=" + "a6cf23e8e9a755637e88705608b4002e");
		
		URL tirc = new URL("https://restapi.amap.com/v3/geocode/regeo?location=" + longitude + "," + latitude
			       + "&output=json&key=" + "a6cf23e8e9a755637e88705608b4002e");

//		 URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?ak=RnYXdZDb0QuGBNPRxiFykBpL1otlUWpy&callback=renderReverse&location="+longitude+","+latitude+"&output=json&pois=1");
		String location = "";
		
		try {
			in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			String str = sb.toString();
//			 System.out.println(str);
//			ObjectMapper mapper = new ObjectMapper();
			if (null != str && !"".equals(str)) {
//				JsonNode jsonNode = mapper.readTree(str);
//				jsonNode.findValue("status").toString();
//				// JsonNode resultNode = jsonNode.findValue("result");
//				JsonNode resultNode = jsonNode.findValue("regeocode");
//				JsonNode locationNode = resultNode.findValue("formatted_address");
//				
//				location = locationNode.toString();
				location = JSON.parseObject(str).getJSONObject("regeocode").getString("formatted_address");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}
	
	/*
	 * 高德地图获取公交线路站点信息
	 * by wangting
	 * 2019-06-05
	 */
	public static String getBusStations(String roulesName,String cityName){
		BufferedReader in = null;
		String str = "";
		try {
			URL tirc = new URL("https://uri.amap.com/line?id=110100023111&view=list&src=mypage&callnative=0"
					+ "&key=" + "2756c9b7b72cbf46840cd81c215737ac");
			
			in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			 str = sb.toString();
			// System.out.println(str);
			ObjectMapper mapper = new ObjectMapper();
/*			if (null != str && !"".equals(str)) {
				JsonNode jsonNode = mapper.readTree(str);
				jsonNode.findValue("status").toString();
				// JsonNode resultNode = jsonNode.findValue("result");
				JsonNode resultNode = jsonNode.findValue("regeocode");
				JsonNode locationNode = resultNode.findValue("formatted_address");
				
				//location = locationNode.toString();
			}*/
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return str;
	}
	
}
