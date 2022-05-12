package com.topsun.entity;
/**
 * 站点信息
 * @author Admin
 *
 */
public class Stations {

	private Integer id;
	//线路ID
	private Integer roulesID;
	//线路名称
	private String roulesName;
	//站点ID
	private Integer stationID;
	//站点名称
	private String stationName;
	//上下行
	private Integer direction;
	//经度
	private Float longitude;
	//纬度
	private Float latitude;
	//站点序号
	private Integer sortID;
	
	
	public Integer getSortID() {
		return sortID;
	}


	public void setSortID(Integer sortID) {
		this.sortID = sortID;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getRoulesID() {
		return roulesID;
	}


	public void setRoulesID(Integer roulesID) {
		this.roulesID = roulesID;
	}


	public String getRoulesName() {
		return roulesName;
	}


	public void setRoulesName(String roulesName) {
		this.roulesName = roulesName;
	}


	public Integer getStationID() {
		return stationID;
	}


	public void setStationID(Integer stationID) {
		this.stationID = stationID;
	}


	public String getStationName() {
		return stationName;
	}


	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


	public Integer getDirection() {
		return direction;
	}


	public void setDirection(Integer direction) {
		this.direction = direction;
	}


	public Float getLongitude() {
		return longitude;
	}


	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}


	public Float getLatitude() {
		return latitude;
	}


	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}


	public Stations(Integer id, Integer roulesID, String roulesName, Integer stationID, String stationName,
			Integer direction, Float longitude, Float latitude) {
		super();
		this.id = id;
		this.roulesID = roulesID;
		this.roulesName = roulesName;
		this.stationID = stationID;
		this.stationName = stationName;
		this.direction = direction;
		this.longitude = longitude;
		this.latitude = latitude;
	}


	public Stations() {
		super();
	}
	
	
	
	
}
