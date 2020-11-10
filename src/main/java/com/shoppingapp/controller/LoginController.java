package com.shoppingapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.Item.CONDITION;
import com.shoppingapp.entity.User.PRIVILAGE;
import com.shoppingapp.service.Service;

@Controller
public class LoginController
{
	//welcome page
	@RequestMapping(value="/welcome")
	public ModelAndView WelcomeUser(HttpServletRequest request, HttpServletResponse response)
	{	
		HttpSession session = request.getSession();
		if(session.getAttribute("cart") == null) {
			Service.establishCart(request, response);
		}
		return new ModelAndView("welcome");
	}
	
	//potentially to be renamed
	//cart
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String CartPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.getAttribute("cart");
		return "cart";
	}
		
	//login page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginPage(ModelMap model) {
		return "login";
	}
	
	//register page is brought up
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String RegisterPage() {
		return "register";
	}
	
	//registers new user
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterPost(@RequestParam String name, @RequestParam String userName, @RequestParam String password, @RequestParam String email, @RequestParam String phone, @RequestParam boolean enabled,
			@RequestParam PRIVILAGE privilage) {
		Service.addNewUser(name, userName, password, email, phone, enabled, privilage);
		return "register";
	}
		
	//logs out user and redirects to login page
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String LogoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
	
	//potentially to be renamed
	//brings up list of all customers
	@RequestMapping(value="/customer-list", method = RequestMethod.GET)
	public String AllCustomersPage() {
		UserDao.getUsers();
		return "customer-list";
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
	
	//potentially to be renamed
	//product-add page is brought up
	@RequestMapping(value = "/product-add", method = RequestMethod.GET)
	public String ProductAddPage() {
		return "product-add";
	}
	
	//adds new product
	//redirects to product-list.jsp once POST is complete
	@RequestMapping(value = "/product-add", method = RequestMethod.POST)
	public String ProductAddPost(@RequestParam String name, @RequestParam String code, @RequestParam String category, @RequestParam CONDITION condition, @RequestParam long price) {
		Service.addNewItem(name, code, category, condition, price);
		return "redirect:/product-list";
	}
	
	//brings up Product Detail Page
	@RequestMapping(value = "/product-detail", method = RequestMethod.GET)
	public String ProductDetailPage() {
		return "product-detail";
	}
}
