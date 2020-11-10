package com.shoppingapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingapp.dao.ItemDao;
import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.Item.CONDITION;
import com.shoppingapp.entity.User;
import com.shoppingapp.entity.User.PRIVILAGE;

public class Service {
	
	static List<Item> cart = new ArrayList<Item>();
	
	public static void addNewItem(String name, String code, String category, CONDITION condition, long price) {
		Item item = new Item(name, code, category, condition, price);
		ItemDao.addItem(item);
	}
	
	public static void addNewUser(String name, String userName, String password, String email, String phone, boolean enabled, PRIVILAGE privilage) {
		User user = new User(-1, name, userName, password, email, phone, enabled, privilage);
		if(!UserDao.userExists(userName)) {
			UserDao.addUser(user);
		}
	}
	
	public void addInvoice(String custName, LocalDate date, int invoiceNum, List<Item> items) {
		Invoice invoice = new Invoice(custName, date, invoiceNum, items);
		for(User user : UserDao.getUsers()) {
			if(user.name == custName) {
				UserDao.addInvoice(user, invoice);
			}
		}
	}
	
	//done if the cart is empty/ not established to create empty cart
	public static void establishCart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("cart", cart);
	}
	
	public static void addToCart(HttpServletRequest request, HttpServletResponse response, Item item) {
		HttpSession session = request.getSession();
		cart.add(item);
		session.setAttribute("cart", cart);
	}

}
