package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CrudController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
}
