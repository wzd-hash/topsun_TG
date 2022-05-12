package com.topsun.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @description 报警
 * @author: wzd
 * @create: 2021-12-09 09:41
 * @Version 1.0
 **/
public class AlarmType extends BaseRowModel {

    private Integer busID;
    @ExcelProperty(value="车牌号",index=0)
    private String plateNO;
    @ExcelProperty(value="所属公司",index=1)
    private String companyName;
    @ExcelProperty(value="终端编号",index=2)
    private String simCard;
    @ExcelProperty(value="报警时间",index=3)
    private String startTime;
    @ExcelProperty(value="开始纬度",index=4)
    private String beginLatitude;
    @ExcelProperty(value="开始经度",index=5)
    private String beginLongitude;
    @ExcelProperty(value="结束时间",index=6)
    private String endTime;
    @ExcelProperty(value="结束纬度",index=7)
    private String endLatitude;
    @ExcelProperty(value="结束经度",index=8)
    private String endLongitude;
    @ExcelProperty(value="报警时长",index=9)
    private String times;
    @ExcelProperty(value="报警类型",index=10)
    private String alarmType;

    public Integer getBusID() {
        return busID;
    }

    public void setBusID(Integer busID) {
        this.busID = busID;
    }

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

    public String getSimCard() {
        return simCard;
    }

    public void setSimCard(String simCard) {
        this.simCard = simCard;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getBeginLatitude() {
        return beginLatitude;
    }

    public void setBeginLatitude(String beginLatitude) {
        this.beginLatitude = beginLatitude;
    }

    public String getBeginLongitude() {
        return beginLongitude;
    }

    public void setBeginLongitude(String beginLongitude) {
        this.beginLongitude = beginLongitude;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(String endLatitude) {
        this.endLatitude = endLatitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }
}
