package com.topsun.entity;

public class Company {
	//组织ID
	private Integer companyID;
	//组织名称
	private String companyName;
	//组织代码
	private String companyCode;
	//联系人
	private String person;
	//联系方式
	private String phoneNum;
	//联系地址
	private String companyAddress;
	//创建时间
	private String createTime;
	//备注
	private String remark;
	//状态
	private Integer delStatus;
	//父组织ID
	private Integer parentID;
	//父组织名称
	private String parentName;
	//祖父组织ID
	private Integer grandParentID;
	//祖父组织名称
	private String grandParentName;
	
	
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	public Integer getGrandParentID() {
		return grandParentID;
	}
	public void setGrandParentID(Integer grandParentID) {
		this.grandParentID = grandParentID;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public Integer getCompanyID() {
		return companyID;
	}
	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
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
	public Company() {
		super();
	}
	public Company(Integer companyID) {
		super();
		this.companyID = companyID;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getGrandParentName() {
		return grandParentName;
	}
	public void setGrandParentName(String grandParentName) {
		this.grandParentName = grandParentName;
	}
	
}
