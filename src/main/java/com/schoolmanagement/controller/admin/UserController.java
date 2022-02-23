package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.UserService;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private EntityManager entityManager;

  @GetMapping("/admin/user/insert")
  public String insertTeacher(Model model){
    model.addAttribute("user", new User());

    return "/admin/user/form_user";
  }

  @PostMapping("/admin/user/save")
  public String saveTeacher(User user, BindingResult result){

    user.setCreatedDate(LocalDateTime.now());
    user.setUpdatedDate(LocalDateTime.now());
    user.setDeleted(false);
    Role role = entityManager.find(Role.class, 2);
    user.addRole(role);
    User teacher = user;
    userService.saveUser(teacher);

    return "redirect:/admin/user/user_management";
  }

  @GetMapping("/admin/user/user_management")
  public String listTeacher(){

    return "/admin/user/user_management";
  }
}
