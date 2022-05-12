package com.topsun.entity;

import com.alibaba.fastjson.JSONArray;

public class Check {

	private String plateNO;
	private String busID;
	private String time;
	private String image;
	private String checks;
	private String remark;
	private JSONArray parseArray;
	private String isSure;
	private String SBImage;
	private String checkTime;
	private String sureTime;
	
	
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getSureTime() {
		return sureTime;
	}
	public void setSureTime(String sureTime) {
		this.sureTime = sureTime;
	}
	public String getSBImage() {
		return SBImage;
	}
	public void setSBImage(String sBImage) {
		SBImage = sBImage;
	}
	public String getIsSure() {
		return isSure;
	}
	public void setIsSure(String isSure) {
		this.isSure = isSure;
	}
	public JSONArray getParseArray() {
		return parseArray;
	}
	public void setParseArray(JSONArray parseArray) {
		this.parseArray = parseArray;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public String getBusID() {
		return busID;
	}
	public void setBusID(String busID) {
		this.busID = busID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getChecks() {
		return checks;
	}
	public void setChecks(String checks) {
		this.checks = checks;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
