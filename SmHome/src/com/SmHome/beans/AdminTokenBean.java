package com.SmHome.beans;

import java.sql.Timestamp;

public class AdminTokenBean {

	private int id;
	private String homeId;
	private String token;
	private Timestamp generatedTime;
	private String tokenStatus;
	public String getHomeId() {
		return homeId;
	}
	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getGeneratedTime() {
		return generatedTime;
	}
	public void setGeneratedTime(Timestamp generatedTime) {
		this.generatedTime = generatedTime;
	}
	public String getTokenStatus() {
		return tokenStatus;
	}
	public void setTokenStatus(String tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
