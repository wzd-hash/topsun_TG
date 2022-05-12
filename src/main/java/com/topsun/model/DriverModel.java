package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class DriverModel extends BaseRowModel{

	@ExcelProperty(value="序号",index=0)
	private Integer id;
	@ExcelProperty(value="姓名",index=1)
	private String driverName;
	@ExcelProperty(value="性别",index=2)
	private String sex;
	private Integer driverSex;
	@ExcelProperty(value="年龄",index=3)
	private Integer driverAge;
	@ExcelProperty(value="联系方式",index=4)
	private String phoneNum;
	@ExcelProperty(value="驾驶证号",index=5)
	private String licenseNO;
	@ExcelProperty(value="IC卡号",index=6)
	private String icCardNO;
	@ExcelProperty(value="从业资格证",index=7)
	private String qulificationNO;
	@ExcelProperty(value="发证机构",index=8)
	private String certifAuthority;
	@ExcelProperty(value="证件有效期",index=9)
	private String validityDate;
	@ExcelProperty(value="所属公司",index=10)
	private String subCompany;
	@ExcelProperty(value="所属集团",index=11)
	private String headCompany;
	private Integer subID;
	private Integer headID;
	@ExcelProperty(value="绑定车辆",index=12)
	private String plateNO;
	private String status;

	
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String reason;
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Integer getDriverSex() {
		return driverSex;
	}
	public void setDriverSex(Integer driverSex) {
		this.driverSex = driverSex;
	}
	public Integer getDriverAge() {
		return driverAge;
	}
	public void setDriverAge(Integer driverAge) {
		this.driverAge = driverAge;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getLicenseNO() {
		return licenseNO;
	}
	public void setLicenseNO(String licenseNO) {
		this.licenseNO = licenseNO;
	}
	public String getIcCardNO() {
		return icCardNO;
	}
	public void setIcCardNO(String icCardNO) {
		this.icCardNO = icCardNO;
	}
	public String getQulificationNO() {
		return qulificationNO;
	}
	public void setQulificationNO(String qulificationNO) {
		this.qulificationNO = qulificationNO;
	}
	public String getCertifAuthority() {
		return certifAuthority;
	}
	public void setCertifAuthority(String certifAuthority) {
		this.certifAuthority = certifAuthority;
	}
	public String getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}
	
	public String getSubCompany() {
		return subCompany;
	}
	public void setSubCompany(String subCompany) {
		this.subCompany = subCompany;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public String getHeadCompany() {
		return headCompany;
	}
	public void setHeadCompany(String headCompany) {
		this.headCompany = headCompany;
	}
	
	
	
	
	
}
