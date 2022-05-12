package com.topsun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

	public static Calendar calendar = Calendar.getInstance();

	public static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");

	/*
	 * 获取当前周的第一天和最后一天
	 */
	public static String getFirstLastDay(String date) {

		String result = "";

		try {
			calendar.setTime(format1.parse(date));
			calendar.add(Calendar.DATE, -1);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			result += format2.format(calendar.getTime());
			calendar.add(Calendar.DATE, 6);
			result += "_" + format2.format(calendar.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;

	}

	/*
	 * 计算时间差
	 */
	public static Integer getTimeStampDiff(String time1, String time2) {

		try {
			Date date1 = format1.parse(time1);
			Date date2 = format1.parse(time2);

			return (int) ((date1.getTime() - date2.getTime()) / 1000);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 切割时间段
	 *
	 * @param dateType 交易类型 M/D/H/N -->每月/每天/每小时/每分钟
	 * @param start    yyyy-MM-dd HH:mm:ss
	 * @param end      yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static List<String> cutDate(String dateType, String start, String end, int length) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dBegin = sdf.parse(start);
			Date dEnd = sdf.parse(end);
			return findDates(dateType, dBegin, dEnd, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> findDates(String dateType, Date dBegin, Date dEnd, int length) throws Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		List<String> listDate = new ArrayList<>();
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		listDate.add(simpleDateFormat.format(calBegin.getTime()));
		while (calEnd.after(calBegin)) {
			switch (dateType) {
			case "M":
				calBegin.add(Calendar.MONTH, length);
				break;
			case "D":
				calBegin.add(Calendar.DAY_OF_YEAR, length);
				break;
			case "H":
				calBegin.add(Calendar.HOUR, length);
				break;
			case "N":
				calBegin.add(Calendar.MINUTE, length);
				break;
			}
			if (calEnd.after(calBegin))
				listDate.add(simpleDateFormat.format(calBegin.getTime()));
			else
				listDate.add(simpleDateFormat.format(calEnd.getTime()));
		}
		return listDate;
	}

	/**
	 * 瑞为的切割
	 * 
	 * @param dateType
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> cutDate(String start, String end) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			List<String> listDate = new ArrayList<>();
			Date dBegin = sdf.parse(start);
			Date dEnd = sdf.parse(end);

			Calendar calBegin = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance();

			calBegin.setTime(dBegin);
			calEnd.setTime(dEnd);

			listDate.add(simpleDateFormat.format(calBegin.getTime()));
			
			
			
			int i = calBegin.get(Calendar.MINUTE);

			int num = 5 - (i % 5);

			calBegin.add(Calendar.MINUTE, num);
			
			if (calEnd.after(calBegin)) {
				while (true) {

					calBegin.add(Calendar.MINUTE, 5);
					
					if (calEnd.after(calBegin)) {
						listDate.add(simpleDateFormat.format(calBegin.getTime()));
					}else {
						listDate.add(simpleDateFormat.format(calEnd.getTime()));
						break;
					}
					
				}
			}else {
				listDate.add(simpleDateFormat.format(calEnd.getTime()));
			}
				

			return listDate;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
