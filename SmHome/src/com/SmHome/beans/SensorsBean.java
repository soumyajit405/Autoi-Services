package com.SmHome.beans;

public class SensorsBean {

	private int sensorId;
	private String homeId;
	private String sensorName;
	private int sensorValue;
	

	private String extraInfo1;
	private String extraInfo2;
	
	public String getHomeId() {
		return homeId;
	}
	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}

	public String getExtraInfo1() {
		return extraInfo1;
	}
	public void setExtraInfo1(String extraInfo1) {
		this.extraInfo1 = extraInfo1;
	}
	public String getExtraInfo2() {
		return extraInfo2;
	}
	public void setExtraInfo2(String extraInfo2) {
		this.extraInfo2 = extraInfo2;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public int getSensorValue() {
		return sensorValue;
	}
	public void setSensorValue(int sensorValue) {
		this.sensorValue = sensorValue;
	}
}
