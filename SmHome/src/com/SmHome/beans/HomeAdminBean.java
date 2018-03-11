package com.SmHome.beans;

public class HomeAdminBean {

	private String homeId;
	private String email;
	private String adminFullName;
	private String phone;
	private String password;

	private String extraInfo1;
	private String extraInfo2;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomeId() {
		return homeId;
	}
	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}
	public String getAdminFullName() {
		return adminFullName;
	}
	public void setAdminFullName(String adminFullName) {
		this.adminFullName = adminFullName;
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
