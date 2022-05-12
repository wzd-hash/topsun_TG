package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;


public class BusModel extends BaseRowModel{
	@ExcelProperty(value="序号",index=0)
	private Integer id;
	@ExcelProperty(value="车牌号",index=1)
	private String plateNO;
	@ExcelProperty(value="车队/线路名称",index=2)
	private String roulesName;
	@ExcelProperty(value="车牌颜色",index=3)
	private String plateColor;
	@ExcelProperty(value="车型",index=4)
	private String busType;
	@ExcelProperty(value="首次上牌时间",index=5)
	private String startTime;
	@ExcelProperty(value="司机从业资格证",index=6)
	private String qulificationNO;
	@ExcelProperty(value="终端SIM卡号",index=7)
	private String simCard;
	@ExcelProperty(value="司机姓名",index=8)
	private String driverName;
	@ExcelProperty(value="创建时间",index=9)
	private String createTime;
	
	private String reason;
	
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getSimCard() {
		return simCard;
	}
	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public String getRoulesName() {
		return roulesName;
	}
	public void setRoulesName(String roulesName) {
		this.roulesName = roulesName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getQulificationNO() {
		return qulificationNO;
	}
	public void setQulificationNO(String qulificationNO) {
		this.qulificationNO = qulificationNO;
	}
	
	
}
