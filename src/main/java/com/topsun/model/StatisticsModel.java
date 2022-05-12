package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class StatisticsModel extends BaseRowModel{

	//车牌号
	@ExcelProperty(value="车牌号",index=0)
	private String plateNO;
	//司机姓名
	@ExcelProperty(value="司机姓名",index=1)
	private String driverName;
	//司机姓名
	@ExcelProperty(value="司机姓名",index=2)
	private String GPS;
	//司机姓名
	@ExcelProperty(value="司机报警率",index=3)
	private String DSM;
	//司机姓名
	@ExcelProperty(value="盲区报警率",index=4)
	private String BSD;
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getGPS() {
		return GPS;
	}
	public void setGPS(String gPS) {
		GPS = gPS;
	}
	public String getDSM() {
		return DSM;
	}
	public void setDSM(String dSM) {
		DSM = dSM;
	}
	public String getBSD() {
		return BSD;
	}
	public void setBSD(String bSD) {
		BSD = bSD;
	}
	
	
	
	
}
