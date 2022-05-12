package com.topsun.entity;

public class RemoteInfo {

	private Integer id;
	
	private Integer busID;
	
	private String cmdID;
	
	private String sendInfo;
	
	private String sendTime;
	
	private String responseInfo;
	
	private Integer cmdState;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusID() {
		return busID;
	}

	public void setBusID(Integer busID) {
		this.busID = busID;
	}

	public String getCmdID() {
		return cmdID;
	}

	public void setCmdID(String cmdID) {
		this.cmdID = cmdID;
	}

	public String getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(String sendInfo) {
		this.sendInfo = sendInfo;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(String responseInfo) {
		this.responseInfo = responseInfo;
	}

	public Integer getCmdState() {
		return cmdState;
	}

	public void setSmdState(Integer cmdState) {
		this.cmdState = cmdState;
	}
	
	
}
