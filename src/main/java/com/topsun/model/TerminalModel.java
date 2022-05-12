package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class TerminalModel extends BaseRowModel{
	@ExcelProperty(value="序号",index=0)
	private Integer id;
	//SIM卡号
	@ExcelProperty(value="SIM卡号",index=1)
	private String simCard;
	//车队/线路ID
	@ExcelProperty(value="车队/线路名称",index=2)
	private String roulesName;
	//终端编号
	@ExcelProperty(value="终端ID",index=3)
	private String termNO;
	//通道数
	@ExcelProperty(value="通道数",index=4)
	private Integer channelCount;
	//终端型号
	@ExcelProperty(value="终端型号",index=5)
	private String termModel;
	//制造商ID
	@ExcelProperty(value="制造商ID",index=6)
	private String manufacturerID;
	//省域ID
	@ExcelProperty(value="省域ID",index=7)
	private String provinceID;
	//市域ID
	@ExcelProperty(value="市域ID",index=8)
	private String cityID;
	//创建时间 
	@ExcelProperty(value="创建时间",index=9)
	private String createTime;
	//注册时间
	@ExcelProperty(value="注册时间",index=10)
	private String loginTime;
	//注销时间
	@ExcelProperty(value="注销时间",index=11)
	private String logoutTime;
	//绑定车牌号
	@ExcelProperty(value="绑定车牌号",index=12)
	private String plateNO;
	
	private String reason;
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTermNO() {
		return termNO;
	}
	public void setTermNO(String termNO) {
		this.termNO = termNO;
	}
	public String getRoulesName() {
		return roulesName;
	}
	public void setRoulesName(String roulesName) {
		this.roulesName = roulesName;
	}
	public String getSimCard() {
		return simCard;
	}
	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}
	public Integer getChannelCount() {
		return channelCount;
	}
	public void setChannelCount(Integer channelCount) {
		this.channelCount = channelCount;
	}
	public String getTermModel() {
		return termModel;
	}
	public void setTermModel(String termModel) {
		this.termModel = termModel;
	}
	public String getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(String manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	public String getProvinceID() {
		return provinceID;
	}
	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	
}
