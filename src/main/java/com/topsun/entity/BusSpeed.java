package com.topsun.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class BusSpeed extends BaseRowModel {

    private Integer id;

    private String busID;

    @ExcelProperty(value="车牌号",index=0)
    private String plateNO;

    //纬度
    @ExcelProperty(value="纬度",index=1)
    private String latitude;
    //经度
    @ExcelProperty(value="经度",index=2)
    private String longitude;
    //速度
    @ExcelProperty(value="速度",index=3)
    private String speed;

    @ExcelProperty(value="开始gps时间",index=4)
    private String startgps_time;

    @ExcelProperty(value="结束gps时间",index=5)
    private String 	endgps_time;

    @ExcelProperty(value="持续时间",index=6)
    private String times;

    @ExcelProperty(value="报警位置",index=7)
    private String location;

    private String x01;

    private String gpsTime;

    private String timefen;

    private String x01fen;

    private String x01s;

    public String getPlateNO() {
        return plateNO;
    }

    public void setPlateNO(String plateNO) {
        this.plateNO = plateNO;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getStartgps_time() {
        return startgps_time;
    }

    public void setStartgps_time(String startgps_time) {
        this.startgps_time = startgps_time;
    }

    public String getEndgps_time() {
        return endgps_time;
    }

    public void setEndgps_time(String endgps_time) {
        this.endgps_time = endgps_time;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getX01() {
        return x01;
    }

    public void setX01(String x01) {
        this.x01 = x01;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String getTimefen() {
        return timefen;
    }

    public void setTimefen(String timefen) {
        this.timefen = timefen;
    }

    public String getX01fen() {
        return x01fen;
    }

    public void setX01fen(String x01fen) {
        this.x01fen = x01fen;
    }

    public String getX01s() {
        return x01s;
    }

    public void setX01s(String x01s) {
        this.x01s = x01s;
    }

    @Override
    public String toString() {
        return "BusSpeed{" +
                "id=" + id +
                ", busID='" + busID + '\'' +
                ", plateNO='" + plateNO + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", speed='" + speed + '\'' +
                ", startgps_time='" + startgps_time + '\'' +
                ", endgps_time='" + endgps_time + '\'' +
                ", times='" + times + '\'' +
                ", location='" + location + '\'' +
                ", x01='" + x01 + '\'' +
                ", gpsTime='" + gpsTime + '\'' +
                ", timefen='" + timefen + '\'' +
                ", x01fen='" + x01fen + '\'' +
                ", x01s='" + x01s + '\'' +
                '}';
    }
}
