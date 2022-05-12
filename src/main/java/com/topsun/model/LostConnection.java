package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class LostConnection extends BaseRowModel{

	
	@ExcelProperty(value="序号",index=0)
	private String id;
	@ExcelProperty(value="车牌",index=0)
	private String plateNO;
	@ExcelProperty(value="所属机构",index=1)
	private String companyName;
	@ExcelProperty(value="离线时间",index=2)
	private String outtime;
	@ExcelProperty(value="上线时间",index=2)
	private String intime;
	@ExcelProperty(value="离线时长",index=3)
	private String leadTime;
	
	
	
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOuttime() {
		return outtime;
	}
	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	
	
	
}
