package com.topsun.entity;

public class Menu {

	private Integer menu_id;
	//菜单名称
	private String menu_name;
	//菜单地址
	private String menu_url;
	//父菜单ID
	private Integer menu_parentID;
	//序号
	private Integer menu_sortID;
	//图标
	private String menu_icon;
	//菜单类型
	private Integer menu_type;
	//状态
	private Integer menu_status;
	
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public Integer getMenu_parentID() {
		return menu_parentID;
	}
	public void setMenu_parentID(Integer menu_parentID) {
		this.menu_parentID = menu_parentID;
	}
	public Integer getMenu_sortID() {
		return menu_sortID;
	}
	public void setMenu_sortID(Integer menu_sortID) {
		this.menu_sortID = menu_sortID;
	}
	public String getMenu_icon() {
		return menu_icon;
	}
	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}
	public Integer getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(Integer menu_type) {
		this.menu_type = menu_type;
	}
	public Integer getMenu_status() {
		return menu_status;
	}
	public void setMenu_status(Integer menu_status) {
		this.menu_status = menu_status;
	}

	
}
