package com.shoppingapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingapp.service.Service;

@Controller
public class LoginController
{
	@RequestMapping(value="/welcome")
	public ModelAndView welcomeUser(HttpServletRequest request, HttpServletResponse response)
	{
		//both of these strings are null
		String userName = request.getParameter("username");
		String userPass = request.getParameter("userpass");
		System.out.println("username is: " + userName + "and userPass is: " + userPass);
		try {
			Service.doPost(request, response, userName, userPass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("welcome");
	}
		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
		
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
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
	
	@RequestMapping(value="/product-list", method = RequestMethod.GET)
	public String productList(Model mo) {
		MiniProduct[] m = new MiniProduct[] {
				new MiniProduct("Black Lotus","Trading Card","mint",40000),
				new MiniProduct("Superman Issue #1","Comic book", "used", 50000),
				new MiniProduct("Inverted Jenny", "Stamp", "ripped", 100000000)
			};
		mo.addAttribute("products", m);
		
		return "product-list";
	}
}
