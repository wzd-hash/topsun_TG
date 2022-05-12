package com.topsun.entity;

import java.util.List;

public class Roules {

	private Integer roulesID;
	private String roulesName;
	private String roulesCode;
	private Integer subID;
	private Integer headID;
	private String person;
	private String phoneNum;
	private String mAddress;
	private String createTime;
	private String remark;
	private Integer delStatus;
	private List<BusInfo> busList;
	
	private Integer onlineNum;
	private Integer outlineNum;
	private Integer stopNum;
	private Integer noGpsNum;
	private Integer exceptionNum;
	
	
	
	
	public Integer getStopNum() {
		return stopNum;
	}
	public void setStopNum(Integer stopNum) {
		this.stopNum = stopNum;
	}
	public Integer getNoGpsNum() {
		return noGpsNum;
	}
	public void setNoGpsNum(Integer noGpsNum) {
		this.noGpsNum = noGpsNum;
	}
	public Integer getExceptionNum() {
		return exceptionNum;
	}
	public void setExceptionNum(Integer exceptionNum) {
		this.exceptionNum = exceptionNum;
	}
	public Integer getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(Integer onlineNum) {
		this.onlineNum = onlineNum;
	}
	public Integer getOutlineNum() {
		return outlineNum;
	}
	public void setOutlineNum(Integer outlineNum) {
		this.outlineNum = outlineNum;
	}
	public List<BusInfo> getBusList() {
		return busList;
	}
	public void setBusList(List<BusInfo> busList) {
		this.busList = busList;
	}
	public Integer getRoulesID() {
		return roulesID;
	}
	public void setRoulesID(Integer roulesID) {
		this.roulesID = roulesID;
	}
	public String getRoulesName() {
		return roulesName;
	}
	public void setRoulesName(String roulesName) {
		this.roulesName = roulesName;
	}
	public String getRoulesCode() {
		return roulesCode;
	}
	public void setRoulesCode(String roulesCode) {
		this.roulesCode = roulesCode;
	}
	
	public Integer getSubID() {
		return subID;
	}
	public void setSubID(Integer subID) {
		this.subID = subID;
	}
	public Integer getHeadID() {
		return headID;
	}
	public void setHeadID(Integer headID) {
		this.headID = headID;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getmAddress() {
		return mAddress;
	}
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	
	
}
