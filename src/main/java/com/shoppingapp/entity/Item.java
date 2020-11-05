package com.shoppingapp.entity;

public class Item {

	public String name;
	public String code;
	public long price;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(String name, String code, long price) {
		super();
		this.name = name;
		this.code = code;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
}
