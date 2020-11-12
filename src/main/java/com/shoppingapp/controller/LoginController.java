package com.shoppingapp.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingapp.dao.ItemDao;
import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.Address;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.Item.CONDITION;
import com.shoppingapp.entity.User;
import com.shoppingapp.entity.User.PRIVILAGE;
import com.shoppingapp.service.Service;

@Controller
public class LoginController implements ServletContextAware
{
	private String path;
	
	//welcome page
	@RequestMapping(value="/welcome")
	public ModelAndView WelcomeUser(HttpServletRequest request, HttpServletResponse response, Model mo)
	{	
		mo.addAttribute("products", ItemDao.getItems());
		HttpSession session = request.getSession();
		if(session.getAttribute("cart") == null) {
			Service.establishCart(request, response);
		}
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}
	
	//potentially to be renamed
	//cart
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String CartPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		System.out.println((List<Item>)session.getAttribute("cart"));
		model.addAttribute("products", (List<Item>)session.getAttribute("cart")); //this will be a list
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
			@RequestParam PRIVILAGE privilage, @RequestParam String streetName, @RequestParam String apptNo, @RequestParam String city, @RequestParam String state) {
		Service.addNewUser(name, userName, password, email, phone, enabled, privilage, streetName, apptNo, city, state, path);
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

	@Override
	public void setServletContext(ServletContext servletContext) {
		path = servletContext.getRealPath("/");
	}
	
	//add product page is brought up
	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	public String AddProductPage() {
		return "add-product";
	}
	
	//add product page sends the post request to add new item
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String AddProductPost(@RequestParam String name, @RequestParam String code, @RequestParam String category, @RequestParam CONDITION condition,
								@RequestParam long price, @RequestParam long unitsInStock, @RequestParam String description, @RequestParam String manufacturer) {
		Service.addNewItem(name, code, category, condition, price, unitsInStock, description, manufacturer, path);
		return "add-product";
	}
	
	//customer-list page is brought up
	@RequestMapping(value = "/customer-list", method = RequestMethod.GET)
	public String CustomerListPage(Model mo) {
		mo.addAttribute("customers", UserDao.getUsers());
		return "customer-list";
	}
	
	//product-detail page is brought up
	@RequestMapping(value = "/product-detail/{code}", method = RequestMethod.GET)
	public String ProductDetailPage(Model mo, @PathVariable String code) {
		mo.addAttribute("product",ItemDao.getItem(code));
		return "product-detail";
	}
	
	//when the add to cart button is clicked, should post and save item to cart
	@RequestMapping(value = "/product-detail/{code}", method = RequestMethod.POST)
	public String ProductDetailPost(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable String code) {
		Service.addToCart(request, response, code);
		model.addAttribute("message", "This product has been added to your cart");
		model.addAttribute("product",ItemDao.getItem(code));
		return "product-detail";
	}
	
	//product-list page is brought up
	@RequestMapping(value = "/product-list", method = RequestMethod.GET)
	public String ProductListPage(Model mo) {
		mo.addAttribute("products", ItemDao.getItems());
		return "product-list";
	}
	
	@RequestMapping(value="/admin/customer-management", method=RequestMethod.GET)
	public String getCustomerList(Model mo, @RequestParam(defaultValue="1") int pageNum, @RequestParam(defaultValue="5") int pageSize) {
		User u = new User(-1,"Jon Smith","JonJonJon","12345","jon@jon.jon","5550142",true,User.PRIVILAGE.ADMIN, new Address("","","",""));
		mo.addAttribute("customers", new User[] {
				new User(-1,"Jon Smith","JonJonJon","12345","jon@jon.jon","5550142",true,User.PRIVILAGE.ADMIN, new Address("", "", "", "")),
				new User(-2,"Betty White","Bdubs","hello","b@w.jon","5550143",true,User.PRIVILAGE.STANDARD, new Address("", "", "", "")),
				new User(-3,"Richard Feynmann","rfeyn","r@f.m","physics_roxx","5550144",false,User.PRIVILAGE.STANDARD, new Address("", "", "", ""))
		});
		mo.addAttribute("user",u);
		mo.addAttribute("pageSize", pageSize);
		mo.addAttribute("pageNum", pageNum);
		return "customer-list";
	}

	@RequestMapping(value="/admin/customer-management", method=RequestMethod.POST)
	public String postCustomerList(Model m) {
		m.addAttribute("result", true);
		return getCustomerList(m,1,5);
	}
	
}
