package com.schoolmanagement.controller.admin;

import com.schoolmanagement.SchoolManagementApplication;
import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.StudentService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private StudentRepositories studentRepositories;

  @Autowired
  private ClassService classService;

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
    Page<Student> page = studentService.searchStudent(search, status, currentPage, sortField,
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

    List<Class> classList = new ArrayList<>();

    for (Class c : (List<Class>) classService.getAllClass()) {
      if (c.getSchoolYear() == 2022) {
        classList.add(c);
      }
    }

    model.addAttribute("classList", classList);

    return "/admin/student/form_student";
  }

  @PostMapping("/save/student")
  public String saveStudent(@Valid Student student, BindingResult result,
      @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

    String uploadDir = "./src/main/resources/static/images/student-images/";
    Path uploadPath = Paths.get(uploadDir);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }
    String imageName = SchoolManagementApplication.randomString(fileName);
    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
      Files.move(filePath, filePath.resolveSibling(imageName));
    } catch (IOException ioException) {
      throw new IOException("Could not save upload file : " + fileName);
    }
    student.setImage(imageName);

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
    studentService.saveStudent(student);

    return "redirect:/show/student";
  }

  @GetMapping("/show/student/details/{id}")
  public String studentDetails(Model model, @PathVariable("id") Integer id) {
    model.addAttribute("student", studentService.getStudentById(id));

    return "/admin/student/student_details";
  }

  @GetMapping("/show/student/search")
  public String searchStudent(@RequestParam("search") String search,
      @RequestParam("status") String status,
      Model model) {

    return listStudentByPage(model, 1, "fullName", "asc", search, status);
  }
}
