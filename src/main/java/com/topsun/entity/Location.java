package com.topsun.entity;

/**
 * @description 区域实体类
 * @author: wzd
 * @create: 2021-11-25 16:07
 * @Version 1.0
 **/
public class Location {


    private Integer ID;
    //区域名称
    private String areaName;
    //区域类型  1-土场  2-工地  3-停车场
    private Integer areaType;
    //总土方量
    private String earthwork;
    //实际土方量
    private String fullSize;
    //区域经纬度
    private String mapCenter;
    //区域围栏经纬度
    private String polygon;
    //土场公示牌图片
    private String soilFieldBillboard;
    //使用类型 使用类型 1-渣土车  2-五小工程车
    private Integer useType;
    //公司名称
    private String companyName;
    //公司名称
    private Integer companyID;
    //创建时间
    private String createTime;
    //开始使用时间
    private String beginTime;
    //结束使用时间
    private String endTime;
    //冻结截止时间
    private String frozenEndTime;
    //备注
    private String remark;
    //使用状态 0-正在使用 1-弃用
    private Integer delStatus;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public String getEarthwork() {
        return earthwork;
    }

    public void setEarthwork(String earthwork) {
        this.earthwork = earthwork;
    }

    public String getMapCenter() {
        return mapCenter;
    }

    public void setMapCenter(String mapCenter) {
        this.mapCenter = mapCenter;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getSoilFieldBillboard() {
        return soilFieldBillboard;
    }

    public void setSoilFieldBillboard(String soilFieldBillboard) {
        this.soilFieldBillboard = soilFieldBillboard;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public String getFullSize() {
        return fullSize;
    }

    public void setFullSize(String fullSize) {
        this.fullSize = fullSize;
    }

    public String getFrozenEndTime() {
        return frozenEndTime;
    }

    public void setFrozenEndTime(String frozenEndTime) {
        this.frozenEndTime = frozenEndTime;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }
}
