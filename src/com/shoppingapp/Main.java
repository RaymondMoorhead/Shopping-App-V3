package com.shoppingapp;

import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;

import com.shoppingapp.entity.User;  

public class Main {
	public static void main(String[] args) {  
	    Resource resource=new ClassPathResource("applicationContext.xml");  
	    BeanFactory factory=new XmlBeanFactory(resource);  
	      
	    User user=(User)factory.getBean("userbean");  
	    user.displayInfo();  
	}  
}
