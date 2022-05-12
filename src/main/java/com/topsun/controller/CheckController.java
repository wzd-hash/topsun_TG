package com.topsun.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.topsun.entity.Check;
import com.topsun.service.AlarmSettingService;
import com.topsun.service.CheckService;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@RestController
public class CheckController<E> extends BaseController {

	@Autowired
	private CheckService checkService;

	@Autowired
	private AlarmSettingService alarmSettingService;



	@RequestMapping("/getChecks")
	public Object getChecks(HttpServletRequest request) {

		String time = request.getParameter("time");
		String busID = request.getParameter("busID");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");


		Properties props = new Properties();
		Properties props1 = new Properties();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("time", time);
		map.put("busID", busID.split(","));

		Page pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		List<Check> list = checkService.getChecks(map);

		if(list != null && list.size() != 0) {
			File file = new File("../image" + File.separator
					+ time.replace("-", "") + ".properties");

			if(file.exists()) {
				try {
					props.load(new FileReader(file));
				}catch(Exception e) {
					props = null;
				}

			}

			File file1 = new File("../image2" + File.separator
					+ time.replace("-", "") + ".properties");

			if(file1.exists()) {
				try {
					props1.load(new FileReader(file1));
				}catch(Exception e) {
					props1 = null;
				}

			}


		}




		for (Check check : list) {
			String checks = check.getChecks();

			if(StringUtils.isNotEmpty(checks)) {

				if(!checks.equals("停驶")) {
					check.setChecks("已检查");
					JSONArray jsonArray = JSON.parseArray(checks);
					if(jsonArray.size()==17){
						jsonArray.add("驾驶室内探头是否正常");
					}
					check.setParseArray(jsonArray);
				}
			}else {
				check.setChecks("未检查");
			}

			if(StringUtils.isEmpty(check.getIsSure())) {
				check.setIsSure("未确认");
			}

			if(StringUtils.isEmpty(check.getTime())) {
				check.setTime(time);
			}

			if(props != null && !check.getChecks().equals("未检查")) {
				String image = props.getProperty(check.getBusID());
				check.setImage(image);

			}

			if(props1 != null && !check.getIsSure().equals("未确认")) {
				String image1 = props1.getProperty(check.getBusID());
				check.setSBImage(image1);

			}

		}

		map.clear();
		map.put("list", list);
		map.put("total", pageHelper.getTotal());
		map.put("code", 0);
		return map;

	}

//	@RequestMapping("/updateChecks")
//	public Object updateChecks(HttpServletRequest request) {
//
//		String time = request.getParameter("time");
//		String busID = request.getParameter("busID");
//		String remark = request.getParameter("remark");
//		String isSure = request.getParameter("isSure");
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("time", time);
//		map.put("busID", busID);
//		map.put("remark", remark);
//		map.put("isSure", isSure);
//		try {
//			checkService.updateChecks(map);
//
//			map.clear();
//			map.put("code", 1);
//		} catch (Exception e) {
//			map.clear();
//			map.put("code", 2);
//			e.printStackTrace();
//		}
//
//		return map;
//
//	}

	@RequestMapping("/exportChecks")
	public Object exportChecks(HttpServletRequest request, HttpServletResponse respone) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String time = request.getParameter("time");
			String busID = request.getParameter("busID");

			Map<String, Object> sessionInfo = getSessionInfo();

			map.put("time", time);
			map.put("busID", busID.split(","));

			List<Check> list = checkService.getChecks(map);

			export(list, respone, "收车后出车前车辆检查", (String) sessionInfo.get("companyName"), time);
			//			export(list, respone, "收车后出车前车辆检查", "", time);
			map.clear();
			map.put("code", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("code", -1);
		}

		return map;

	}


	public void export(List<Check> list, HttpServletResponse response, String title, String string, String time) {

		try {

			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String fileName = URLEncoder.encode(StringUtils.trim(title) + ".xls", "UTF-8");
			fileName = StringUtils.replace(fileName, "+", "%20");// 空格转 %20
			response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
			WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			SheetSettings sheetSettings= sheet.getSettings();
			sheetSettings.setOrientation(PageOrientation.LANDSCAPE);
			sheetSettings.setTopMargin(0.2);

			sheetSettings.setBottomMargin(0.2);

			sheetSettings.setLeftMargin(0.2);

			sheetSettings.setRightMargin(0);

			sheetSettings.setHorizontalCentre(true);

			sheetSettings.setVerticalFreeze(4);
			sheetSettings.setPrintTitlesRow(0, 3);

//			sheetSettings.setVerticalCentre(true);
			// 设置excel的样式
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			WritableCellFormat format_title = new WritableCellFormat(wf);
			format_title.setWrap(true);
			format_title.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_title.setAlignment(Alignment.CENTRE);
			format_title.setVerticalAlignment(VerticalAlignment.CENTRE);
//			format_title.setBackground(Colour.VERY_LIGHT_YELLOW);
			// 样式2
			WritableCellFormat format_content = new WritableCellFormat(NumberFormats.TEXT);
			format_content.setWrap(true);
			format_content.setBorder(Border.ALL, BorderLineStyle.THIN);
			format_content.setFont(new WritableFont(WritableFont.createFont("宋体")));
			format_content.setAlignment(Alignment.CENTRE);
			format_content.setVerticalAlignment(VerticalAlignment.CENTRE);

			int row = 0;// 行
//			int num = 0;
			// 列头

			sheet.mergeCells(0, row, 22, row);

			String[] split = time.split("-");


			if("犇亿".equals(string) ) {
				sheet.addCell(new Label(0, 0, "南京犇亿环保科技有限公司收车后出车前车辆安全每日检查记录", format_title));
			}else if("龙昊".equals(string) ) {
				sheet.addCell(new Label(0, 0, "南京龙昊市政工程有限公司收车后出车前车辆每日检查记录", format_title));
			}else if("弘方".equals(string)){
				sheet.addCell(new Label(0, 0, "南京弘方环保科技有限公司收车后出车前车辆每日检查记录", format_title));
			}else if("方坤".equals(string)){
				sheet.addCell(new Label(0, 0, "南京方坤基础工程有限公司收车后出车前车辆每日检查记录", format_title));
			}
			else{
				sheet.addCell(new Label(0, 0, "南京" + string + "建设工程有限公司收车后出车前车辆安全每日检查记录", format_title));
			}


			row++;

			sheet.mergeCells(0, row, 22, row);
			sheet.addCell(new Label(0, 1, "日期："+split[0] + "年"
					+ split[1] + "月" + split[2] + "日"+"                                  制表人签名：                                 负责人签名：                                ", format_title));

			row++;

			sheet.setRowView(0, 500, false);
			sheet.setRowView(1, 500, false);
			sheet.setRowView(2, 500, false);
			sheet.setRowView(3, 500, false);


			for (int j = 0; j < 23; j++) {

				switch (j) {
				case 0:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row, "序号", format_title));
					break;
				case 1:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 9);
					sheet.addCell(new Label(j, row, "车牌号", format_title));
					break;
				case 2:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 7);
					sheet.addCell(new Label(j, row, "牌照清晰情况(制式)", format_title));
					break;
				case 3:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 7);
					sheet.addCell(new Label(j, row, "卫星定位在线情况", format_title));
					break;
				case 4:
					sheet.mergeCells(j, row, j + 3, row);
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row, "全车灯光", format_title));
					sheet.addCell(new Label(j, row + 1, "前", format_title));
					break;
				case 5:
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row + 1, "后", format_title));
					break;
				case 6:
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row + 1, "左", format_title));
					break;
				case 7:
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row + 1, "右", format_title));
					break;
				case 8:

					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 9);
					sheet.addCell(new Label(j, row, "转弯时对车外语音提示", format_title));
					break;
				case 9:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 6);
					sheet.addCell(new Label(j, row, "右转弯雷达", format_title));
					break;
				case 10:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 7);
					sheet.addCell(new Label(j, row, "转弯影像", format_title));
					break;
				case 11:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 7);
					sheet.addCell(new Label(j, row, "放大号牌清晰情况", format_title));
					break;
				case 12:
					sheet.mergeCells(j, row, j + 2, row);
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row, "车身反光贴", format_title));
					sheet.addCell(new Label(j, row + 1, "左", format_title));
					break;
				case 13:
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row + 1, "右", format_title));
					break;
				case 14:
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row + 1, "后", format_title));
					break;
				case 15:
					sheet.mergeCells(j, row, j + 2, row);
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row, "防护栏", format_title));
					sheet.addCell(new Label(j, row + 1, "左", format_title));
					break;
				case 16:
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row + 1, "右", format_title));
					break;
				case 17:
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row + 1, "后", format_title));
					break;
				case 18:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 5);
					sheet.addCell(new Label(j, row, "轮胎", format_title));
					break;
				case 19:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 9);
					sheet.addCell(new Label(j, row, "驾驶室内探头是否正常", format_title));
					break;
				case 20:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 8);
					sheet.addCell(new Label(j, row, "检查时间", format_title));
					break;
				case 21:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 9);
					sheet.addCell(new Label(j, row, "整改修复情况", format_title));
					break;
				case 22:
					sheet.mergeCells(j, row, j, row + 1);
					sheet.setColumnView(j, 9);
					sheet.addCell(new Label(j, row, "复查时间", format_title));
					break;
				}

			}
//
//
			row += 2;// 行
//			int num = 1;
			// 数据

			for (int n = 0; n < list.size(); n++) {

				sheet.addCell(new Label(0, row, (n + 1) + "", format_content));

				Check check = list.get(n);

				sheet.addCell(new Label(1, row, check.getPlateNO(), format_content));

				String checks = check.getChecks();

				sheet.setRowView(row, 500, false);

				if(StringUtils.isNotEmpty(checks)) {
					if(!checks.equals("停驶")) {
						JSONArray parseArray = JSON.parseArray(checks);
						if(parseArray.size()==17){
							JSONObject json = new JSONObject();
						    json.put("isshow",false);
							json.put("name","驾驶室内探头是否正常");
						    json.put("text", "");
						    json.put("list", "");
						    parseArray.add(json);
						}
						for (int i = 0; i < 18; i++) {
							JSONObject jsonObject = parseArray.getJSONObject(i);
							Boolean b = jsonObject.getBoolean("isshow");
							String t = jsonObject.getString("text");
							if (b) {
								sheet.addCell(new Label(i + 2, row, "√", format_content));
							} else {
								sheet.addCell(new Label(i + 2, row, t, format_content));
							}

						}
					}else {
						sheet.mergeCells(2, row, 19, row);
						sheet.addCell(new Label(2, row, checks, format_content));
					}


				}else {
					for (int i = 0; i < 19; i++) {
						sheet.addCell(new Label(i + 2, row, "", format_content));
					}
				}




				sheet.addCell(new Label(20, row, StringUtils.isEmpty(check.getCheckTime())?"/":check.getCheckTime(), format_content));



				if(StringUtils.isEmpty(check.getIsSure()) || "未确认".equals(check.getIsSure())) {
					sheet.addCell(new Label(21, row, "", format_content));
				}else if("无需确认".equals(check.getIsSure())) {
					sheet.addCell(new Label(21, row, "/", format_content));
				}else{
					sheet.addCell(new Label(21, row, StringUtils.isEmpty(check.getRemark())||check.getRemark().equals("null")  ?"/":check.getRemark(), format_content));
				}


				sheet.addCell(new Label(22, row, StringUtils.isEmpty(check.getSureTime())?"/":check.getSureTime(), format_content));

				row++;
			}

			wwb.write();
			wwb.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
