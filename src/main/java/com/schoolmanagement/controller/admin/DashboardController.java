package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.AccountDetails;
import java.security.Principal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
  @GetMapping("/")
  public String dashboard (@AuthenticationPrincipal AccountDetails accountDetails, Model model) {
    model.addAttribute("image", accountDetails.getImage());

    return "/admin/index";
  }
}
