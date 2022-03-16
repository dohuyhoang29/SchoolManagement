package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.implement.StudentEvaluateServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentEvaluateController {
  @Autowired
  private StudentServiceImp studentServiceImp;
  @Autowired
  private StudentEvaluateServiceImp studentEvaluateServiceImp;

  @PreAuthorize("hasAnyAuthority('HOMEROOM_TEACHER')")
  @GetMapping("/insert/evaluate/{id}")
  public String insertStudentEvaluate(@PathVariable("id") Integer id, Model model) {
    User student = studentServiceImp.getStudentById(id);
    StudentEvaluate studentEvaluate= new StudentEvaluate();

    studentEvaluate.setStudent(student);

    model.addAttribute("student", student);
    model.addAttribute("studentEvaluate", studentEvaluate);

    return "/admin/student/student_evaluate";
  }

  @PreAuthorize("hasAnyAuthority('HOMEROOM_TEACHER')")
  @PostMapping("/evaluate/save")
  public String saveStudentEvaluate (@Valid StudentEvaluate studentEvaluate, BindingResult result, AccountDetails accountDetails) {
    studentEvaluate.setCreatedBy(accountDetails.getUser());
    studentEvaluate.setUpdatedBy(accountDetails.getUser());
    studentEvaluate.setCreatedDate(LocalDate.now());
    studentEvaluate.setUpdatedDate(LocalDate.now());

    if (result.hasErrors()) {
      return "/admin/student/student_evaluate";
    }

    studentEvaluateServiceImp.saveStudentEvaluate(studentEvaluate);
    return "redirect:/show/student";
  }
}
