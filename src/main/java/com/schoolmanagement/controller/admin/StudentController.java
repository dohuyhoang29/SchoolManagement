package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.Student;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.service.implement.ClassServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

  @Autowired
  private StudentServiceImp studentServiceImp;

  @Autowired
  private StudentRepositories studentRepositories;

  @Autowired
  private ClassServiceImp classServiceImp;

  @Autowired
  private EntityManager entityManager;

  @GetMapping("/show/student")
  public String listStudent(Model model) {
    return listStudentByPage(model, 1, "fullName", "asc", "", "all");
  }

  @GetMapping("/show/student/{page}")
  public String listStudentByPage(Model model,
      @PathVariable("page") int currentPage,
      @Param("sortField") String sortField,
      @Param("sortDir") String sortDir,
      @Param("search") String search,
      @Param("status") String status) {
    Page<Student> page = studentServiceImp.searchStudent(search, status, currentPage, sortField,
        sortDir);

    long totalItems = page.getTotalElements();
    int totalPages = page.getTotalPages();
    List<Student> studentList = page.getContent();

    model.addAttribute("studentList", studentList);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("totalItems", totalItems);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("search", search);
    model.addAttribute("status", status);

    String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
    model.addAttribute("reverseSortDir", reverseSortDir);

    return "/admin/student/student_management";
  }

  @GetMapping("/insert/student")
  public String insertStudent(Model model) {
    model.addAttribute("student", new Student());
    model.addAttribute("classList", classServiceImp.getAllClass());

    return "/admin/student/form_student";
  }

  @PostMapping("/save/student")
  public String saveStudent(@Valid Student student, BindingResult result,
      @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

    String root = "src/main/";
    String folder = "upload/image/student_image/";
    String org_filename = multipartFile.getOriginalFilename();
    String str_filename = "";
    if (org_filename != null && !org_filename.isEmpty()) {
      str_filename = UUID.randomUUID() + org_filename.substring(org_filename.lastIndexOf('.'));

      if (!Files.exists(Paths.get(root + folder))) {
        Files.createDirectories(Paths.get(root + folder));
      }
      Files.copy(multipartFile.getInputStream(), Paths.get(root + folder + str_filename),
          StandardCopyOption.REPLACE_EXISTING);

      student.setImage(str_filename);
    }

    student.setUsername("std_" + student.getAdmissionYear() + "_" + (
        studentRepositories.findAllByAdmissionYear(student.getAdmissionYear()).size() + 1));

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = "123456";
    String encoderPassword = encoder.encode(rawPassword);
    student.setPassword(encoderPassword);

    student.setCreatedDate(LocalDateTime.now());
    student.setUpdatedDate(LocalDateTime.now());

    if (result.hasErrors()) {
      return "/admin/student/form_student";
    }
    studentServiceImp.saveStudent(student);

    return "redirect:/show/student";
  }

  @GetMapping("/show/student/details/{id}")
  public String studentDetails(Model model, @PathVariable("id") Integer id) {
    model.addAttribute("student", studentServiceImp.getStudentById(id));

    return "/admin/student/student_details";
  }

  @GetMapping("/show/student/search")
  public String searchStudent(@RequestParam("search") String search,
      @RequestParam("status") String status,
      Model model) {

    return listStudentByPage(model, 1, "fullName", "asc", search, status);
  }
}
