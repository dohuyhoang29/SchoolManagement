package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.service.ClassServiceImp;
import com.schoolmanagement.service.ClassTeacherSubjectServiceImp;
import com.schoolmanagement.service.StudentServiceImp;
import com.schoolmanagement.service.SubjectServiceImp;
import com.schoolmanagement.service.UserServiceImp;

import java.util.List;

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

@Controller
public class ClassController {

	@Autowired
	private ClassServiceImp classServiceImp;

	@Autowired
	private StudentServiceImp studentServiceImp;

	@Autowired
	private SubjectServiceImp subjectServiceImp;

	@Autowired
	private ClassTeacherSubjectServiceImp classTeacherSubjectServiceImp;

	@Autowired
	private UserServiceImp userServiceImp;
//index
	@GetMapping("/show/class")
	public String classList(Model model) {
		
		return listClassByPage(model , 1,"");
	}

	@GetMapping("/show/class/page/{page}")
	public String listClassByPage(Model model, @PathVariable("page") int currentPage, @Param("search") String search) {

		Page<Class> page = classServiceImp.getAllClassPage(search, currentPage);
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
		model.addAttribute("userList", userServiceImp.getAllUser());

		return "/admin/class/form_class";
	}

	@GetMapping("/edit/class/{id}")
	public String EditClass(@PathVariable("id") int id, Model model) {
		model.addAttribute("class", classServiceImp.getClassById(id));
		model.addAttribute("userList", userServiceImp.getAllUser());

		return "/admin/class/form_class";
	}

	@PostMapping("/save/class")
	public String saveClass(Class aClass, BindingResult result) {
		if (result.hasErrors()) {
			return "/admin/class/form_class";
		}
		classServiceImp.saveClass(aClass);

		return "redirect:/show/class";
	}
//details
	@GetMapping("/details/class/{id}")
	public String DetailClass(Model model, @PathVariable("id") int id) {
		
		return DetailsClassPage(model, id, 1 ,"");
		
	}

	@GetMapping("/details/class/{classId}/{id}")
	public String DetailsClassPage(Model model , @PathVariable("classId") int id 
			, @PathVariable("id") int currentPage , @Param("search") String search) {
		Iterable<ClassTeacherSubject> cts = classTeacherSubjectServiceImp.findAllByClassId(id);
		
		Page<Student> studentPage = studentServiceImp.findStudentByClassId(id,search, currentPage);
		
		int totalPages = studentPage.getSize();
		
		List<Student> listStudent = studentPage.getContent();
		
		model.addAttribute("classTeacherSubject", cts);
		model.addAttribute("class", classServiceImp.getClassById(id));
		model.addAttribute("studentList", listStudent);
		model.addAttribute("subjectList", subjectServiceImp.getAllSubject());
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("search",search);
		
		return "/admin/class/class_details";
	}
	
// search manager
	@GetMapping("/show/class/search")
	public String SearchClass(Model model , @RequestParam("search") String nameclass) {
		
		return listClassByPage(model, 1, nameclass);
	}
	//seacher student by class Details
	@GetMapping("/detail/class/student/search/{id}")
	public String searchByNameStudent(Model model ,@PathVariable("id") int id  , @Param("search") String search) {
		
		return DetailsClassPage(model, id, 1, search);
	}
}
