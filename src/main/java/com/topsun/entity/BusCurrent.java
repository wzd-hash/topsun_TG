package com.topsun.entity;

import java.util.List;

public class BusCurrent {

	private String busID;
	private String driverID;
	private String server_time;
	private String state;
	private String latitude;
	private String longitude;
	private Double speed;
	private String busStatus;
	private String driverName;
	private String licenseNO;
	private String icCardNO;
	private String subName;
	private String plateNO;
	private List<AlarmInfo> list;
	public String getBusID() {
		return busID;
	}
	public void setBusID(String busID) {
		this.busID = busID;
	}
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	public String getServer_time() {
		return server_time;
	}
	public void setServer_time(String server_time) {
		this.server_time = server_time;
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
	public String getBusStatus() {
		return busStatus;
	}
	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getLicenseNO() {
		return licenseNO;
	}
	public void setLicenseNO(String licenseNO) {
		this.licenseNO = licenseNO;
	}
	public String getIcCardNO() {
		return icCardNO;
	}
	public void setIcCardNO(String icCardNO) {
		this.icCardNO = icCardNO;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public List<AlarmInfo> getList() {
		return list;
	}
	public void setList(List<AlarmInfo> list) {
		this.list = list;
	}
	
	
	

}
