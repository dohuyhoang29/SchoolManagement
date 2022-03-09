package com.schoolmanagement.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.ClassTeacherSubjectRequest;
import com.schoolmanagement.service.ClassServiceImp;
import com.schoolmanagement.service.ClassTeacherSubjectServiceImp;
import com.schoolmanagement.service.StudentServiceImp;
import com.schoolmanagement.service.SubjectServiceImp;
import com.schoolmanagement.service.UserServiceImp;

@Controller
public class ClassTeacherSubjectController {
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
	
	@GetMapping("/update/teacher/{id}")
	public String updateTeacher(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("class", classServiceImp.getClassById(id));
		model.addAttribute("studentList", studentServiceImp.getAllStudent());
		model.addAttribute("subjectList", subjectServiceImp.getAllSubject());

		return "/admin/class/update_teacher";
	}
	
	
	@PostMapping("/company/save")
	public ResponseEntity<Void> saveOrUpdateCompany(@RequestBody ClassTeacherSubjectRequest dr) {
		ClassTeacherSubject cts = new ClassTeacherSubject();
		User users = userServiceImp.findbyUserid(dr.getUserid());
		Subjects subjects = subjectServiceImp.findBySubjectID(dr.getSubjectId());
		com.schoolmanagement.model.Class class_u = classServiceImp.getClassById(dr.getClassid());
		ClassTeacherSubject e = classTeacherSubjectServiceImp.findByIdOther(subjects.getId(), class_u.getId());
		if(e != null && e.getId() != 0) {
			cts.setId(e.getId());
			
		}
		
		cts.setSubjects(subjects);
		cts.setTheClass(class_u);
		cts.setUsers(users);
		classTeacherSubjectServiceImp.Save(cts);
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
