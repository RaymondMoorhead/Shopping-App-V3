package com.shoppingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingapp.entity.User;

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
		
		User u = new User(-1,"Jon Smith","JonJonJon","12345","jon@jon.jon","5550142",true,User.PRIVILAGE.ADMIN);
		mo.addAttribute("user",u);
		
		return "product-list";
	}
	
	@RequestMapping(value="/admin/customer-management", method=RequestMethod.GET)
	public String getCustomerList(Model mo, @RequestParam(defaultValue="1") int pageNum, @RequestParam(defaultValue="5") int pageSize) {
		User u = new User(-1,"Jon Smith","JonJonJon","12345","jon@jon.jon","5550142",true,User.PRIVILAGE.ADMIN);
		mo.addAttribute("customers", new User[] {
				u,
				new User(-2,"Betty White","Bdubs","hello","b@w.jon","5550143",true,User.PRIVILAGE.STANDARD),
				new User(-3,"Richard Feynmann","rfeyn","r@f.m","physics_roxx","5550144",false,User.PRIVILAGE.STANDARD)
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
	
	@RequestMapping(value="/temp/confirm-order", method=RequestMethod.POST)
	public String getConfirmOrder(Model mo) {
		MiniProduct[] m = new MiniProduct[] {
				new MiniProduct("Black Lotus","Trading Card","mint",40000),
				new MiniProduct("Superman Issue #1","Comic book", "used", 50000),
				new MiniProduct("Inverted Jenny", "Stamp", "ripped", 100000000)
			};
		mo.addAttribute("products",m);
		return "confirm-order";
	}
	
	
	@RequestMapping(value="/temp/register", method=RequestMethod.GET)
	public String getRegister() {
		return "register";
	}
	
	@RequestMapping(value="/temp/product/{id}", method=RequestMethod.GET)
	public String getProductDetail(Model model, @PathVariable int id) {
		model.addAttribute("product", new MiniProduct("Black Lotus","Trading Card","mint",40000));
		return "product-detail";
	}
	
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.POST)
	public String postAddToCart(Model m, @PathVariable int id) {
		m.addAttribute("product", new MiniProduct("Black Lotus","Trading Card","mint",40000));
		m.addAttribute("message", "This product has been added to your cart");
		return "product-detail";
	}
	
	@RequestMapping(value="/temp/cart", method=RequestMethod.GET)
	public String getCart(Model model) {
		MiniProduct[] m = new MiniProduct[] {
				new MiniProduct("Black Lotus","Trading Card","mint",40000),
				new MiniProduct("Superman Issue #1","Comic book", "used", 50000),
				new MiniProduct("Inverted Jenny", "Stamp", "ripped", 100000000)
			};
		model.addAttribute("products", m);
		return "cart";
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
