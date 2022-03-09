package com.schoolmanagement.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.schoolmanagement.model.ClassTeacherSubejct;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.ClassTeacherSubjectRequest;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.ClassTeacherSubjectService;
import com.schoolmanagement.service.StudentService;
import com.schoolmanagement.service.SubjectService;
import com.schoolmanagement.service.UserService;

@Controller
public class ClassTeacherSubjectController {
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
	
	@GetMapping("/update/teacher/{id}")
	public String updateTeacher(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("class", classService.getClassById(id));
		model.addAttribute("studentList", studentService.getAllStudent());
		model.addAttribute("subjectList", subjectService.getAllSubject());

		return "/admin/class/update_teacher";
	}
	
	
	@PostMapping("/company/save")
	public ResponseEntity<Void> saveOrUpdateCompany(@RequestBody ClassTeacherSubjectRequest dr) {
		ClassTeacherSubejct cts = new ClassTeacherSubejct();
		User users = userService.findbyUserid(dr.getUserid());
		Subjects subjects = subjectService.findBySubjectID(dr.getSubjectId());
		com.schoolmanagement.model.Class class_u = classService.getClassById(dr.getClassid());
		ClassTeacherSubejct e = classTeacherSubjectService.FindbyIdOrther(subjects.getId(), class_u.getId());
		if(e != null && e.getId() != 0) {
			cts.setId(e.getId());
			
		}
		
		cts.setSubjects(subjects);
		cts.setTheClass(class_u);
		cts.setUsers(users);
		classTeacherSubjectService.Save(cts);
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
