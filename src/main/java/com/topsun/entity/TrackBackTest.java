package com.topsun.entity;

public class TrackBackTest {

	//车辆ID
	private String busID;
	//车牌号
	private String plateNO;
	//GPS时间
	private String gps_time;
	//纬度
	private String latitude;
	//经度
	private String longitude;
	//驾驶员ID
	private String driverID;
	//流水号
	private String serial;
	//有无报警
	private String alarmStatus;
	//有无在线
	private String leadTime;
	//报警
	private String alarm;
	//状态
	private String state;

	private String busStatus;

	private String speed;

	private double x01;

	private double NowMile;


	public String getPlateNO() {
		return plateNO;
	}

	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}

	public double getX01() {
		return x01;
	}
	public void setX01(double x01) {
		this.x01 = x01;
	}
	public double getNowMile() {
		return NowMile;
	}
	public void setNowMile(double nowMile) {
		NowMile = nowMile;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}


	public String getBusStatus() {
		return busStatus;
	}
	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
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
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	public String getBusID() {
		return busID;
	}
	public void setBusID(String busID) {
		this.busID = busID;
	}
	public String getGps_time() {
		return gps_time;
	}
	public void setGps_time(String gps_time) {
		this.gps_time = gps_time;
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
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}



}
