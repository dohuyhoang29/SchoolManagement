package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.request.ResetPasswordRequest;
import com.schoolmanagement.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResetPasswordController {

  @Autowired
  private UserService userService;

  @GetMapping("/reset-password/{id}")
  public String showFormResetPassword(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("user", userService.findByUserId(id));
    model.addAttribute("reset", new ResetPasswordRequest());

    return "/admin/reset_password";
  }

  @PostMapping("/reset-password/save/{id}")
  public String resetPassword(@PathVariable("id") Integer id, Model model,
      @Valid @ModelAttribute("reset") ResetPasswordRequest resetPasswordRequest, BindingResult result) {
    if (result.hasErrors())  {
      model.addAttribute("user", userService.findByUserId(id));

      return "/admin/reset_password";
    }

    userService.resetPassword(resetPasswordRequest, id);

    return "redirect:/show/student";
  }
}
