package com.schoolmanagement.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.implement.SubjectServiceImp;
import com.schoolmanagement.service.implement.UserServiceImp;

@Controller
public class SubjectController {

	@Autowired
	private SubjectServiceImp subjectServiceImp;

	@Autowired
	private UserServiceImp userServiceImp;

	// index
	@GetMapping("/show/subjects")
	public String SubjectManage(Model model) {

		return listSubjectByPage(model, 1, "id", "asc", "");
	}

	// index page and search
	@GetMapping("/show/subjects/page/{page}")
	public String listSubjectByPage(Model model, @PathVariable("page") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("search") String search) {
		Page<Subjects> page;
		if (search.equalsIgnoreCase("")) {
			page = subjectServiceImp.getAllSubjectByPage(currentPage, sortField, sortDir);
		} else {
			page = subjectServiceImp.findSubjectByName(search, currentPage, sortField, sortDir);
		}
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		Iterable<Subjects> listSubject = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("search", search);
		model.addAttribute("listSubject", listSubject);

		String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);

		return "/admin/subjects/subjects_management";
	}

	@GetMapping("/show/subjects/search")
	public String searchSubject(Model model, @RequestParam(value = "search") String search) {
		return listSubjectByPage(model, 1, "subjectName", "asc", search);
	}

	// New
	@GetMapping("/insert/subjects")
	public String SubjectIndexAdd(Model model) {
		Iterable<User> listUser = userServiceImp.getAllUser();
		
		
		model.addAttribute("listUser", listUser);
		model.addAttribute("subjects", new Subjects());
		model.addAttribute("listSubject", subjectServiceImp.getAllSubject());

		return "/admin/subjects/form_subject";
	}

	// Edit
	@GetMapping("/edit/subjects/{id}")
	public String SubjectEdit(Model model, @PathVariable("id") int id) {
		Subjects subjects = subjectServiceImp.findBySubjectID(id);
		Iterable<User> listUser = userServiceImp.getAllUser();
		model.addAttribute("listUser", listUser);
		model.addAttribute("subjects", subjects);

		return "/admin/subjects/update_subjects";
	}

	// Post add , save
	@PostMapping("/subjects/save")
	public String SaveSubject(@Valid Subjects subjects, BindingResult result) {

		if (result.hasErrors()) {
			return "/admin/subjects/form_subject";
		}

		subjectServiceImp.SaveSubject(subjects);
		return "redirect:/show/subjects";
	}

	@PostMapping("/subjects/update/{id}")
	public String updateSubjects(@PathVariable("id") Integer id, @Valid Subjects subjects, BindingResult result) {
		if (result.hasErrors()) {
			subjects.setId(id);
			return "/admin/subjects/update_subjects";
		}

		subjectServiceImp.SaveSubject(subjects);

		return "redirect:/show/subjects";
	}

	// Detail
	@GetMapping("/show/subjects/details/{id}")
	public String DetailSubject(Model model, @PathVariable("id") int id) {
		Subjects subjects = subjectServiceImp.findBySubjectID(id);
		model.addAttribute("subject", subjects);
		model.addAttribute("listUser", subjects.getUsers());

		return "/admin/subjects/subjects_details";
	}

	@GetMapping("/show/subjects/details/{id}/{userid}")
	public String DetailSubjectDelete(Model model, @PathVariable("id") int id, @PathVariable("userid") int userid) {
		Subjects subjects = subjectServiceImp.findBySubjectID(id);
		User user = userServiceImp.findByUserId(userid);

		subjects.getUsers().remove(user);

		subjectServiceImp.SaveSubject(subjects);

		return "redirect:/show/subjects/details/" + subjects.getId();
	}
}
