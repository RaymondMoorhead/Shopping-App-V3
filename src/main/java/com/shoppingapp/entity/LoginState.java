package com.shoppingapp.entity;

public class LoginState {
	public String error;
	public User user;
	
	public LoginState(String error, User user) {
		super();
		this.error = error;
		this.user = user;
	}
}
