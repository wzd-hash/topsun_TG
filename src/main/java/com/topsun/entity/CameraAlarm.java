package com.topsun.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @description 摄像头故障
 * @author: wzd
 * @create: 2022-01-06 11:52
 * @Version 1.0
 **/
public class CameraAlarm extends BaseRowModel {

    private Integer busID;
    //车牌号
    @ExcelProperty(value="车牌号",index=0)
    private String plateNO;
    //线路
    @ExcelProperty(value="车牌号",index=1)
    private String companyName;
    @ExcelProperty(value="报警类型",index=2)
    private String alarmType;
    @ExcelProperty(value="报警时间",index=3)
    private String serverTime;
    @ExcelProperty(value="终端号",index=4)
    private String simCard;

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

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getSimCard() {
        return simCard;
    }

    public void setSimCard(String simCard) {
        this.simCard = simCard;
    }
}
