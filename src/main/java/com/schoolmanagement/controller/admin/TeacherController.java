package com.schoolmanagement.controller.admin;

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
import java.util.Objects;
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

import com.schoolmanagement.helper.UserExcelExporter;
import com.schoolmanagement.helper.UserExcelImporter;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.implement.UserServiceImp;

@Controller
public class TeacherController {

  @Autowired
  private UserServiceImp userService;

	@Autowired
	private EntityManager entityManager;

	@GetMapping("/show/teacher/{page}")
	public String listTeacherByPage(Model model, @PathVariable("page") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("search") String search,
			@Param("status") String status) {
		Page<User> page;

		if (status.equalsIgnoreCase("all")) {
			page = userService.searchUserByFullName(search, currentPage, sortField, sortDir);
		} else if (status.equalsIgnoreCase("true")) {
			page = userService.searchUserByFullNameAndDeleted(search, true, currentPage, sortField, sortDir);
		} else {
			page = userService.searchUserByFullNameAndDeleted(search, false, currentPage, sortField, sortDir);
		}
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<User> list = page.getContent();
		List<User> listUser = new ArrayList<>();

		for (User user : list) {
			for (Role role : user.getRoles()) {
				if (role.getRoleID() == 2) {
					listUser.add(user);
				}
			}
		}

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
		return "/admin/teacher/user_management";
	}

	@GetMapping("/show/teacher")
	public String listTeacher(Model model) {
		return listTeacherByPage(model, 1, "id", "asc", "", "all");
	}

	@GetMapping("/show/teacher/details/{id}")
	public String teacherDetails(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));

		return "/admin/teacher/user_details";
	}

	@GetMapping("/insert/teacher")
	public String insertTeacher(Model model) {
		model.addAttribute("user", new User());

    return "/admin/teacher/form_user";
  }

	@PostMapping("/teacher/save")
	public String saveTeacher(@Valid User user, BindingResult result,
			@RequestParam("fileImage") MultipartFile multipartFile,
			RedirectAttributes rdrAttr) throws IOException {

		user.setCreatedDate(LocalDateTime.now());
		user.setUpdatedDate(LocalDateTime.now());

		Role role = entityManager.find(Role.class, 2);
		user.addRole(role);

		String root = "src/main/";
		String folder = "upload/image/user_image/";
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
			if (user.getPassword().isEmpty()) {
				result.rejectValue("password", "error.user", "Enter password");
			} else {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String rawPassword = user.getPassword();
				String encoderPassword = encoder.encode(rawPassword);
				user.setPassword(encoderPassword);
			}
			if (userService.getUserByEmail(user.getEmail()) != null) {
				result.rejectValue("email", "error.user", "An account already exists for this email.");
			}
		} else {
			user.setPassword(user.getPassword());
			if (userService.getUserByEmail(user.getEmail()) != null &&
					!Objects.equals(user.getId(), userService.getUserByEmail(user.getEmail()).getId())) {
				result.rejectValue("email", "error.user", "An account already exists for this email.");
			}
		}

		if (result.hasErrors()) {
			return "/admin/teacher/form_user";
		}

		if (user.getImage() == null) {
			rdrAttr.addFlashAttribute("message", "Add teacher successfully");
		} else {
			rdrAttr.addFlashAttribute("message", "Edit teacher successfully");
		}

		userService.saveUser(user);

		return "redirect:/show/teacher";
	}

	@GetMapping("/edit/teacher/{id}")
	public String editTeacher(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));

		return "/admin/teacher/form_user";
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

		List<User> listUsers = userService.getAllUser();

		UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

		excelExporter.export(response);
	}

	@PostMapping("/import/teacher")
	public String importFromExcel(@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		if (multipartFile != null) {
			UserExcelImporter excelImporter = new UserExcelImporter();
			Role role = entityManager.find(Role.class, 2);
			Iterable<User> listUser = excelImporter.excelImport(multipartFile, role);
			userService.saveAllUser(listUser);
		}

		return "redirect:/show/teacher";
	}

	@GetMapping("/update/teacher/retired/{id}")
	public String makeTeacherRetired(@PathVariable("id") Integer id) {
		userService.makeRetired(id);

		return "redirect:/show/teacher";
	}

	@GetMapping("/update/teacher/working/{id}")
	public String makeTeacherWorking(@PathVariable("id") Integer id) {
		userService.makeWorking(id);

		return "redirect:/show/teacher";
	}
}
