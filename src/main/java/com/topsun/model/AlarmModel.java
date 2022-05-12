package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class AlarmModel extends BaseRowModel{

	//车牌号
	@ExcelProperty(value="车牌号",index=0)
	private String plateNO;
	//终端号
	@ExcelProperty(value="SIM卡号",index=1)
	private String simCard;
	//所属组织
	@ExcelProperty(value="所属组织",index=2)
	private String companyName;
	//司机姓名
	@ExcelProperty(value="司机姓名",index=3)
	private String driverName;
	//司机从业资格证
	@ExcelProperty(value="从业资格证",index=4)
	private String qulificationNO;
	//报警时间
	@ExcelProperty(value="报警时间",index=5)
	private String alarmTime;
	//报警类型
	@ExcelProperty(value="报警类型",index=6)
	private String alarmType;
	//报警级别
	@ExcelProperty(value="报警级别",index=7)
	private String alarmLevel;
	//报警位置
	@ExcelProperty(value="报警位置",index=8)
	private String position;
	//车速
	@ExcelProperty(value="车速",index=9)
	private String speed;
	//处理状态
	@ExcelProperty(value="处理状态",index=10)
	private String handleState;
	//纬度
	private String latitude;
	//经度
	private String longitude;
	
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
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public String getSimCard() {
		return simCard;
	}
	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getQulificationNO() {
		return qulificationNO;
	}
	public void setQulificationNO(String qulificationNO) {
		this.qulificationNO = qulificationNO;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getHandleState() {
		return handleState;
	}
	public void setHandleState(String handleState) {
		this.handleState = handleState;
	}
	
}
