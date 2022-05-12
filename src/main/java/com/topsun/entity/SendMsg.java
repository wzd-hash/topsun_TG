package com.topsun.entity;

public class SendMsg {

	//cmdID
	private int cmdID;
	//发送内容
	private String sendMsg;
	//注释
	private String remark;
	//供应商：1-清研 2-瑞为
	private int termType;
	public int getCmdID() {
		return cmdID;
	}
	public void setCmdID(int cmdID) {
		this.cmdID = cmdID;
	}
	public String getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getTermType() {
		return termType;
	}
	public void setTermType(int termType) {
		this.termType = termType;
	}
	
	
	
}
