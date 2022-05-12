package com.topsun.entity;

import java.util.List;

public class Queue {

	private String queueName;
	private List<SubCompany> subCompanyList;
	
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public List<SubCompany> getSubCompanyList() {
		return subCompanyList;
	}
	public void setSubCompanyList(List<SubCompany> subCompanyList) {
		this.subCompanyList = subCompanyList;
	}
}
