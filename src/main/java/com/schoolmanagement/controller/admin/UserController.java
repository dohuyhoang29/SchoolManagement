package com.schoolmanagement.controller.admin;

import com.schoolmanagement.SchoolManagementApplication;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.UserService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private EntityManager entityManager;

  @GetMapping("/admin/user/insert")
  public String insertTeacher(Model model) {
    model.addAttribute("user", new User());

    return "/admin/user/form_user";
  }

  @PostMapping("/admin/user/save")
  public String saveTeacher(@Valid User user, BindingResult result,
      @RequestParam("fileImage") MultipartFile multipartFile)
      throws IOException {

    user.setCreatedDate(LocalDateTime.now());
    user.setUpdatedDate(LocalDateTime.now());

    Role role = entityManager.find(Role.class, 2);
    user.addRole(role);

    if (user.getImage() != null) {
      String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

      String uploadDir = "./src/main/resources/static/images/user-images/";
      Path uploadPath = Paths.get(uploadDir);

      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }
      String imageName = SchoolManagementApplication.randomString(fileName);
      try (InputStream inputStream = multipartFile.getInputStream()) {
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        Files.move(filePath,
            filePath.resolveSibling(imageName));
      } catch (IOException ioException) {
        throw new IOException("Could not save uploaded  file : " + fileName);
      }
      user.setImage(imageName);
    }

    if (result.hasErrors()) {
      return "/admin/user/form_user";
    }
    userService.saveUser(user);

    return "redirect:/admin/user/user_management";
  }

  @GetMapping("/admin/user/user_management")
  public String listTeacher(Model model) {
    return listTeacherByPage(model, 1, "fullName", "asc", "", "all");
  }

  @GetMapping("/admin/user/user_management/{page}")
  public String listTeacherByPage(Model model,
      @PathVariable("page") int currentPage,
      @Param("sortField") String sortField,
      @Param("sortDir") String sortDir,
      @Param("search") String search,
      @Param("status") String status) {
    Page<User> page;

    if (status.equalsIgnoreCase("all")) {
      page = userService.searchUserByFullName(search, currentPage, sortField, sortDir);
    } else if (status.equalsIgnoreCase("true")) {
      page = userService.searchUserByFullNameAndDeleted(search, true, currentPage, sortField,
          sortDir);
    } else {
      page = userService.searchUserByFullNameAndDeleted(search, false, currentPage, sortField,
          sortDir);
    }
    long totalItems = page.getTotalElements();
    int totalPages = page.getTotalPages();
    List<User> listUser = page.getContent();

    model.addAttribute("listUser", listUser);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalItems", totalItems);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("search", search);
    model.addAttribute("status", status);

    String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
    model.addAttribute("reverseSortDir", reverseSortDir);
    return "/admin/user/user_management";
  }

  @GetMapping("/admin/user/details/{id}")
  public String teacherDetails(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("user", userService.getUserById(id));

    return "/admin/user/user_details";
  }

  @GetMapping("/admin/user/edit/{id}")
  public String editTeacher(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("user", userService.getUserById(id));

    return "/admin/user/form_user";
  }

  @GetMapping("/admin/user/user_management/search")
  public String searchTeacher(@RequestParam(value = "search") String search,
      @RequestParam(value = "status") String status, Model model) {

    return listTeacherByPage(model, 1, "fullName", "asc", search, status);
  }
}
