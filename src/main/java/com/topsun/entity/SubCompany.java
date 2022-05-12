package com.topsun.entity;

import java.util.List;

public class SubCompany {

	private Integer subID;
	private String subName;
	private String subCode;
	private Integer headID;
	private String person;
	private String phoneNum;
	private String subAddress;
	private String createTime;
	private String remark;
	private Integer delStatus;
	private List<Roules> roulesList;
	private List<DriverInfo> driverList;
	
	
	
	public List<DriverInfo> getDriverList() {
		return driverList;
	}
	public void setDriverList(List<DriverInfo> driverList) {
		this.driverList = driverList;
	}
	public Integer getSubID() {
		return subID;
	}
	public void setSubID(Integer subID) {
		this.subID = subID;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
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
	public String getSubAddress() {
		return subAddress;
	}
	public void setSubAddress(String subAddress) {
		this.subAddress = subAddress;
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

	public List<Roules> getRoulesList() {
		return roulesList;
	}
	public void setRoulesList(List<Roules> roulesList) {
		this.roulesList = roulesList;
	}
	
	
	
	
	
	
	
}
