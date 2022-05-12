package com.topsun.entity;

public class AlarmStatistics {

	private String plateNO;
	private String driverName;
	private String simCard;
	private Double GPS;
	private Double DSM;
	private Double BSD;
	private Double MOVE;
	private String busID;
	
	public Double getMOVE() {
		return MOVE;
	}
	public void setMOVE(Double mOVE) {
		MOVE = mOVE;
	}
	public String getBusID() {
		return busID;
	}
	public void setBusID(String busID) {
		this.busID = busID;
	}
	public String getPlateNO() {
		return plateNO;
	}
	public void setPlateNO(String plateNO) {
		this.plateNO = plateNO;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getSimCard() {
		return simCard;
	}
	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}
	public Double getGPS() {
		return GPS;
	}
	public void setGPS(Double gPS) {
		GPS = gPS;
	}
	public Double getDSM() {
		return DSM;
	}
	public void setDSM(Double dSM) {
		DSM = dSM;
	}
	public Double getBSD() {
		return BSD;
	}
	public void setBSD(Double bSD) {
		BSD = bSD;
	}
	
	
	
	
}
