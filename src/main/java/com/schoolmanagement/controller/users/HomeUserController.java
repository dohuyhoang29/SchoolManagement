package com.schoolmanagement.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeUserController {

	@GetMapping("/" )
	public String HomeUser(){
		
		return "/user/index";
	}
	
	
}
