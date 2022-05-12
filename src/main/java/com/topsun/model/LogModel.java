package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class LogModel extends BaseRowModel{

	@ExcelProperty(value="序号",index=0)
	private String ID;
	@ExcelProperty(value="用户名",index=1)
	private String userName;
	@ExcelProperty(value="所属组织",index=2)
	private String subName;
	@ExcelProperty(value="操作内容",index=3)
	private String content;
	@ExcelProperty(value="操作时间",index=4)
	private String crateTime;
	@ExcelProperty(value="操作项",index=5)
	private String optType;
	@ExcelProperty(value="IP地址",index=6)
	private String loginIP;
	

	
	
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCrateTime() {
		return crateTime;
	}
	public void setCrateTime(String crateTime) {
		this.crateTime = crateTime;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}

	
	
	

}
