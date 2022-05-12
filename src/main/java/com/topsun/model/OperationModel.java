package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class OperationModel extends BaseRowModel {
	
	@ExcelProperty(value="机构",index=0)
	private String subName;
	
	@ExcelProperty(value="车牌号",index=1)
	private String plateNO;
	
	@ExcelProperty(value="报警类型",index=2)
	private String alarmType;
	
	@ExcelProperty(value="当前状态",index=3)
	private String alarmstate;
	
	@ExcelProperty(value="报警时间",index=4)
	private String time;
	
	@ExcelProperty(value="最后上线时间",index=5)
	private String server_time;

	
	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getPlateNO() {
		return plateNO;
	}

	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}

	public String getAlarmstate() {
		return alarmstate;
	}

	public void setAlarmstate(String alarmstate) {
		this.alarmstate = alarmstate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getServer_time() {
		return server_time;
	}

	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}
	
	
}
