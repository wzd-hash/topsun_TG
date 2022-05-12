package com.topsun.entity;

public class AlarmSetting {

	private Integer headID;
	private Integer subID;
	private Integer time;
	private Integer isUser;
	private String topLevel;
	private String midLevel;
	private String lowLevel;
	private Object alarmtype;
	private String uptime;
	
	
	public Integer getSubID() {
		return subID;
	}
	public void setSubID(Integer subID) {
		this.subID = subID;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public Integer getHeadID() {
		return headID;
	}
	public void setHeadID(Integer headID) {
		this.headID = headID;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public Integer getIsUser() {
		return isUser;
	}
	public void setIsUser(Integer isUser) {
		this.isUser = isUser;
	}
	public String getTopLevel() {
		return topLevel;
	}
	public void setTopLevel(String topLevel) {
		this.topLevel = topLevel;
	}
	public String getMidLevel() {
		return midLevel;
	}
	public void setMidLevel(String midLevel) {
		this.midLevel = midLevel;
	}
	public String getLowLevel() {
		return lowLevel;
	}
	public void setLowLevel(String lowLevel) {
		this.lowLevel = lowLevel;
	}
	public Object getAlarmtype() {
		return alarmtype;
	}
	public void setAlarmtype(Object alarmtype) {
		this.alarmtype = alarmtype;
	}
	
	
	
}
