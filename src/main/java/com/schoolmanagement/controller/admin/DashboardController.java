package com.schoolmanagement.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
  @GetMapping("/dashboard")
  public String dashboard () {
    return "/admin/index";
  }
}