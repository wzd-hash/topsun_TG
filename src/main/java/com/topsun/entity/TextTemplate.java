package com.topsun.entity;

public class TextTemplate {

	private Integer textID;
	
	private Integer headID;
	
	private Integer subID;
	
	private String content;

	public TextTemplate() {
		super();
	}

	public TextTemplate(Integer textID, Integer headID, Integer subID, String content) {
		super();
		this.textID = textID;
		this.headID = headID;
		this.subID = subID;
		this.content = content;
	}

	public Integer getTextID() {
		return textID;
	}

	public void setTextID(Integer textID) {
		this.textID = textID;
	}

	public Integer getHeadID() {
		return headID;
	}

	public void setHeadID(Integer headID) {
		this.headID = headID;
	}

	public Integer getSubID() {
		return subID;
	}

	public void setSubID(Integer subID) {
		this.subID = subID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
