package com.topsun.controller;

import com.alibaba.fastjson.JSONObject;
import com.topsun.entity.*;
import com.topsun.service.*;
import com.topsun.service.impl.ReceiveQueryTerminalParamWebServiceService;
import com.topsun.util.DateUtils;
import com.topsun.util.FtpUtils;
import com.topsun.util.MathUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@RestController
@RequestMapping("/history")
public class HistoryController extends BaseController{

	@Autowired
	private DataLocationService dataLocationService;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private OrgService orgService;
	
	
	@Autowired
	private RemoteService remoteService;
	
	@Value("${video.outputurl}")
	private String ftpUrl;
	
	@Value("${video.inputurl.ip}")
	private String videoIP;
	
	@Value("${video.inputurl.port}")
	private String videoPort;
	
	@Value("${ftp.ip}")
	private String ftpIP;
	
	@Value("${ftp.port}")
	private String ftpPort;
	
	@Value("${ftp.username}")
	private String ftpUserName;
	
	@Value("${ftp.password}")
	private String ftpPassword;
	
	
	private static String ffmpegUrl = ""; 
	
	

	
	@RequestMapping("/listTrackBack")
	public Object listTrackBackTest(HttpServletRequest request){
		
		Map<String, Object> map = getSessionInfo();
		
		try {

			
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String busID = request.getParameter("busID");

			map.put("busID", busID);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			
			List<TrackBackTest> listTrackBackTest = dataLocationService.listTrackBackTest(map);

			List<Location> locationList = locationService.listLocationByType(null);
			
			double x01 = listTrackBackTest.get(0).getX01();
			
			for (TrackBackTest trackBackTest : listTrackBackTest) {
				trackBackTest.setNowMile(new BigDecimal(trackBackTest.getX01() - x01).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			
			
			map.clear();
			
			map.put("code", 0);
			map.put("list", listTrackBackTest);
			map.put("locationList", locationList);
		}catch(Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("code", 1);

		}
		
		return map;
	}
	

	


	/*
	 * 服务器录像回放
	 */
	@RequestMapping("/listMediaAttachment")
	public Object listMediaAttachment(HttpServletRequest request){
		
		Map<String, Object> map = getSessionInfo();
		
		String busID = request.getParameter("busID");
		
		String startTime = request.getParameter("startTime");
		
		String endTime = request.getParameter("endTime");
		
		String file_format = request.getParameter("file_format");
		
		String attachment_type = request.getParameter("attachment_type");
		
		String source = request.getParameter("source");
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("file_format", file_format);
		map.put("attachment_type", attachment_type);
		map.put("busID", busID);
		map.put("source", source);
		
		map.put("tableName", "bus_alarm_"+DateUtils.getFirstLastDay(startTime));
		map.put("databaseName", "alarm_topsun");
		
		if (alarmService.tableIfExists(map)==0) {
			map.clear();
			map.put("list", new ArrayList<>());
			map.put("code", 0);	
			return map;
		}
		List<MediaInfo> list = alarmService.listMediaInfo(map);
		map.clear();
		map.put("list", list);
		map.put("code", 0);
		return map;
	}
	
	@RequestMapping("/getDriverInTrackBack")
	public Object getDriverInTrackBack(HttpServletRequest request){
		
		Map<String, Object> map = getSessionInfo();
		
		try {

			String busID = request.getParameter("busID");
			String driverID = request.getParameter("driverID");
			String gpsTime = request.getParameter("gpsTime");
			
			
			map.put("busID", busID);
			
			
			map.put("gpsTime", gpsTime);
			
			
			map.put("tableName", getTable(gpsTime));
			
			//查到外设ID
			AlarmInfoInTack alarmInfoInTack = alarmService.getAlarmInfoInTack(map);

			DriverInfo driverInfo = new DriverInfo();
			
			driverInfo.setDriverID(Integer.valueOf(driverID));
			
			map.put("driver", driverInfo);
			
			List<DriverInfo> driver = driverService.getDriver(map);
			
			if (StringUtils.isNotEmpty(busID)) {
				map.put("busID", busID.split(","));
			}
			
			List<BusInfo> listBusInfo = busService.listBusInfo(map);
			
			HashMap<String, Object> info = new HashMap<String, Object>();
			
			info.put("driverName", driver.get(0).getDriverName());
			info.put("phoneNum", driver.get(0).getPhoneNum());
			info.put("licenseNO", driver.get(0).getLicenseNO());
			info.put("roulesName", listBusInfo.get(0).getCompany().getCompanyName());
			try {
			info.put("perID", alarmInfoInTack.getPerID());
			info.put("time", alarmInfoInTack.getTime());
			info.put("speed", alarmInfoInTack.getSpeed());
			info.put("x64", alarmInfoInTack.getX64());
			info.put("x65", alarmInfoInTack.getX65());
			info.put("x66", alarmInfoInTack.getX66());
			info.put("x67", alarmInfoInTack.getX67());
			info.put("x67", alarmInfoInTack.getX67());
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			map.clear();
			
			map.put("code", 0);
			map.put("info", info);
		}catch(Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("code", 1);

		}
		
		return map;
	}
	
	
	private String getTable(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		
		Calendar cal = Calendar.getInstance();  
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);  
        if (1 == dayWeek) {  
           cal.add(Calendar.DAY_OF_MONTH, -1);  
        }
        
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        
        
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        
        String tableName = "alarm_topsun.bus_alarm_"+simpleDateFormat.format(cal.getTime());
        
        cal.add(Calendar.DATE, 6);
        
        tableName = tableName +"_"+simpleDateFormat.format(cal.getTime());
		
        return tableName;
	}
	
	/*
	 * 拉流地址
	 */
	@RequestMapping("/returnFtpUrl")
	public Object returnFtpUrl(){
		Map<String, Object> map = new HashMap<>();
		
		String busID = request.getParameter("busID");
		
		if (StringUtils.isNotEmpty(busID)) {
			map.put("busID", busID);
			List<BusInfo> list = orgService.listOrgTreeBus(map);
			map.clear();
			map.put("list", list);
			
		}
		map.put("ftpUrl", ftpUrl);
		return map;
	}
	
	/*
	 * 设备录像回放
	 */
	@RequestMapping("/historyVideo")
	public Object historyVideo(HttpServletRequest request){
		
		//车辆ID
		String busID = request.getParameter("busID");
		//终端SIM卡号
		String simCard = request.getParameter("simCard");
		//通道号
		String channelNO = request.getParameter("channelNO");
		//操作类型
		String optType = request.getParameter("optType");
		
		Map<String, String> map = new HashMap<>();
		map.put("busID", busID);
		map.put("simCard", simCard);
		map.put("channelNO", channelNO);
		map.put("optType", optType);
		
		switch (optType) {
		case "0":
			map.put("startTime", request.getParameter("startTime"));
			map.put("endTime", request.getParameter("endTime"));
			break;
		case "3":
			map.put("count", request.getParameter("count"));
			break;
		case "5":
			map.put("time", request.getParameter("time"));
			break;
		}
		
		String cmdID = "";
		if (optType.equals("0")) {
			cmdID = "37377";
		}else{
			cmdID = "37378";
		}
		
		map.put("cmdID", cmdID);
		
		String content = getContent(map);
		
		map.put("content", content);
		
		ReceiveQueryTerminalParamWebServiceService mywebService = new ReceiveQueryTerminalParamWebServiceService();
		ReceiveQueryTerminalParamWebService rq = mywebService.getReceiveQueryTerminalParamWebServicePort();
		
		int state = Integer.parseInt(rq.sendParamQueryCmdSeq(simCard+","+cmdID+","+content));
		System.err.println("设备录像回放状态:state--"+state);
		
		if (state == 1) {
			
			Integer result = remoteService.insertVideoHistory(map);
			if (result>0) {
				return 0;
			}
		}
		return -1;
	}
	
	
	String getContent(Map<String, String> map){
		
		String content = "";
		
		String channelNO = String.format("%02x", Integer.parseInt(map.get("channelNO")));
		String optType = String.format("%02x", Integer.parseInt(map.get("optType")));
		
		switch (optType) {
		case "00"://开始回放
			String ip = MathUtil.toHexString(videoIP);
			String length = String.format("%02x", ip.length()/2);
			String port =String.format("%x", Integer.parseInt(videoPort));
			String startTime =  map.get("startTime");
			String endTime =  map.get("endTime");
			content = length+ip+port+"0000"+channelNO+"0202010000"
					+startTime.replaceAll(" ", "").replaceAll("[:-]+", "").substring(2)
					+endTime.replaceAll(" ", "").replaceAll("[:-]+", "").substring(2);
			break;
		case "01"://暂停回放
		case "02"://结束回放
			content=channelNO+optType+"00000000000000";
			break;
		case "03"://快进回放
			String count = String.format("%02x", Integer.parseInt(map.get("count")));
			content=channelNO+optType+count+"000000000000";
			break;
		case "05"://拖动回放
			String time =  map.get("time");
			content=channelNO+optType+"00"+time.replaceAll(" ", "").replaceAll("[:-]+", "").substring(2);
			break;
		case "07"://继续播放
			content=channelNO+"0000000000000000";
			break;
		}
		return content;
	}
	
	
	
	
	
	/*
	 * 设备上传视频到ftp
	 * 9206:37382
	 */
	@RequestMapping("/queryFtpVideo")
	public Object queryFtpVideo(HttpServletRequest request){
		
		String busID = request.getParameter("busID");
		//开始时间
		String startTime = request.getParameter("startTime");
		//结束时间
		String endTime = request.getParameter("endTime");
		//通道号
		String channelID = request.getParameter("channelID");
		//simCard 
		String simCard = request.getParameter("simCard");



		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		FTPClient client;
		

		RemoteInfo remoteInfo = null;
		try {

			client = FtpUtils.isConnection();
			
			int check = checkFile(startTime, endTime, channelID, simCard, client);
			
			if(check == 2) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("startTime", startTime);
				jsonObject.put("endTime", endTime);
				jsonObject.put("channelID", channelID);
				jsonObject.put("ftpUrl", "/"+simCard+"/"+channelID+"/");
				jsonObject.put("ftpIP", ftpIP);
				jsonObject.put("ftpPort", ftpPort);
				jsonObject.put("ftpUserName", ftpUserName);
				jsonObject.put("ftpPassword", ftpPassword);
				
				remoteInfo = new RemoteInfo();
				remoteInfo.setBusID(Integer.parseInt(busID));
				remoteInfo.setCmdID("37382");
				remoteInfo.setSendInfo(jsonObject.toJSONString());
				
				Integer result = remoteService.sendCommand(remoteInfo);
				
				Map<String, Object> map = new HashMap<>();
				map.put("id", remoteInfo.getId());
				map.put("channelID", channelID);
				map.put("fileTime", startTime);
				ReceiveQueryTerminalParamWebServiceService mywebService = new ReceiveQueryTerminalParamWebServiceService();
				ReceiveQueryTerminalParamWebService rq = mywebService.getReceiveQueryTerminalParamWebServicePort();
				
				Integer state = Integer.parseInt(rq.sendParamQueryCmd(remoteInfo.getId().toString(), remoteInfo.getCmdID(), "", ""));
				Thread.sleep(3000);
				
				if (state == 1) {
					
					
					int checkFile = checkFile(startTime, endTime, channelID, simCard, client);
					
					resultMap.put("code", checkFile);
					
					
						
				}
			}else {
				resultMap.put("code", check);
			}
			
				
			
		} catch (Exception e) {
			resultMap.put("code", -1);//系统报错
			e.printStackTrace();
		}
		

		return resultMap;
	}





	private int checkFile(String startTime, String endTime, String channelID, String simCard, FTPClient client) throws Exception {
		if(simCard.contains("1399")) {
			String filePath = "/"+simCard+"/"+channelID+"/CH"+channelID+'-'
					+startTime.replaceAll(" ", "").replaceAll("[:-]+", "")+"-"
					+endTime.replaceAll(" ", "").replaceAll("[:-]+", "")+".264";
			
			//获取文件大小
			long size1 =  FtpUtils.getFileSize(client, filePath);
			
			Thread.sleep(2000);
			
			long size2 =  FtpUtils.getFileSize(client, filePath);
			if(size1==-1&&size2== -1) {
				return 2;//文件不存在
			}else if (size1==size2&&size1!=0&&size2!= 0) {

//				Runtime.getRuntime().exec(ffmpegUrl +" -i "+filePath+" -vcodec copy -f mp4 -y "+filePath);

				return 0;
			}else{
				return 1;//文件正在下载
			}
		}else if(simCard.contains("1499") || simCard.contains("1599")) {
			//分割时间
			List<String> cutDate = DateUtils.cutDate("N",startTime, endTime,5);
			for (int i = 0;i<cutDate.size()-1;i++) {
				String filePath = "/"+simCard+"/"+channelID+"/"+ cutDate.get(i)+"_to_"+cutDate.get(i+1)+"_CH"+channelID+".avi";
				
				//获取文件大小
				long size1 =  FtpUtils.getFileSize(client, filePath);
				
				Thread.sleep(2000);
				
				long size2 =  FtpUtils.getFileSize(client, filePath);
				if(size1==-1&&size2== -1) {
					return 2;//文件不存在
				}else if (size1==size2&&size1!=0&&size2!= 0) {
					continue;
				}else{
					return 1;//文件正在下载
				}
			}
			
			return 0;
			
		}
		return 2;
	}
	
	
	/*
	 * ftp下载到本地
	 */
	@RequestMapping("/downLoadFtp")
	public Object downLoadFtp(HttpServletRequest request,HttpServletResponse response){
		
		
		
		Map<String, Integer> map = new HashMap<>();
		
		//通讯号
		String simCard = request.getParameter("simCard");
		//通道号
		String channelNo = request.getParameter("channelNo");
		//开始时间
		String startTime = request.getParameter("startTime");
		//结束时间
		String endTime = request.getParameter("endTime");
		
		FTPClient client = null;
		ServletOutputStream outputStream = null;
		try {
			
			client = FtpUtils.isConnection();
			if(simCard.contains("1399")) {
				String filePath = "/"+simCard+"/"+channelNo+"/CH"+channelNo+'-'
						+startTime.replaceAll(" ", "").replaceAll("[:-]+", "")+"-"
						+endTime.replaceAll(" ", "").replaceAll("[:-]+", "")+".264";
				String newFileName = "CH"+channelNo+'-'
						+startTime.replaceAll(" ", "").replaceAll("[:-]+", "")+"-"
						+endTime.replaceAll(" ", "").replaceAll("[:-]+", "")+".264";
				outputStream = response.getOutputStream();
				 // 清空response
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Disposition", "attachment;filename=" + new String(newFileName.getBytes()));
				FtpUtils.downFile(newFileName, filePath,outputStream);
			}else if(simCard.contains("1499")) {
				//分割时间
				List<String> cutDate = DateUtils.cutDate(startTime, endTime);
				for (int i = 0;i<cutDate.size()-1;i++) {
					String filePath = "/"+simCard+"/"+channelNo+"/"+ cutDate.get(i)+"_to_"+cutDate.get(i+1)+"_CH"+channelNo+".avi";
					String newFileName = cutDate.get(i)+"_to_"+cutDate.get(i+1)+"_CH"+channelNo+".avi";
					outputStream = response.getOutputStream();
					 // 清空response
					response.reset();
					// 设置response的Header
					response.addHeader("Content-Disposition", "attachment;filename=" + new String(newFileName.getBytes()));
					FtpUtils.downFile(newFileName, filePath,outputStream);
				}
			}
			
			
		
		} catch (Exception e) {
			map.put("code", -1);//系统报错
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
	
}
