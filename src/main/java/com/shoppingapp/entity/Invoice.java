package com.shoppingapp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
	
	public String custName;
	public LocalDate date;
	public int invoiceNum;
	public List<Item> items;
	
	private static int lastInvoice = -1;

	public Invoice(String custName, LocalDate date, int invoiceNum, List<Item> items) {
		super();
		this.custName = custName;
		this.date = date;
		this.invoiceNum = invoiceNum;
		this.items = items;
	}

	public Invoice(String custName) {
		super();
		this.custName = custName;
		this.date = LocalDate.now();
		this.invoiceNum = ++lastInvoice;
		this.items = new ArrayList<Item>();
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getInvoiceNum() {
		return invoiceNum;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
