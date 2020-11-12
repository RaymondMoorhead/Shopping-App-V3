package com.shoppingapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ServletContextAware;

import com.shoppingapp.dao.ItemDao;
import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.Address;
import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.Item.CONDITION;
import com.shoppingapp.entity.User;
import com.shoppingapp.entity.User.PRIVILAGE;

public class Service{
	
	static List<Item> cart = new ArrayList<Item>();
	
	public static void addNewItem(String name, String code, String category, CONDITION condition, long price, long unitsInStock, String description, String manufacturer, String path) {
		Item item = new Item(name, code, category, condition, price, unitsInStock, description, manufacturer);
		ItemDao.addItem(item, path);
	}
	
	public static void addNewUser(String name, String userName, String password, String email, String phone, boolean enabled, PRIVILAGE privilage, 
									String streetName, String apptNo, String city, String state, String path) {
		User user = new User(-1, name, userName, password, email, phone, enabled, privilage, new Address(streetName, apptNo, city, state));
		if(!UserDao.userExists(userName)) {
			UserDao.addUser(user, path);
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
	
	public static void addToCart(HttpServletRequest request, HttpServletResponse response, String code) {
		HttpSession session = request.getSession();
		Item item = ItemDao.getItem(code);
		cart.add(item);
		session.setAttribute("cart", cart);
	}

}
