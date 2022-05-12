package com.topsun.entity;

/**
 * @description 响应内容
 * @author: wzd
 * @create: 2021-12-30 15:51
 * @Version 1.0
 **/
public class ResponseMsg {

    private String channelNO;
    private String size;
    private String startTime;
    private String endTime;
    private String resourceType;

    public String getChannelNO() {
        return channelNO;
    }

    public void setChannelNO(String channelNO) {
        this.channelNO = channelNO;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
