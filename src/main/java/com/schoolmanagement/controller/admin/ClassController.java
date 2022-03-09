package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.ClassTeacherSubejct;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.ClassTeacherSubjectService;
import com.schoolmanagement.service.StudentService;
import com.schoolmanagement.service.SubjectService;
import com.schoolmanagement.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassController {

	@Autowired
	private ClassService classService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ClassTeacherSubjectService classTeacherSubjectService;

	@Autowired
	private UserService userService;
//index
	@GetMapping("/show/class")
	public String classList(Model model) {
		
		return listClassByPage(model , 1,"");
	}

	@GetMapping("/show/class/page/{page}")
	public String listClassByPage(Model model, @PathVariable("page") int currentPage, @Param("search") String search) {

		Page<Class> page = classService.getAllClassPage(search, currentPage);
		int totalPages = page.getTotalPages();
		Iterable<Class> classList = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("search", search);
		model.addAttribute("classList", classList);

		return "/admin/class/class_management";
	}
// insert and edit
	@GetMapping("/insert/class")
	public String insertClass(Model model) {
		model.addAttribute("class", new Class());
		model.addAttribute("userList", userService.getAllUser());

		return "/admin/class/form_class";
	}

	@GetMapping("/edit/class/{id}")
	public String EditClass(@PathVariable("id") int id, Model model) {
		model.addAttribute("class", classService.getClassById(id));
		model.addAttribute("userList", userService.getAllUser());

		return "/admin/class/form_class";
	}

	@PostMapping("/save/class")
	public String saveClass(Class aClass, BindingResult result) {
		if (result.hasErrors()) {
			return "/admin/class/form_class";
		}
		classService.saveClass(aClass);

		return "redirect:/show/class";
	}
//details
	@GetMapping("/details/class/{id}")
	public String DetailClass(Model model, @PathVariable("id") int id) {
		Iterable<ClassTeacherSubejct> cts = classTeacherSubjectService.findAllByClassId(id);	
		model.addAttribute("classTeacherSubject", cts);
		model.addAttribute("class", classService.getClassById(id));
		model.addAttribute("studentList", studentService.getAllStudent());
		model.addAttribute("subjectList", subjectService.getAllSubject());
		
		return "/admin/class/class_details";
		
	}

// search
	@GetMapping("/show/class/search")
	public String SearchClass(Model model , @RequestParam("search") String nameclass) {
		
		return listClassByPage(model, 1, nameclass);
	}
	//seacher student by class
	
}
