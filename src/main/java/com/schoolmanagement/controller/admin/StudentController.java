package com.schoolmanagement.controller.admin;

import com.schoolmanagement.helper.StudentExcelExporter;
import com.schoolmanagement.helper.StudentExcelImporter;
import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.ClassTeacherSubjectService;
import com.schoolmanagement.service.StudentEvaluateService;
import com.schoolmanagement.service.StudentService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {
  @Autowired
  private StudentService studentService;
  @Autowired
  private ClassService classService;
  @Autowired
  private ClassTeacherSubjectService classTeacherSubjectService;
  @Autowired
  private StudentEvaluateService studentEvaluateService;
  @Autowired
  private EntityManager entityManager;

  @GetMapping("/show/student")
  public String listStudent(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {

    return listStudentByPage(model, 1, "id", "asc", "", "all", "", "", "", accountDetails);
  }

  @GetMapping("/show/student/{page}")
  public String listStudentByPage(Model model,
      @PathVariable("page") int currentPage, @Param("sortField") String sortField,
      @Param("sortDir") String sortDir, @Param("search") String search,
      @Param("status") String status, @Param("grade") String grade,
      @Param("class-name") String className, @Param("school-year") String schoolYear,
      @AuthenticationPrincipal AccountDetails accountDetails) {
    Page<User> page;

    if (accountDetails.hasRole("ADMIN")) {
      page = studentService.searchStudent(search, status, currentPage, sortField,
          sortDir, grade, className, schoolYear);
    } else {
      Set<Class> classList = classTeacherSubjectService.findAllByTeacher(accountDetails.getId());

      page = studentService.findAllStudentByListClass(classList, currentPage, sortField, sortDir, search, grade, className);
    }

    assert page != null;
    int totalPages = page.getTotalPages();
    List<User> studentList = new ArrayList<>();
    for (User user : page.getContent()) {
      if (user.hasRole("STUDENT")) {
        studentList.add(user);
      }
    }

    model.addAttribute("studentList", studentList);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("sortField", sortField);
    model.addAttribute("search", search);
    model.addAttribute("status", status);
    model.addAttribute("className", className);
    model.addAttribute("grade", grade);
    model.addAttribute("schoolYear", schoolYear);
    model.addAttribute("classList", classService.getAllClass());
    model.addAttribute("schoolYearList", classService.getSchoolYear());

    String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
    model.addAttribute("reverseSortDir", reverseSortDir);

    return "/admin/student/student_management";
  }

  @GetMapping("/insert/student")
  public String insertStudent(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("classList", classService.getAllClass());

    return "/admin/student/form_student";
  }

  @PostMapping("/save/student")
  public String saveStudent(@Valid User user, BindingResult result,
      @RequestParam("fileImage") MultipartFile multipartFile, Model model,
      RedirectAttributes rdrAttr) throws IOException {

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

      user.setImage(str_filename);
    }
    if (user.getId() == null) {
      int size = studentService.findAllByAdmissionYear(user.getUserInfo().getAdmissionYear()).size();
      user.setUsername("std_" + user.getUserInfo().getAdmissionYear() + "_" + (size+ 1));
      if (user.getUserInfo().getAdmissionYear()== null) {
        result.rejectValue("userInfo.admissionYear", "error.user.userInfo", "Enter Admission Year");
      }
      if (user.getUserInfo().getGraduateYear()== null) {
        result.rejectValue("userInfo.graduateYear", "error.user.userInfo", "Enter Graduate Year");
      }
      if (user.getUserInfo().getStatus()== null) {
        result.rejectValue("userInfo.status", "error.user.userInfo", "Choose a status");
      }
      if (user.getUserInfo().getAClass()== null) {
        result.rejectValue("userInfo.aClass", "error.user.userInfo", "Choose a class");
      }
      if (user.getUsername().equalsIgnoreCase("")) {
        result.rejectValue("username", "error.user", "Enter username");
      }

      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String rawPassword = "123456";
      String encoderPassword = encoder.encode(rawPassword);
      user.setPassword(encoderPassword);

      user.setCreatedDate(LocalDateTime.now());
      user.setUpdatedDate(LocalDateTime.now());
    } else {
      user.setUsername(user.getUsername());
      user.setPassword(user.getPassword());

      user.setCreatedDate(user.getCreatedDate());
      user.setUpdatedDate(LocalDateTime.now());
    }

    if (user.getUserInfo().getGraduateYear() != null && user.getUserInfo().getAdmissionYear() != null) {
      if (user.getUserInfo().getAdmissionYear() > user.getUserInfo().getGraduateYear()) {
        result.rejectValue("graduateYear", "error.student", "Graduate Year must be greater than Admission Year");
      }
    }

    if (user.getDob() != null) {
      if (LocalDateTime.now().getYear() - user.getDob().getYear() < 16) {
        result.rejectValue("dob", "error.student", "Students must be 16 years old");
      }
    }
    Role role = entityManager.find(Role.class, 4);
    user.addRole(role);

    if (result.hasErrors()) {
      model.addAttribute("classList", classService.getAllClass());

      return "/admin/student/form_student";
    }
    studentService.saveStudent(user);

    if (user.getId() == null) {
      rdrAttr.addFlashAttribute("message", "Add student successfully");
    } else {
      rdrAttr.addFlashAttribute("message", "Edit student successfully");
    }

    return "redirect:/show/student";
  }

  @GetMapping("/show/student/details/{id}")
  public String studentDetails(Model model, @PathVariable("id") Integer id) {
    model.addAttribute("student", studentService.getStudentById(id));

    return "/admin/student/student_details";
  }

  @GetMapping("/show/student/search")
  public String searchStudent(@RequestParam("search") String search,
      @RequestParam("status") String status, @RequestParam("grade") String grade,
      @RequestParam("class-name") String className, @AuthenticationPrincipal AccountDetails accountDetails,
      @RequestParam("school-year") String schoolYear, Model model) {

    return listStudentByPage(model, 1, "id", "asc", search, status, grade, className, schoolYear, accountDetails);
  }

  @GetMapping("/show/student/teacher/search")
  public String searchStudentForTeacher(@RequestParam("search") String search, @RequestParam("grade") String grade,
      @RequestParam("class-name") String className, @AuthenticationPrincipal AccountDetails accountDetails, Model model) {

    return listStudentByPage(model, 1, "id", "asc", search, "all", grade, className, "", accountDetails);
  }

  @GetMapping("/edit/student/{id}")
  public String editStudent(@PathVariable("id") Integer id, Model model){
    model.addAttribute("user", studentService.getStudentById(id));
    model.addAttribute("classList", classService.getAllClass());

    return "/admin/student/form_student";
  }

  @RequestMapping("/export/student")
  @ResponseBody
  public void exportToExcel(HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=students_" + currentDateTime + ".xlsx";
    response.setHeader(headerKey, headerValue);

    List<User> studentList = (List<User>) studentService.getAllStudent();

    StudentExcelExporter excelExporter = new StudentExcelExporter(studentList);

    excelExporter.export(response);
  }

  @PostMapping("/import/student")
  public String importFromExcel(@RequestParam("fileImage") MultipartFile multipartFile)
      throws IOException {
    if (multipartFile != null) {
      StudentExcelImporter excelImporter = new StudentExcelImporter();
      Role role = entityManager.find(Role.class, 4);
      Iterable<User> studentList = excelImporter.excelImport(multipartFile, studentService, classService, role);
      studentService.saveAlLStudent(studentList);
    }

    return "redirect:/show/teacher";
  }

  @PreAuthorize("hasAuthority('TEACHER')")
  @GetMapping("/insert/student/mark")
  public String insertStudentMark() {
    return "/admin/index";
  }

  @GetMapping("/show/student-class-detail/{id}")
  public String showStudentClassDetail(Model model, @PathVariable("id") Integer id) {
    User student = studentService.getStudentById(id);
    Iterable<StudentEvaluate> studentEvaluates = studentEvaluateService.getAllByStudent(student);

    model.addAttribute("student", student);
    model.addAttribute("studentEvaluates", studentEvaluates);

    return "/admin/student/student_class_detail";
  }
}
