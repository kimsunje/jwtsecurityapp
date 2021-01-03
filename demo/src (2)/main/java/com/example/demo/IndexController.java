package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/hello")
	public String Hello() {
		System.out.println("Call Index Controller method Hello()");
		return "index2.html";
	}
	

}
