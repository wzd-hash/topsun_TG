package com.topsun.entity;

public class BusInfo {
	
	private Integer busID;
	//车牌号
	private String plateNO;
	//线路
	private Company company;
	//司机
	private DriverInfo driverInfo;
	//车牌颜色
	private String plateColor;
	//终端
	private TerminalInfo terminalInfo;
	//创建时间
	private String createTime;
	//首次上牌时间
	private String startTime;
	//车型
	private String busType;
	//删除状态
	private Integer delStatus;
	
	//在线状态
	private Integer busStatus;
	
	//报警状态
	private Integer alarmStatus;
	
	private Integer id;
	
	private String label;
	
	private String type;
	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(Integer alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public Integer getBusStatus() {
		return busStatus;
	}
	public void setBusStatus(Integer busStatus) {
		this.busStatus = busStatus;
	}
	public Integer getBusID() {
		return busID;
	}
	public void setBusID(Integer busID) {
		this.busID = busID;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public DriverInfo getDriverInfo() {
		return driverInfo;
	}
	public void setDriverInfo(DriverInfo driverInfo) {
		this.driverInfo = driverInfo;
	}
	public String getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	public TerminalInfo getTerminalInfo() {
		return terminalInfo;
	}
	public void setTerminalInfo(TerminalInfo terminalInfo) {
		this.terminalInfo = terminalInfo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	
}
