package com.topsun.entity;

public class Roles {

	private Integer roleID;
	//角色名称
	private String roleName;
	//权限 0-总账号 1-总公司 2-分公司
	private Integer authLeave;
	//所属组织
	private Company company;
	//管理范围
	private String manRange;
	//创建时间
	private String createTime;
	//状态
	private Integer delStatus;
	
	
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getAuthLeave() {
		return authLeave;
	}
	public void setAuthLeave(Integer authLeave) {
		this.authLeave = authLeave;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getManRange() {
		return manRange;
	}
	public void setManRange(String manRange) {
		this.manRange = manRange;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Roles [roleID=" + roleID + ", roleName=" + roleName + ", authLeave=" + authLeave + ", company="
				+ company + ", manRange=" + manRange + ", createTime=" + createTime + ", delStatus=" + delStatus + "]";
	}
	
}
