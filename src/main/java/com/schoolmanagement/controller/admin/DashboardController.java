package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.service.implement.BlogServiceImp;
import com.schoolmanagement.service.implement.ClassServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import com.schoolmanagement.service.implement.TeacherServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
  @Autowired
  private TeacherServiceImp teacherServiceImp;
  @Autowired
  private StudentServiceImp studentServiceImp;
  @Autowired
  private ClassServiceImp classServiceImp;
  @Autowired
  private BlogServiceImp blogServiceImp;

  @GetMapping("/")
  public String dashboard (@AuthenticationPrincipal AccountDetails accountDetails, Model model) {
    model.addAttribute("totalTeacher", teacherServiceImp.countTeacher());
    model.addAttribute("totalStudent", studentServiceImp.countAllStudent());
    model.addAttribute("totalClass", classServiceImp.countAllClass());
    model.addAttribute("totalBlog", blogServiceImp.countAllBlog());

    return "/admin/index";
  }
}
