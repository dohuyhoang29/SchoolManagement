package com.schoolmanagement.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeUserController {

	@GetMapping("/home")
	public String HomeUser(){
		
		return "/user/home";
	}
}
