package com.shoppingapp.entity;

public class Address {
	public String streetName;
	public String apptNo;
	public String city;
	public String state;
	
	
	public Address(String streetName, String apptNo, String city, String state) {
		super();
		this.streetName = streetName;
		this.apptNo = apptNo;
		this.city = city;
		this.state = state;
	}
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getApptNo() {
		return apptNo;
	}
	public void setApptNo(String apptNo) {
		this.apptNo = apptNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
