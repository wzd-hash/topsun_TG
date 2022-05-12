package com.topsun.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class BusSpeedTable extends BaseRowModel {

    @ExcelProperty(value="企业名称",index=0)
    private String subName;
    @ExcelProperty(value="拥有车辆数",index=1)
    private Integer num;
    @ExcelProperty(value="超速总数",index=2)
    private Integer bnum;
    @ExcelProperty(value="超速车辆数",index=3)
    private Integer cnum;
    @ExcelProperty(value="超速总时长",index=4)
    private String times;
    @ExcelProperty(value="超速总里程",index=5)
    private Double x01;

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getBnum() {
        return bnum;
    }

    public void setBnum(Integer bnum) {
        this.bnum = bnum;
    }

    public Integer getCnum() {
        return cnum;
    }

    public void setCnum(Integer cnum) {
        this.cnum = cnum;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Double getX01() {
        return x01;
    }

    public void setX01(Double x01) {
        this.x01 = x01;
    }
}
