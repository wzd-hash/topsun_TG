package com.topsun.entity;

import java.util.List;

public class HeadCompany {

	private Integer headID;
	private String headName;
	private String headCode;
	private String headAddress;
	private String person;
	private String phoneNum;
	private String createTime;
	private String remark;
	private Integer delStatus;
	private List<Queue> queueList;
	public Integer getHeadID() {
		return headID;
	}
	public void setHeadID(Integer headID) {
		this.headID = headID;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getHeadCode() {
		return headCode;
	}
	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}
	public String getHeadAddress() {
		return headAddress;
	}
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
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
	public List<Queue> getQueueList() {
		return queueList;
	}
	public void setQueueList(List<Queue> queueList) {
		this.queueList = queueList;
	}
	
	
	
	
}
