package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.service.BlogService;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.StudentService;
import com.schoolmanagement.service.TeacherService;
import com.schoolmanagement.service.implement.BlogServiceImp;
import com.schoolmanagement.service.implement.ClassServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import com.schoolmanagement.service.implement.TeacherServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
  @Autowired
  private TeacherService teacherService;
  @Autowired
  private StudentService studentService;
  @Autowired
  private ClassService classService;
  @Autowired
  private BlogService blogService;

  @GetMapping("/dashboard")
  public String dashboard (@AuthenticationPrincipal AccountDetails accountDetails, Model model) {

    model.addAttribute("totalTeacher", teacherService.countTeacher());
    model.addAttribute("totalStudent", studentService.countAllStudent());
    model.addAttribute("totalClass", classService.countAllClass());
    model.addAttribute("totalBlog", blogService.countAllBlog());
    model.addAttribute("totalNewStudentLastYear", studentService.countAllNewStudentLastYear());
    model.addAttribute("totalNewStudentThisYear", studentService.countAllNewStudentThisYear());
    model.addAttribute("totalGraduateLastYear", studentService.countAllStudentGraduateLastYear());
    model.addAttribute("totalAbsentLastYear", studentService.countAllStudentAbsentLastYear());
    model.addAttribute("totalFailLastYear", studentService.countAllStudentFailLastYear());
    model.addAttribute("totalNewStudent10Year", studentService.countAllNewStudentLast10Year());
    model.addAttribute("totalGraduate10Year", studentService.countAllStudentGraduateLast10Year());
    model.addAttribute("totalAbsent10Year", studentService.countAllStudentAbsentLast10Year());
    model.addAttribute("totalFail10Year", studentService.countAllStudentFailLast10Year());

    return "/admin/index";
  }
}
