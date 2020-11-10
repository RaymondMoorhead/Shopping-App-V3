package com.shoppingapp.entity;

//import org.springframework.web.multipart.MultipartFile;

public class Item {
	
	public enum CONDITION {
		NEW, USED
	}

	public String name;
	public String code;
	public String category;
	public CONDITION condition;
	public long price;
//	public MultipartFile image;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Item(String name, String code, String category, CONDITION condition, long price) {
		super();
		this.name = name;
		this.code = code;
		this.category = category;
		this.condition = condition;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public CONDITION getCondition() {
		return condition;
	}
	public void setCondition(CONDITION condition) {
		this.condition = condition;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
//	public MultipartFile getImage() {
//		return image;
//	}
//	public void setImage(MultipartFile image) {
//		this.image = image;
//	}
}
