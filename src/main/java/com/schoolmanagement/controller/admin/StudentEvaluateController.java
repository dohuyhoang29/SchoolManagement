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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    StudentEvaluate evaluate = studentEvaluateServiceImp.findStudentEvaluateByStudentId(id);
    if (evaluate == null) {
      User student = studentServiceImp.getStudentById(id);
      StudentEvaluate studentEvaluate= new StudentEvaluate();

      studentEvaluate.setStudent(student);

      model.addAttribute("student", student);
      model.addAttribute("studentEvaluate", studentEvaluate);
    } else {
      model.addAttribute("student", evaluate.getStudent());
      model.addAttribute("studentEvaluate", evaluate);
    }

    return "/admin/student/student_evaluate";
  }

  @PreAuthorize("hasAnyAuthority('HOMEROOM_TEACHER')")
  @PostMapping("/evaluate/save/{id}")
  public String saveStudentEvaluate (@Valid StudentEvaluate studentEvaluate, BindingResult result,
      @AuthenticationPrincipal AccountDetails accountDetails, @PathVariable("id") Integer id, Model model) {
    User student = studentServiceImp.getStudentById(id);
    User user = accountDetails.getUser();
    studentEvaluate.setCreatedBy(user);
    studentEvaluate.setUpdatedBy(user);
    studentEvaluate.setCreatedDate(LocalDate.now());
    studentEvaluate.setUpdatedDate(LocalDate.now());
    studentEvaluate.setStudent(student);

    if (result.hasErrors()) {
      studentEvaluate.setStudent(student);

      model.addAttribute("student", student);
      model.addAttribute("studentEvaluate", studentEvaluate);

      return "/admin/student/student_evaluate";
    }

    studentEvaluateServiceImp.saveStudentEvaluate(studentEvaluate);
    return "redirect:/show/student";
  }
}
