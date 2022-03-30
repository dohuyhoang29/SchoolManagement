package com.schoolmanagement.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.service.MarkService;
import com.schoolmanagement.service.StudentService;
import com.schoolmanagement.service.SubjectService;

@Controller
public class MarkController {
	@Autowired
	private SubjectService subjectService;

	@Autowired
	private MarkService markService;

	@Autowired
	private StudentService studentService;

	@GetMapping("/show/mark")
	public String IndexMark(Model model) {
		Iterable<User> stduents = studentService.getAllStudent();
		List<Mark> marks = new ArrayList<>();
		
		try {
			for (User student : stduents) {
				
				Mark mark = new Mark();
				float medium = 0;
				
				if (markService.Average(student.getId(), 1) > 0
						&& markService.Average(student.getId(), 2) > 0 ) {
					
					float medium1 = markService.Average(student.getId(), 1);
					float medium2 = markService.Average(student.getId(), 2);
					
					medium = (medium1 + (medium2 * 2)) / 3;
						
					mark.setCoefficient(Float.valueOf(String.format(Locale.getDefault(), "%.2f" , medium)));
				
				}else {
					
					mark.setCoefficient(medium);
				}
				
				
				mark.setStudents(student);
				marks.add(mark);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		model.addAttribute("markList", marks);
		model.addAttribute("listSubject", subjectService.findAllSubjectAscId());
		
		return "/admin/mark/mark_management";
	}

	
	@GetMapping("/detail/mark/{studentId}")
	public String MarkDetails(Model model , @PathVariable("studentId") int studentId) {
		
		User student = studentService.getStudentById(studentId);
		
		List<MarkRequest> listRquest = subjectService.findByStudent(studentId);
		
		model.addAttribute("student", student);
		model.addAttribute("listRquest", listRquest);
		
		return "/admin/mark/mark_details";
	}
}
