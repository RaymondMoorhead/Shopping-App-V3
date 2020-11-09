package com.shoppingapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.shoppingapp.dao.ItemDao;
import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.User;
import com.shoppingapp.entity.User.PRIVILAGE;

public class Service {
	
	public static Authentication findPrincipal() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("Auth is: " + auth.getPrincipal());
		return auth;
	}
	
	public void addNewItem(String code, String name, long price) {
		Item item = new Item(code, name, price);
		ItemDao.addItem(item);
	}
	
	public void addNewUser(int id, String name, String password, String email, PRIVILAGE privilage) {
		User user = new User(id, name, password, email, privilage);
		UserDao.addUser(user);
	}
	
	//invoice additions to be added
	public void addInvoice(String custName, LocalDate date, int invoiceNum, List<Item> items) {
		Invoice invoice = new Invoice(custName, date, invoiceNum, items);
		//ItemDao.AddTransaction(Invoice invoice);
	}

}
