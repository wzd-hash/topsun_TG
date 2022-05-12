package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class MendModel extends BaseRowModel {

	@ExcelProperty(value="序号",index=0)
	private Integer ID;
	private Integer busID;
	@ExcelProperty(value="联系方式",index=3)
	private String phone;
	@ExcelProperty(value="联系人",index=2)
	private String name;
	@ExcelProperty(value="维修人员",index=8)
	private String mendPeople;
	@ExcelProperty(value="维修状态",index=6)
	private String status;
	@ExcelProperty(value="维修时间",index=7)
	private String mendTime;
	@ExcelProperty(value="创建时间",index=5)
	private String createTime;
	@ExcelProperty(value="维修说明",index=11)
	private String mendReport;
	@ExcelProperty(value="问题说明",index=10)
	private String question;
	@ExcelProperty(value="车牌",index=1)
	private String plateNO;
	private String driverName;
	private String simCard;
	@ExcelProperty(value="地点",index=4)
	private String location;
	@ExcelProperty(value="维修电话",index=9)
	private String mendPhone;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getBusID() {
		return busID;
	}
	public void setBusID(Integer busID) {
		this.busID = busID;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMendPeople() {
		return mendPeople;
	}
	public void setMendPeople(String mendPeople) {
		this.mendPeople = mendPeople;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMendTime() {
		return mendTime;
	}
	public void setMendTime(String mendTime) {
		this.mendTime = mendTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMendReport() {
		return mendReport;
	}
	public void setMendReport(String mendReport) {
		this.mendReport = mendReport;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getSimCard() {
		return simCard;
	}
	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMendPhone() {
		return mendPhone;
	}
	public void setMendPhone(String mendPhone) {
		this.mendPhone = mendPhone;
	}
	
	
}
