package com.shoppingapp.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	public enum PRIVILAGE {
		STANDARD, ADMIN
	}

	public int id;
	public String name;
	public String username;
	public String password;
	public String email;
	public String phone;
	public boolean enabled;
	public PRIVILAGE privilage;
	public List<Invoice> purchases;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String username, String password, String email, String phone, boolean enabled, PRIVILAGE privilage) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.privilage = privilage;
		this.purchases = new ArrayList<Invoice>();
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public PRIVILAGE getPrivilage() {
		return privilage;
	}

	public void setPrivilage(PRIVILAGE privilage) {
		this.privilage = privilage;
	}

	public List<Invoice> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Invoice> purchases) {
		this.purchases = purchases;
	}
	
	public void addPurchase(Invoice purchase) {
		purchases.add(purchase);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", privilage="
				+ privilage.name() + "]";
	}
	
	
}
