package com.shoppingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TempController {
	@RequestMapping(value="/temp/add-product", method=RequestMethod.GET)
	public String getAddProduct(Model mo) {
		mo.addAttribute("product", new MiniProduct("","","",0));
		return "add-product";
	}
	@RequestMapping(value="/temp/edit-product", method=RequestMethod.GET)
	public String getAddProduct2(Model mo) {
		mo.addAttribute("product", new MiniProduct("Black Lotus","Trading Card","mint",40000));
		return "add-product";
	}
	
	
	@RequestMapping(value="/temp/product-list", method = RequestMethod.GET)
	public String productList(Model mo) {
		MiniProduct[] m = new MiniProduct[] {
				new MiniProduct("Black Lotus","Trading Card","mint",40000),
				new MiniProduct("Superman Issue #1","Comic book", "used", 50000),
				new MiniProduct("Inverted Jenny", "Stamp", "ripped", 100000000)
			};
		mo.addAttribute("products", m);
		
		return "product-list";
	}
	
	
	public class MiniProduct{
		public String name, category, condition; public int price;
		MiniProduct(String n,String cat,String co,int p){
			name=n;category=cat;condition=co;price=p;
		}
		public String getName() {return name;}
		public String getCategory() {return category;}
		public String getCondition() {return condition;}
		public int getPrice() {return price;}
		public void setName(String n) {name=n;}
		public String toString() {
			return name;
		}
	} 
	
}
