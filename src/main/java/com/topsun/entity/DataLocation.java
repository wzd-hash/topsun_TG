package com.topsun.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.topsun.controller.BaseController;

public class DataLocation extends BaseRowModel {
	
	//车辆ID
	private Integer busID;

	private String headID;

	private String subID;

	private String roulesID;
	//司机ID
	private Integer driverID;
	//车辆信息+司机信息
	private BusInfo busInfo;
	//服务器时间
	private String server_time;
	//gps时间
	private String gps_time;
	//报警标志
	private String alarm;
	//状态
	private String state;

	@ExcelProperty(value="车牌号",index=0)
	private String plateNO;

	//纬度
	@ExcelProperty(value="纬度",index=1)
	private String latitude;
	//经度
	@ExcelProperty(value="经度",index=2)
	private String longitude;
	//速度
	@ExcelProperty(value="速度",index=3)
	private Double speed;

	//方向
	private Integer direction;
	//累计里程
	private Double x01;
	//油量
	private Double x02;
	//速度
	private Integer x03;
	//需要人工确认的ID数
	private Integer x04;
	//ADAS
	private Integer x64;
	//DSM
	private Integer x65;
	//TPMS
	private Integer x66;
	//BSD
	private Integer x67;
	//流水号
	private Integer serial;
	//时间差
	private String leadTime;
	private String busStatus;
	//上线时间
	private String loginTime;
	//下线时间
	private String logoutTime;

	private int remark;

	@ExcelProperty(value="开始gps时间",index=4)
	private String startgps_time;

	@ExcelProperty(value="结束gps时间",index=5)
	private String 	endgps_time;

	@ExcelProperty(value="持续时间",index=6)
	private String times;

	@ExcelProperty(value="报警位置",index=7)
	private String location;


	public String getHeadID() {
		return headID;
	}

	public void setHeadID(String headID) {
		this.headID = headID;
	}

	public String getSubID() {
		return subID;
	}

	public void setSubID(String subID) {
		this.subID = subID;
	}

	public String getRoulesID() {
		return roulesID;
	}

	public void setRoulesID(String roulesID) {
		this.roulesID = roulesID;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getPlateNO() {
		return plateNO;
	}

	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}

	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	public int getRemark() {
		return remark;
	}
	public void setRemark(int remark) {
		this.remark = remark;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getBusStatus() {
		return busStatus;
	}
	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
	}
	public Integer getBusID() {
		return busID;
	}
	public void setBusID(Integer busID) {
		this.busID = busID;
	}
	public Integer getDriverID() {
		return driverID;
	}
	public void setDriverID(Integer driverID) {
		this.driverID = driverID;
	}
	public BusInfo getBusInfo() {
		return busInfo;
	}
	public void setBusInfo(BusInfo busInfo) {
		this.busInfo = busInfo;
	}
	public String getServer_time() {
		return server_time;
	}
	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}
	public String getGps_time() {
		return gps_time;
	}
	public void setGps_time(String gps_time) {
		this.gps_time = gps_time;
	}
	public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public Double getX01() {
		return x01;
	}
	public void setX01(Double x01) {
		this.x01 = x01;
	}
	public Double getX02() {
		return x02;
	}
	public void setX02(Double x02) {
		this.x02 = x02;
	}
	public Integer getX03() {
		return x03;
	}
	public void setX03(Integer x03) {
		this.x03 = x03;
	}
	public Integer getX04() {
		return x04;
	}
	public void setX04(Integer x04) {
		this.x04 = x04;
	}
	public Integer getX64() {
		return x64;
	}
	public void setX64(Integer x64) {
		this.x64 = x64;
	}
	public Integer getX65() {
		return x65;
	}
	public void setX65(Integer x65) {
		this.x65 = x65;
	}
	public Integer getX66() {
		return x66;
	}
	public void setX66(Integer x66) {
		this.x66 = x66;
	}
	public Integer getX67() {
		return x67;
	}
	public void setX67(Integer x67) {
		this.x67 = x67;
	}
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getEndgps_time() {
		return endgps_time;
	}

	public void setEndgps_time(String endgps_time) {
		this.endgps_time = endgps_time;
	}

	public String getStartgps_time() {
		return startgps_time;
	}

	public void setStartgps_time(String startgps_time) {
		this.startgps_time = startgps_time;
	}

	@Override
	public String toString() {
		return "DataLocation{" +
				"busID=" + busID +
				", headID='" + headID + '\'' +
				", subID='" + subID + '\'' +
				", roulesID='" + roulesID + '\'' +
				", driverID=" + driverID +
				", busInfo=" + busInfo +
				", server_time='" + server_time + '\'' +
				", gps_time='" + gps_time + '\'' +
				", alarm='" + alarm + '\'' +
				", state='" + state + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", speed=" + speed +
				", direction=" + direction +
				", x01=" + x01 +
				", x02=" + x02 +
				", x03=" + x03 +
				", x04=" + x04 +
				", x64=" + x64 +
				", x65=" + x65 +
				", x66=" + x66 +
				", x67=" + x67 +
				", serial=" + serial +
				", leadTime='" + leadTime + '\'' +
				", busStatus='" + busStatus + '\'' +
				", loginTime='" + loginTime + '\'' +
				", logoutTime='" + logoutTime + '\'' +
				", location='" + location + '\'' +
				", remark=" + remark +
				'}';
	}
}
