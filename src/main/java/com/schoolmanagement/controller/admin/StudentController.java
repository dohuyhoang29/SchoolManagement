package com.schoolmanagement.controller.admin;

import com.schoolmanagement.helper.StudentExcelExporter;
import com.schoolmanagement.helper.StudentExcelImporter;
import com.schoolmanagement.helper.TeacherExcelExporter;
import com.schoolmanagement.helper.TeacherExcelImporter;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.service.implement.ClassServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    return listStudentByPage(model, 1, "id", "asc", "", "all", "", "", "");
  }

  @GetMapping("/show/student/{page}")
  public String listStudentByPage(Model model,
      @PathVariable("page") int currentPage,
      @Param("sortField") String sortField,
      @Param("sortDir") String sortDir,
      @Param("search") String search,
      @Param("status") String status,
      @Param("grade") String grade,
      @Param("class-name") String className,
      @Param("school-year") String schoolYear) {
    Page<Student> page = studentServiceImp.searchStudent(search, status, currentPage, sortField,
        sortDir, grade, className, schoolYear);

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
    model.addAttribute("className", className);
    model.addAttribute("grade", grade);
    model.addAttribute("schoolYear", schoolYear);
    model.addAttribute("classList", classServiceImp.getAllClass());
    model.addAttribute("schoolYearList", classServiceImp.getSchoolYear());

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

      student.setImage(str_filename);
    }
    if (student.getId() == null) {
      int size = studentRepositories.findAllByAdmissionYear(student.getAdmissionYear()).size();
      student.setUsername("std_" + student.getAdmissionYear() + "_" + (size+ 1));

      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String rawPassword = "123456";
      String encoderPassword = encoder.encode(rawPassword);
      student.setPassword(encoderPassword);

      student.setCreatedDate(LocalDateTime.now());
      student.setUpdatedDate(LocalDateTime.now());
    } else {
      student.setUsername(student.getUsername());
      student.setPassword(student.getPassword());

      student.setCreatedDate(student.getCreatedDate());
      student.setUpdatedDate(LocalDateTime.now());
    }

    if (student.getGraduateYear() != null && student.getAdmissionYear() != null) {
      if (student.getAdmissionYear() > student.getGraduateYear()) {
        result.rejectValue("graduateYear", "error.student", "Graduate Year must be greater than Admission Year");
      }
    }

    if (student.getDob() != null) {
      if (LocalDateTime.now().getYear() - student.getDob().getYear() < 16) {
        result.rejectValue("dob", "error.student", "Students must be 16 years old");
      }
    }

    if (result.hasErrors()) {
      model.addAttribute("classList", classServiceImp.getAllClass());

      return "/admin/student/form_student";
    }
    studentServiceImp.saveStudent(student);

    if (student.getId() == null) {
      rdrAttr.addFlashAttribute("message", "Add student successfully");
    } else {
      rdrAttr.addFlashAttribute("message", "Edit student successfully");
    }

    return "redirect:/show/student";
  }

  @GetMapping("/show/student/details/{id}")
  public String studentDetails(Model model, @PathVariable("id") Integer id) {
    model.addAttribute("student", studentServiceImp.getStudentById(id));

    return "/admin/student/student_details";
  }

  @GetMapping("/show/student/search")
  public String searchStudent(@RequestParam("search") String search,
      @RequestParam("status") String status, @RequestParam("grade") String grade,
      @RequestParam("class-name") String className,
      @RequestParam("school-year") String schoolYear, Model model) {

    return listStudentByPage(model, 1, "id", "asc", search, status, grade, className, schoolYear);
  }

  @GetMapping("/edit/student/{id}")
  public String editStudent(@PathVariable("id") Integer id, Model model){
    model.addAttribute("student", studentServiceImp.getStudentById(id));
    model.addAttribute("classList", classServiceImp.getAllClass());

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

    List<Student> studentList = (List<Student>) studentServiceImp.getAllStudent();

    StudentExcelExporter excelExporter = new StudentExcelExporter(studentList);

    excelExporter.export(response);
  }

  @PostMapping("/import/student")
  public String importFromExcel(@RequestParam("fileImage") MultipartFile multipartFile)
      throws IOException {
    if (multipartFile != null) {
      StudentExcelImporter excelImporter = new StudentExcelImporter();
      Iterable<Student> studentList = excelImporter.excelImport(multipartFile);
      studentServiceImp.saveAlLStudent(studentList);
    }

    return "redirect:/show/teacher";
  }
}
