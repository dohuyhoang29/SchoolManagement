package com.schoolmanagement.controller.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.implement.ClassServiceImp;
import com.schoolmanagement.service.implement.ClassTeacherSubjectServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import com.schoolmanagement.service.implement.TeacherServiceImp;

@Controller
public class TeacherAddMarkController {
	@Autowired
	private ClassServiceImp classServiceImpl;
	
	@Autowired
	private StudentServiceImp studentServiceImp;
	
	@Autowired
	private TeacherServiceImp teacherServiceImp;
	
	@Autowired
	private ClassTeacherSubjectServiceImp classTeacherSubjectServiceImp;
	
	@GetMapping("/insert/mark/{classid}/{value}")
	public String IndexAddMark(Model model , @PathVariable("classid") int id , Authentication authentication ,@PathVariable("value") int type) {
		com.schoolmanagement.model.Class c = classServiceImpl.getClassById(id);	
		User u = teacherServiceImp.getUserByUsername(authentication.getName());
		List<User> s = studentServiceImp.findAllStudentByClassId(id);
		
		ClassTeacherSubject cts = classTeacherSubjectServiceImp.findByIdOther(u.getId(), c.getId());
		
		model.addAttribute("cst", cts);
		model.addAttribute("Studentlist", s);
		model.addAttribute("type", type);
		
		return "/admin/mark/mark_form";
	}
	
	
	@PostMapping("/Save/mark")
	public ResponseEntity<Void> SaveMark(){
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
