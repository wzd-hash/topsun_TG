package com.topsun.entity;

public class DriverInfo {

	private Integer driverID;
	private String driverName;
	private Integer driverSex;
	private Integer driverAge;
	private String phoneNum;
	private String licenseNO;
	private String icCardNO;
	private String createTime;
	private Integer delStatus;
	private String qulificationNO;
	private String certifAuthority;
	private String validityDate;
	private String status;
	private Integer subID;
	private Integer headID;
	private SubCompany subCompany;
	private HeadCompany headCompany;
	private BusInfo busInfo;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getDriverID() {
		return driverID;
	}
	public void setDriverID(Integer driverID) {
		this.driverID = driverID;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
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
	
	public SubCompany getSubCompany() {
		return subCompany;
	}
	public void setSubCompany(SubCompany subCompany) {
		this.subCompany = subCompany;
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
	public BusInfo getBusInfo() {
		return busInfo;
	}
	public void setBusInfo(BusInfo busInfo) {
		this.busInfo = busInfo;
	}
	public DriverInfo() {
		super();
	}
	public DriverInfo(Integer driverID) {
		super();
		this.driverID = driverID;
	}
	public HeadCompany getHeadCompany() {
		return headCompany;
	}
	public void setHeadCompany(HeadCompany headCompany) {
		this.headCompany = headCompany;
	}
	

	
	
	
	
}
