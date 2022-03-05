package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.StudentService;
import com.schoolmanagement.service.SubjectService;
import com.schoolmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClassController {

  @Autowired
  private ClassService classService;

  @Autowired
  private StudentService studentService;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private UserService userService;

  @GetMapping("/show/class")
  public String classList(Model model) {
    model.addAttribute("classList", classService.getAllClass());

    return "/admin/class/class_management";
  }

  @GetMapping("/insert/class")
  public String insertClass(Model model) {
    model.addAttribute("class", new Class());
    model.addAttribute("studentList", studentService.getAllStudent());
    model.addAttribute("subjectList", subjectService.getAllSubject());
    model.addAttribute("userList", userService.getAllUser());

    return "/admin/class/form_class";
  }

  @PostMapping("/save/class")
  public String saveClass(Class aClass) {
    classService.saveClass(aClass);

    return "redirect:/show/class";
  }
}
