package com.schoolmanagement.controller.admin;

import com.schoolmanagement.helper.TeacherExcelExporter;
import com.schoolmanagement.helper.TeacherExcelImporter;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.EditTeacherRequest;
import com.schoolmanagement.model.request.InsertTeacherRequest;
import com.schoolmanagement.model.request.TeacherManagementRequest;
import com.schoolmanagement.service.TeacherService;
import com.schoolmanagement.service.UserService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
public class TeacherController {

  @Autowired
  private TeacherService teacherService;
  @Autowired
  private UserService userService;
  @Autowired
  private EntityManager entityManager;
  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/show/teacher/{page}")
  public String listTeacherByPage(Model model, @PathVariable("page") int currentPage,
      @Param("sortField") String sortField, @Param("sortDir") String sortDir,
      @Param("search") String search, @Param("status") String status) {
    Page<User> page = teacherService.searchTeacher(currentPage, search, status, sortField, sortDir);

    List<TeacherManagementRequest> list = page.getContent().stream().map(
        user -> modelMapper.map(user, TeacherManagementRequest.class)).collect(Collectors.toList());

    model.addAttribute("listUser", list);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("search", search);
    model.addAttribute("status", status);

    String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
    model.addAttribute("reverseSortDir", reverseSortDir);

    return "/admin/teacher/teacher_management";
  }

  @GetMapping("/show/teacher")
  public String listTeacher(Model model) {
    return listTeacherByPage(model, 1, "id", "asc", "", "all");
  }

  @GetMapping("/show/teacher/details/{id}")
  public String teacherDetails(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("user", teacherService.findTeacherDetail(id));

    return "/admin/teacher/teacher_details";
  }

  @GetMapping("/insert/teacher")
  public String insertTeacher(Model model) {
    model.addAttribute("user", new InsertTeacherRequest());

    return "/admin/teacher/form_teacher";
  }

  @PostMapping("/teacher/save")
  public String saveTeacher(@Valid @ModelAttribute("user") InsertTeacherRequest user,
      BindingResult result, @RequestParam("fileImage") MultipartFile multipartFile,
      RedirectAttributes rdrAttr) throws IOException {

    if (result.hasErrors()) {
      return "/admin/teacher/form_teacher";
    }

    rdrAttr.addFlashAttribute("message", "Add teacher successfully");
    teacherService.saveUser(user, multipartFile);

    return "redirect:/show/teacher";
  }

  @PostMapping("/teacher/update")
  public String updateTeacher(@Valid @ModelAttribute("user") EditTeacherRequest user,
      BindingResult result,
      @RequestParam("fileImage") MultipartFile multipartFile, RedirectAttributes attributes) {
    if (result.hasErrors()) {
      return "/admin/teacher/edit_teacher";
    }

    teacherService.updateTeacher(user, multipartFile);

    attributes.addFlashAttribute("message", "Edit teacher successfully");

    return "redirect:/show/teacher";
  }

  @GetMapping("/edit/teacher/{id}")
  public String editTeacher(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("user", teacherService.getUserById(id));

    return "/admin/teacher/edit_teacher";
  }

  @GetMapping("/show/teacher/search")
  public String searchTeacher(@RequestParam(value = "search") String search,
      @RequestParam(value = "status") String status, Model model) {

    return listTeacherByPage(model, 1, "fullName", "asc", search, status);
  }

  @RequestMapping("/export/teacher")
  @ResponseBody
  public void exportToExcel(HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
    response.setHeader(headerKey, headerValue);

    List<User> listUsers = teacherService.getAllUser();

    TeacherExcelExporter excelExporter = new TeacherExcelExporter(listUsers);

    excelExporter.export(response);
  }

  @PostMapping("/import/teacher")
  public String importFromExcel(@RequestParam("fileImage") MultipartFile multipartFile)
      throws IOException {
    if (multipartFile != null) {
      TeacherExcelImporter excelImporter = new TeacherExcelImporter();
      Role role = entityManager.find(Role.class, 2);
      Iterable<User> listUser = excelImporter.excelImport(multipartFile, role);
      teacherService.saveAllUser(listUser);
    }

    return "redirect:/show/teacher";
  }

  @GetMapping("/update/teacher/retired/{id}")
  public String makeTeacherRetired(@PathVariable("id") Integer id) {
    teacherService.makeRetired(id);

    return "redirect:/show/teacher";
  }

  @GetMapping("/update/teacher/working/{id}")
  public String makeTeacherWorking(@PathVariable("id") Integer id) {
    teacherService.makeWorking(id);

    return "redirect:/show/teacher";
  }
}
