package com.example.demo.app.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/")
	public String Index() {
		System.out.println("Call Index Controller");
		return "index.html";
	}
	
	
}
