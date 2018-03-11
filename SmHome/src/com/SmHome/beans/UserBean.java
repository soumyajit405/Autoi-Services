package com.SmHome.beans;

import java.util.Date;

public class UserBean {

	private String id;
	private String homeId;
	private String name;
	private String phone;
	private String password;
	private String email;
	//private Date DOB;
	private String status;
	private String accessLevel;
	private String extraInfo1;
	private String extraInfo2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHomeId() {
		return homeId;
	}
	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}*/
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
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
	
}
