package com.topsun.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ExportModel extends BaseRowModel{
	
	@ExcelProperty(value="车型",index=0)
	private String busType;
	
	@ExcelProperty(value="机构",index=1)
	private String companyName;
	
	@ExcelProperty(value="车牌号",index=2)
	private String plateNO;
	
	@ExcelProperty(value="通讯号",index=3)
	private String simCard;

	@ExcelProperty(value="备注",index=4)
	private String remark;

	private Integer busID;
	

	public Integer getBusID() {
		return busID;
	}

	public void setBusID(Integer busID) {
		this.busID = busID;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	
	
	
	
}
