package com.topsun.entity;

/**
 * @description 七天未上线（外借）
 * @author: wzd
 * @create: 2022-02-11 09:20
 * @Version 1.0
 **/
public class OfflineBus {

    private String carNo;

    private String fleetName;

    private String deviceNo;

    private String lastUploadTime;

    private String carLabel;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getLastUploadTime() {
        return lastUploadTime;
    }

    public void setLastUploadTime(String lastUploadTime) {
        this.lastUploadTime = lastUploadTime;
    }

    public String getCarLabel() {
        return carLabel;
    }

    public void setCarLabel(String carLabel) {
        this.carLabel = carLabel;
    }
}
