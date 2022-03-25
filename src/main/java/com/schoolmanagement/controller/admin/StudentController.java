package com.schoolmanagement.controller.admin;

import com.schoolmanagement.helper.StudentExcelExporter;
import com.schoolmanagement.helper.StudentExcelImporter;
import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.ResetPasswordRequest;
import com.schoolmanagement.model.request.StudentManagementRequest;
import com.schoolmanagement.model.request.StudentRequest;
import com.schoolmanagement.model.request.TeacherManagementRequest;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.ClassTeacherSubjectService;
import com.schoolmanagement.service.StudentEvaluateService;
import com.schoolmanagement.service.StudentService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
  @Autowired
  private ModelMapper modelMapper;

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

      page = studentService.findAllStudentByListClass(classList, currentPage, sortField, sortDir,
          search, grade, className);
    }

    List<StudentManagementRequest> list = page.getContent().stream().map(
        user -> modelMapper.map(user, StudentManagementRequest.class)).collect(Collectors.toList());

    model.addAttribute("studentList", list);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", page.getTotalPages());
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
    model.addAttribute("user", new StudentRequest());
    model.addAttribute("classList", classService.getAllClass());

    return "/admin/student/form_student";
  }

  @PostMapping("/save/student")
  public String saveStudent(@Valid @ModelAttribute("user") StudentRequest studentRequest, BindingResult result,
      @RequestParam("fileImage") MultipartFile multipartFile, Model model,
      RedirectAttributes rdrAttr) throws IOException {

    if (result.hasErrors()) {
      model.addAttribute("classList", classService.getAllClass());

      return "/admin/student/form_student";
    }
    studentService.saveStudent(studentRequest, multipartFile);

    if (studentRequest.getId() == null) {
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
      @RequestParam("class-name") String className,
      @AuthenticationPrincipal AccountDetails accountDetails,
      @RequestParam("school-year") String schoolYear, Model model) {

    return listStudentByPage(model, 1, "id", "asc", search, status, grade, className, schoolYear,
        accountDetails);
  }

  @GetMapping("/show/student/teacher/search")
  public String searchStudentForTeacher(@RequestParam("search") String search,
      @RequestParam("grade") String grade,
      @RequestParam("class-name") String className,
      @AuthenticationPrincipal AccountDetails accountDetails, Model model) {

    return listStudentByPage(model, 1, "id", "asc", search, "all", grade, className, "",
        accountDetails);
  }

  @GetMapping("/edit/student/{id}")
  public String editStudent(@PathVariable("id") Integer id, Model model) {
    StudentRequest studentRequest = modelMapper.map(studentService.getStudentById(id), StudentRequest.class);

    model.addAttribute("user", studentRequest);
    model.addAttribute("classList", classService.getAllClass());

    return "/admin/student/form_student";
  }

  @PostMapping("/student/update")
  public String updateStudent() {


    return "redirect:/show/student";
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
      Iterable<User> studentList = excelImporter.excelImport(multipartFile, studentService,
          classService, role);
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
