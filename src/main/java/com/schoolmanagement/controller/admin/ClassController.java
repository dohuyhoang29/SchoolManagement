package com.schoolmanagement.controller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.schoolmanagement.helper.ClassExcelExporter;
import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.model.request.response.SelectStudentReponse;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.ClassTeacherSubjectService;
import com.schoolmanagement.service.MarkService;
import com.schoolmanagement.service.StudentService;
import com.schoolmanagement.service.SubjectService;
import com.schoolmanagement.service.TeacherService;

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
	private TeacherService teacherService;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private MarkService markService;
	
	// index
	@GetMapping("/show/class")
	  public String classList(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
	    return listClassByPage(model, 1, "className", "asc", "", accountDetails);
	  }

	 @GetMapping("/show/class/{page}")
	  public String listClassByPage(Model model, @PathVariable("page") int currentPage,
	      @Param("sortField") String sortField, @Param("sortDir") String sortDir,
	      @Param("search") String search,
	      @AuthenticationPrincipal AccountDetails accountDetails) {
	    Page<Class> page;
	    if (accountDetails.hasRole("ADMIN")) {
	      page = classService.getAllClassPage(search, currentPage, sortField, sortDir);
	    } else {
	      page = classService.getAllByTeacherId(accountDetails.getId(), currentPage, sortField, sortDir);
	    }
	    int totalPages = page.getTotalPages();
	    Iterable<Class> classList = page.getContent();
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("search", search);
	    model.addAttribute("classList", classList);
	    String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
	    model.addAttribute("reverseSortDir", reverseSortDir);
	    return "/admin/class/class_management";
	  }
	  

	// insert and edit
	@GetMapping("/insert/class")
	public String insertClass(Model model) {
		model.addAttribute("class", new Class());
		model.addAttribute("userList", teacherService.getAllUser());
		model.addAttribute("studentList", studentService.findAllStudentNotClass());

		return "/admin/class/form_class";
	}

	@GetMapping("/insert/class/student")
	public ResponseEntity<List<SelectStudentReponse>> insertClassByStudent(
			@RequestParam("schoolYear") Integer schoolYear, @RequestParam("grade") Integer grade,
			@RequestParam("id") Integer id) {

		List<SelectStudentReponse> listStudent = studentService.findAllStudentNotClassByAdmissionYear(schoolYear, grade,
				id);

		return new ResponseEntity<>(listStudent, HttpStatus.OK);
	}

	@GetMapping("/edit/class/{id}")
	public String EditClass(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("class", classService.getClassById(id));
		model.addAttribute("userList", teacherService.getAllUser());

		return "/admin/class/form_class";
	}

	@PostMapping("/save/class")
	public String saveClass(Model model, @Valid Class aClass, BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		if (aClass.getId() == null) {
			
			if (classService.getClassByClassName(aClass.getClassName()) != null) {
				result.rejectValue("className", "error.class", "Class Name already exist");
			}
		} else {
			
			if (classService.getClassByClassName(aClass.getClassName()) != null && !Objects
					.equals(classService.getClassByClassName(aClass.getClassName()).getId(), aClass.getId())) {
				result.rejectValue("className", "error.class", "Class Name already exist");
			}
		}

		Role role = entityManager.find(Role.class, 3);
		User u = aClass.getUser();
		u.addRole(role);

		if (result.hasErrors()) {
			model.addAttribute("userList", teacherService.getAllUser());

			return "/admin/class/form_class";
		}

//		teacherService.saveUser(u);
		classService.saveClass(aClass);

		if (aClass.getId() == null) {
			redirectAttributes.addFlashAttribute("message", "Add class successfully");
		} else {
			redirectAttributes.addFlashAttribute("message", "Edit class successfully");
		}

		return "redirect:/show/class";
	}

	// details
	@GetMapping("/details/class/{id}")
	public String DetailClass(Model model, @PathVariable("id") int id) {

		return DetailsClassPage(model, id, 1, "");

	}

	@GetMapping("/details/class/{classId}/{page}")
	public String DetailsClassPage(Model model, @PathVariable("classId") int id, @PathVariable("page") int currentPage,
			@Param("search") String search) {
		Iterable<ClassTeacherSubject> cts = classTeacherSubjectService.findAllByClassId(id);

		Page<User> studentPage = studentService.findStudentByClassId(id, search, currentPage);

		int totalPages = studentPage.getTotalPages();

		List<User> listStudent = studentPage.getContent();

		model.addAttribute("classTeacherSubject", cts);
		model.addAttribute("class", classService.getClassById(id));
		model.addAttribute("studentList", listStudent);
		model.addAttribute("subjectList", subjectService.getAllSubject());
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("search", search);

		return "/admin/class/class_details";
	}

	// search manager
	 @GetMapping("/show/class/search")
	  public String SearchClass(Model model, @RequestParam("search") String nameclass,
	      @AuthenticationPrincipal AccountDetails accountDetails) {
	    return listClassByPage(model, 1, "className", "asc", nameclass, accountDetails);
	  }

	// seacher student by class Details
	@GetMapping("/detail/class/student/search/{id}")
	public String searchByNameStudent(Model model, @PathVariable("id") int id, @Param("search") String search) {

		return DetailsClassPage(model, id, 1, search);
	}
	
	// export
	@RequestMapping("/export/studentByClass/{classId}")
	@ResponseBody
	public void exportToExcel(@PathVariable("classId") int classId,HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=students_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<MarkRequest> markRqList = classService.findAverageOneAndTwo(classId);

		Iterable<Subjects> listSubject = subjectService.getAllSubject();
		
		ClassExcelExporter excelExporter = new ClassExcelExporter(markRqList,listSubject, markService);
		
		excelExporter.export(response);
	}

}
