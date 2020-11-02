package com.shoppingapp.entity;

public class User {
	
	public enum PRIVILAGE {
		STANDARD, ADMIN
	}

	private int id;
	private String name;
	private String password;
	private String email;
	private PRIVILAGE privilage;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public PRIVILAGE getPrivilage() {
		return privilage;
	}

	public void setPrivilage(PRIVILAGE privilage) {
		this.privilage = privilage;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", privilage="
				+ privilage.name() + "]";
	}
	
	
}
