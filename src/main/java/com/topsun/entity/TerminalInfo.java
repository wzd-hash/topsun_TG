package com.topsun.entity;

public class TerminalInfo {

	private Integer termID;
	//终端编号
	private String termNO;
	//终端型号
	private String termModel;
	//车队/线路ID
	private Integer companyID;
	//线路名称
	private String companyName;
	//SIM卡号
	private String simCard;
	//制造商ID
	private String manufacturerID;
	//终端类型
	private String termType;
	//安装状态
	private Integer installStatus;
	//创建时间
	private String createTime;
	//注册时间
	private String loginTime;
	//注销时间
	private String logoutTime;
	//删除状态
	private Integer delStatus;
	//省域ID
	private String provinceID;
	//市域ID
	private String cityID;
	//鉴权码
	private String authentication;
	//车牌号
	private String plateNO;
	//通道数
	private Integer channelCount;
	//ICCID
	private String iccid;
	
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public Integer getChannelCount() {
		return channelCount;
	}
	public void setChannelCount(Integer channelCount) {
		this.channelCount = channelCount;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public Integer getTermID() {
		return termID;
	}
	public void setTermID(Integer termID) {
		this.termID = termID;
	}
	public String getTermNO() {
		return termNO;
	}
	public void setTermNO(String termNO) {
		this.termNO = termNO;
	}
	public String getTermModel() {
		return termModel;
	}
	public void setTermModel(String termModel) {
		this.termModel = termModel;
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
	public String getSimCard() {
		return simCard;
	}
	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}
	public String getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(String manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public Integer getInstallStatus() {
		return installStatus;
	}
	public void setInstallStatus(Integer installStatus) {
		this.installStatus = installStatus;
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
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
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
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public TerminalInfo() {
		super();
	}
	public TerminalInfo(Integer termID) {
		super();
		this.termID = termID;
	}
	
	
	
}
