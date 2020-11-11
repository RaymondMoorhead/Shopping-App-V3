package com.shoppingapp.controller;

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

import com.shoppingapp.dao.ItemDao;
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
		session.getAttribute("cart"); //this will be a list
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
	
	//add product page is brought up
	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	public String AddProductPage() {
		return "add-product";
	}
	
	//add product page sends the post request to add new item
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String AddProductPost(@RequestParam String name, @RequestParam String code, @RequestParam String category, @RequestParam CONDITION condition, @RequestParam long price) {
		Service.addNewItem(name, code, category, condition, price);
		return "add-product";
	}
	
	//customer-list page is brought up
	@RequestMapping(value = "/customer-list", method = RequestMethod.GET)
	public String CustomerListPage(Model mo) {
		mo.addAttribute("customers", UserDao.getUsers());
		return "customer-list";
	}
	
	//product-detail page is brought up
	@RequestMapping(value = "/product-detail", method = RequestMethod.GET)
	public String ProductDetailPage(Model mo, String code) {
		mo.addAttribute("product",ItemDao.getItem(code));
		return "product-detail";
	}
	
	//when the add to cart button is clicked, should post and save item to cart
	@RequestMapping(value = "/product-detail", method = RequestMethod.POST)
	public String ProductDetailPost(HttpServletRequest request, HttpServletResponse response, Item item) {
		Service.addToCart(request, response, item);
		return "product-detail";
	}
	
	//product-list page is brought up
	@RequestMapping(value = "/product-list", method = RequestMethod.GET)
	public String ProductListPage(Model mo) {
		mo.addAttribute("products", ItemDao.getItems());
		return "product-list";
	}
	
}
