package com.schoolmanagement.controller.users;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.service.implement.ClassTeacherSubjectServiceImp;
import com.schoolmanagement.service.implement.MarkServiceImp;
import com.schoolmanagement.service.implement.StudentEvaluateServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import com.schoolmanagement.service.implement.SubjectServiceImp;

@Controller
public class StudentShowMarkController {
	@Autowired
	private StudentEvaluateServiceImp evaluateServiceImp;
	
	@Autowired
	private StudentServiceImp studentServiceImp;
	
	@Autowired
	private MarkServiceImp markServiceImp;
	
	@Autowired
	private SubjectServiceImp subjectServiceImp;
	
	@Autowired
	private ClassTeacherSubjectServiceImp classTeacherSubjectServiceImp;
	
	@GetMapping("/student/show/mark")
	public String IndexStudent(Model model , @AuthenticationPrincipal AccountDetails accountDetails) {
		User students = studentServiceImp.findStudentByUserName(accountDetails.getUsername());
		StudentEvaluate studentEvaluate1 = evaluateServiceImp.findStudentEvaluateByStudentId(students.getId(), 1);
		StudentEvaluate studentEvaluate2 = evaluateServiceImp.findStudentEvaluateByStudentId(students.getId(), 2);
		
		List<Mark> mark1 = markServiceImp.findAllMarkByMedium(students.getId(), 5, 1);
		List<Mark> mark2 = markServiceImp.findAllMarkByMedium(students.getId(), 5, 2);
		List<MarkRequest> markRequests1 = subjectServiceImp.findByStudentSemester(students.getId(), 1);
		List<MarkRequest> markRequests2 = subjectServiceImp.findByStudentSemester(students.getId(), 2);	
		List<Subjects> listSubject = (List<Subjects>)subjectServiceImp.getAllSubject();
		Iterable<ClassTeacherSubject>  cts = classTeacherSubjectServiceImp.findAllByClassId(students.getUserInfo().getAClass().getId());
		Iterable<User> list = studentServiceImp.getAllStudent();
		
		float average1 = 0;
		float average2 = 0;
		
		if(listSubject.size() == mark1.size()) {
			for(int i = 0 ; i < mark1.size() ; i ++) {
				average1 += mark1.get(i).getCoefficient();
				if(i+ 1 == mark1.size()) {
					average1 = average1/mark1.size();
				}	
			}
		}
		
		if(listSubject.size() == mark2.size()) {
			for(int i = 0 ; i < mark2.size() ; i ++) {
				average2 += mark2.get(i).getCoefficient();
				if(i+ 1 == mark2.size()) {
					average2 = average2/mark2.size();
				}	
			}
		}
		
		model.addAttribute("classStudentList", list);
		model.addAttribute("classTeacherSubject", cts);
		model.addAttribute("class", students.getUserInfo().getAClass());
		model.addAttribute("markList1", markRequests1);
		model.addAttribute("markList2", markRequests2);
		model.addAttribute("average1",Float.valueOf(String.format(Locale.getDefault(), "%.2f", average1)));
		model.addAttribute("average2", Float.valueOf(String.format(Locale.getDefault(), "%.2f", average2)));
		model.addAttribute("studentEvaluate1", studentEvaluate1);
		model.addAttribute("studentEvaluate2", studentEvaluate2);
		
		return "/user/student_show";
	}

	
	@GetMapping("/data/table")
	public ResponseEntity<List<User>> handList(){
		
	
		List<User> list = (List<User>)studentServiceImp.getAllStudent();
		return new ResponseEntity<List<User>>(list , HttpStatus.OK);
	}
}
