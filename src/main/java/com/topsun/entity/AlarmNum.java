package com.topsun.entity;

public class AlarmNum {
	private Integer perID;
	private String plateNO;
	private Integer busID;
	private String alarmName;
	private Integer number;
	private String time;
	//超速报警
	private Integer alarm_1;
	//GNSS模块发生故障
	private Integer alarm_4;
	//GNSS天线未接或被剪断
	private Integer alarm_5;
	//GNSS天线短路
	private Integer alarm_6;
	//终端主电源欠压
	private Integer alarm_7;
	//终端主电源掉电
	private Integer alarm_8;
	//终端LCD或显示器故障
	private Integer alarm_9;
	//摄像头故障
	private Integer alarm_11;
	//当天累计驾驶超时
	private Integer alarm_18;
	//侧翻预警
	private Integer alarm_30;
	//前向碰撞
	private Integer x641;
	//车道偏离
	private Integer x642;
	//车距过近
	private Integer x643;
	//行人碰撞
	private Integer x644;
	//频繁变道
	private Integer x645;
	//道路标识超限报警
	private Integer x646;
	//障碍物报警
	private Integer x647;
	//疲劳驾驶报警
	private Integer x651;
	//接打电话报警
	private Integer x652;
	//抽烟报警
	private Integer x653;
	//分神驾驶报警
	private Integer x654;
	//驾驶员异常报警
	private Integer x655;
	//打哈欠
	private Integer x656;
	//离岗
	private Integer x657;
	//驾驶员变更
	private Integer x6511;
	//后方接近报警
	private Integer x671;
	//左侧后方接近报警
	private Integer x672;
	//右侧后方接近报警
	private Integer x673;
	
	
	
	public Integer getBusID() {
		return busID;
	}
	public void setBusID(Integer busID) {
		this.busID = busID;
	}
	public Integer getX656() {
		return x656;
	}
	public void setX656(Integer x656) {
		this.x656 = x656;
	}
	public Integer getX657() {
		return x657;
	}
	public void setX657(Integer x657) {
		this.x657 = x657;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public Integer getAlarm_1() {
		return alarm_1;
	}
	public void setAlarm_1(Integer alarm_1) {
		this.alarm_1 = alarm_1;
	}
	public Integer getAlarm_4() {
		return alarm_4;
	}
	public void setAlarm_4(Integer alarm_4) {
		this.alarm_4 = alarm_4;
	}
	public Integer getAlarm_5() {
		return alarm_5;
	}
	public void setAlarm_5(Integer alarm_5) {
		this.alarm_5 = alarm_5;
	}
	public Integer getAlarm_6() {
		return alarm_6;
	}
	public void setAlarm_6(Integer alarm_6) {
		this.alarm_6 = alarm_6;
	}
	public Integer getAlarm_7() {
		return alarm_7;
	}
	public void setAlarm_7(Integer alarm_7) {
		this.alarm_7 = alarm_7;
	}
	public Integer getAlarm_8() {
		return alarm_8;
	}
	public void setAlarm_8(Integer alarm_8) {
		this.alarm_8 = alarm_8;
	}
	public Integer getAlarm_9() {
		return alarm_9;
	}
	public void setAlarm_9(Integer alarm_9) {
		this.alarm_9 = alarm_9;
	}
	public Integer getAlarm_11() {
		return alarm_11;
	}
	public void setAlarm_11(Integer alarm_11) {
		this.alarm_11 = alarm_11;
	}
	public Integer getAlarm_18() {
		return alarm_18;
	}
	public void setAlarm_18(Integer alarm_18) {
		this.alarm_18 = alarm_18;
	}
	public Integer getAlarm_30() {
		return alarm_30;
	}
	public void setAlarm_30(Integer alarm_30) {
		this.alarm_30 = alarm_30;
	}
	public Integer getX641() {
		return x641;
	}
	public void setX641(Integer x641) {
		this.x641 = x641;
	}
	public Integer getX642() {
		return x642;
	}
	public void setX642(Integer x642) {
		this.x642 = x642;
	}
	public Integer getX643() {
		return x643;
	}
	public void setX643(Integer x643) {
		this.x643 = x643;
	}
	public Integer getX644() {
		return x644;
	}
	public void setX644(Integer x644) {
		this.x644 = x644;
	}
	public Integer getX645() {
		return x645;
	}
	public void setX645(Integer x645) {
		this.x645 = x645;
	}
	public Integer getX646() {
		return x646;
	}
	public void setX646(Integer x646) {
		this.x646 = x646;
	}
	public Integer getX647() {
		return x647;
	}
	public void setX647(Integer x647) {
		this.x647 = x647;
	}
	public Integer getX651() {
		return x651;
	}
	public void setX651(Integer x651) {
		this.x651 = x651;
	}
	public Integer getX652() {
		return x652;
	}
	public void setX652(Integer x652) {
		this.x652 = x652;
	}
	public Integer getX653() {
		return x653;
	}
	public void setX653(Integer x653) {
		this.x653 = x653;
	}
	public Integer getX654() {
		return x654;
	}
	public void setX654(Integer x654) {
		this.x654 = x654;
	}
	public Integer getX655() {
		return x655;
	}
	public void setX655(Integer x655) {
		this.x655 = x655;
	}
	public Integer getX6511() {
		return x6511;
	}
	public void setX6511(Integer x6511) {
		this.x6511 = x6511;
	}
	public Integer getX671() {
		return x671;
	}
	public void setX671(Integer x671) {
		this.x671 = x671;
	}
	public Integer getX672() {
		return x672;
	}
	public void setX672(Integer x672) {
		this.x672 = x672;
	}
	public Integer getX673() {
		return x673;
	}
	public void setX673(Integer x673) {
		this.x673 = x673;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAlarmName() {
		return alarmName;
	}
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getPerID() {
		return perID;
	}
	public void setPerID(Integer perID) {
		this.perID = perID;
	}
	
}
