package com.shoppingapp.service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.User;

public class Service extends HttpServlet{
	
	public static void doPost(HttpServletRequest request, HttpServletResponse response, String userName, String userPass) throws IOException {
        
        HttpSession session = request.getSession();
        
        SetCurUser(session, userName, userPass);
        
        System.out.println("in doPost, session holds: " + session.getAttribute("curUser"));
	}
	
	private static final long serialVersionUID = 1L;

	/*
	 * //@ModelAttribute("curUser") public static User GetCurUser(HttpSession
	 * session) { return (User) session.getAttribute("curUser"); }
	 */
	
	public static void SetCurUser(HttpSession session, String userName, String userPass) {
		//System.out.println("username is: " + userName + "and userPass is: " + userPass);
		User user = UserDao.getUser(userName, userPass);
		System.out.println("userName is: " + userName);
		System.out.println("userpass is: " + userPass);
		System.out.println("user is: " + (user==null ? "null" : user.toString()));
		if(user != null) {
			session.setAttribute("curUser", user);
		}
		
	}

}
