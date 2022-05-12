package com.topsun.entity;

import com.alibaba.excel.annotation.ExcelProperty;

public class speedTable {
    @ExcelProperty(value="车牌号",index=0)
    private String plateNO;
    @ExcelProperty(value="超速平均时速",index=1)
    private String speed;
    @ExcelProperty(value="超速时长占比",index=2)
    private String timefen;
    @ExcelProperty(value="超速里程占比",index=3)
    private String x01fen;

    public String getPlateNO() {
        return plateNO;
    }

    public void setPlateNO(String plateNO) {
        this.plateNO = plateNO;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
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
}
