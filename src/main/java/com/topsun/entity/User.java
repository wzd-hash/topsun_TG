package com.topsun.entity;

public class User {
	private Integer userID;
	//用户名
	private String userName;
	//密码
	private String password;
	//非加密密码
	private String passWord;
	//创建用户
	private Integer createUserID;
	//创建用户名称
	private String createUserName;
	//总公司ID
	private Integer headCompanyID;
	//联系人
	private String person;
	//联系电话
	private String phoneNum;
	//备注
	private String remark;
	//角色
	private Roles roles;
	//状态
	private Integer delStatus;
	
	private String openid;
	//售后联系方式
	private String contactPhone;
	
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(Integer createUserID) {
		this.createUserID = createUserID;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Integer getHeadCompanyID() {
		return headCompanyID;
	}
	public void setHeadCompanyID(Integer headCompanyID) {
		this.headCompanyID = headCompanyID;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
}
