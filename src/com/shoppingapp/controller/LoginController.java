package com.shoppingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping
	public String startLogin() {
		return "login";
	}
	
	@PostMapping
	public String doLogin(ModelMap model,
						@RequestParam("uname") String uname,
						@RequestParam("pass") String pass) {
		if(uname.equals("Karl") && pass.equals("ShieldBash!"))
			model.addAttribute("message", "Success");
		else
			model.addAttribute("message", "Invalid Credentials");
		return "login";
	}
}
