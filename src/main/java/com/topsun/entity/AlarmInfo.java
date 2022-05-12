package com.topsun.entity;

public class AlarmInfo {

	
	private Integer id;
	private Integer busID;
	
	private Integer driverID;
	private Integer perID;
	
	private DriverInfo driverInfo;
	
	private BusInfo busInfo;
	//报警时间
	private String alarmTime;
	//报警ID
	private Integer alarmID;
	//报警状态
	private Integer alarmState;
	//报警级别
	private String alarmLevel;
	//纬度
	private String latitude;
	//经度
	private String longitude;
	//车速
	private Integer speed;
	//车辆状态
	private Integer busState;
	//报警标志
	private String alarmSign;
	//处理状态
	private Integer handleState;
	
	//报警类型
	private String alarmType;
	
	private String location;
	
	private String file_format;
	
	private String attachment_type;
	
	private Integer channelID;
	
	private String fileURL;

	private Integer source;
	
	
	
	
	public String getFile_format() {
		return file_format;
	}
	public void setFile_format(String file_format) {
		this.file_format = file_format;
	}
	public String getAttachment_type() {
		return attachment_type;
	}
	public void setAttachment_type(String attachment_type) {
		this.attachment_type = attachment_type;
	}
	public Integer getChannelID() {
		return channelID;
	}
	public void setChannelID(Integer channelID) {
		this.channelID = channelID;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getPerID() {
		return perID;
	}
	public void setPerID(Integer perID) {
		this.perID = perID;
	}
	
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public Integer getBusID() {
		return busID;
	}
	public void setBusID(Integer busID) {
		this.busID = busID;
	}
	public BusInfo getBusInfo() {
		return busInfo;
	}
	public void setBusInfo(BusInfo busInfo) {
		this.busInfo = busInfo;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public Integer getAlarmID() {
		return alarmID;
	}
	public void setAlarmID(Integer alarmID) {
		this.alarmID = alarmID;
	}
	public Integer getAlarmState() {
		return alarmState;
	}
	public void setAlarmState(Integer alarmState) {
		this.alarmState = alarmState;
	}
	public String getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Integer getBusState() {
		return busState;
	}
	public void setBusState(Integer busState) {
		this.busState = busState;
	}
	public String getAlarmSign() {
		return alarmSign;
	}
	public void setAlarmSign(String alarmSign) {
		this.alarmSign = alarmSign;
	}
	public Integer getHandleState() {
		return handleState;
	}
	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}
	public Integer getDriverID() {
		return driverID;
	}
	public void setDriverID(Integer driverID) {
		this.driverID = driverID;
	}
	public DriverInfo getDriverInfo() {
		return driverInfo;
	}
	public void setDriverInfo(DriverInfo driverInfo) {
		this.driverInfo = driverInfo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
