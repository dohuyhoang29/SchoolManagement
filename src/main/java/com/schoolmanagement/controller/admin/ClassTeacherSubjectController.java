package com.schoolmanagement.controller.admin;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.ClassTeacherSubjectRequest;
import com.schoolmanagement.service.implement.ClassServiceImp;
import com.schoolmanagement.service.implement.ClassTeacherSubjectServiceImp;
import com.schoolmanagement.service.implement.SubjectServiceImp;
import com.schoolmanagement.service.implement.TeacherServiceImp;

@Controller
public class ClassTeacherSubjectController {
	@Autowired
	private ClassServiceImp classServiceImp;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private SubjectServiceImp subjectServiceImp;

	@Autowired
	private ClassTeacherSubjectServiceImp classTeacherSubjectServiceImp;

	@Autowired
	private TeacherServiceImp teacherServiceImp;
	
	@PostMapping("/classTeacherSubject/save")
	public ResponseEntity<Void> saveOrUpdateClassTeacherSubject(@RequestBody List<ClassTeacherSubjectRequest> dr) {
		for(int i = 0 ; i < dr.size() ; i++) {
			ClassTeacherSubject cts = new ClassTeacherSubject();
			User users = teacherServiceImp.findByUserId(dr.get(i).getUserid());
			Subjects subjects = subjectServiceImp.findBySubjectID(dr.get(i).getSubjectId());
			com.schoolmanagement.model.Class class_u = classServiceImp.getClassById(dr.get(i).getClassid());
			ClassTeacherSubject e = classTeacherSubjectServiceImp.findByIdOther(subjects.getId(), class_u.getId());
			
			class_u.setClassName(dr.get(i).getClassReq().getClassName());
			class_u.setGrade(dr.get(i).getClassReq().getGrade());
			class_u.setSchoolYear(dr.get(i).getClassReq().getSchoolYear());
			class_u.setUser(teacherServiceImp.findByUserId(dr.get(i).getClassReq().getUserId()));
			class_u.setUpdatedDate(LocalDateTime.now());
			
			
			if(e != null && e.getId() != 0) {
				cts.setId(e.getId());
			}
			
			cts.setSubjects(subjects);
			cts.setTheClass(class_u);
			
			
			
			cts.setUsers(users);
			Role role = entityManager.find(Role.class, 3);
			User u = class_u.getUser();
			u.addRole(role);
			
			teacherServiceImp.saveUser(u);
			classServiceImp.saveClass(class_u);
			classTeacherSubjectServiceImp.Save(cts);
			
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PostMapping("/classTeacherSubject/change")
	public ResponseEntity<Void> ChangeClassTeacherSubject(@RequestBody List<ClassTeacherSubjectRequest> dr) {
		for(int i = 0 ; i < dr.size() ; i++) {
			ClassTeacherSubject cts = new ClassTeacherSubject();
			User users = teacherServiceImp.findByUserId(dr.get(i).getUserid());
			Subjects subjects = subjectServiceImp.findBySubjectID(dr.get(i).getSubjectId());
			com.schoolmanagement.model.Class class_u = classServiceImp.getClassById(dr.get(i).getClassid());
			ClassTeacherSubject e = classTeacherSubjectServiceImp.findByIdOther(subjects.getId(), class_u.getId());
			
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
