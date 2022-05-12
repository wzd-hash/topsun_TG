package com.topsun.entity;

public class MediaInfo {

	private Integer busID;
	
	private String plateNO;
	
	private String file_format;
	
	private String attachment_type;
	
	private Integer channelID;
	
	private String fileTime;
	
	private String fileURL;

	private Integer source;
	
	private Integer alarmType;
	
	
	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
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

	public String getFileTime() {
		return fileTime;
	}

	public void setFileTime(String fileTime) {
		this.fileTime = fileTime;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	
	
}
