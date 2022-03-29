package com.schoolmanagement.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.ClassTeacherSubjectRequest;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.ClassTeacherSubjectService;
import com.schoolmanagement.service.SubjectService;
import com.schoolmanagement.service.TeacherService;

@Controller
public class ClassTeacherSubjectController {
	@Autowired
	private ClassService classService;

	
	@Autowired
	private SubjectService subjectServiceImp;

	@Autowired
	private ClassTeacherSubjectService classTeacherSubjectServiceImp;

	@Autowired
	private TeacherService teacherServiceImp;
	

	@GetMapping("/updateClassTeacher/class/{id}")
	public String EditClass(@PathVariable("id") int id, Model model) {
		model.addAttribute("userList", teacherServiceImp.getAllUser());
		model.addAttribute("class", classService.getClassById(id));
		model.addAttribute("subjectList", subjectServiceImp.getAllSubject());

		return "/admin/class/update_classTeacher";
	}

	
	
	@PostMapping("/classTeacherSubject/change")
	public ResponseEntity<Void> ChangeClassTeacherSubject(@RequestBody List<ClassTeacherSubjectRequest> dr) {
		for(int i = 0 ; i < dr.size() ; i++) {
			ClassTeacherSubject cts = new ClassTeacherSubject();
			User users = teacherServiceImp.findByUserId(dr.get(i).getUserid());
			Subjects subjects = subjectServiceImp.findBySubjectID(dr.get(i).getSubjectId());
			Class class_u = classService.getClassById(dr.get(i).getClassid());
			ClassTeacherSubject e = classTeacherSubjectServiceImp.findById(users.getId(), class_u.getId(), subjects.getId());
			
			if(e != null && e.getId() != 0) {
				cts.setId(e.getId());
			}
			
			cts.setSubjects(subjects);
			cts.setTheClass(class_u);
			cts.setUsers(users);
			
			classTeacherSubjectServiceImp.Save(cts);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
