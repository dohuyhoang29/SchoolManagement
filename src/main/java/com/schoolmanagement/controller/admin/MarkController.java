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
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.SubjectRequest;
import com.schoolmanagement.service.implement.MarkServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import com.schoolmanagement.service.implement.SubjectServiceImp;

@Controller
public class MarkController {
	@Autowired
	private SubjectServiceImp subjectServiceImp;

	@Autowired
	private MarkServiceImp markServiceImp;

	@Autowired
	private StudentServiceImp studentServiceImp;

	@GetMapping("/show/mark")
	public String IndexMark(Model model) {

		Iterable<User> stduents = studentServiceImp.getAllStudent();
		List<Mark> marks = new ArrayList<>();
		try {
			for (User student : stduents) {
				Mark mark = new Mark();
				if (markServiceImp.findAllMarkByMedium(student.getId(), 5, 1).size() > 0
						&& markServiceImp.findAllMarkByMedium(student.getId(), 5, 2).size() > 0) {
					List<Mark> marks1 = markServiceImp.findAllMarkByMedium(student.getId(), 5, 1);
					List<Mark> marks2 = markServiceImp.findAllMarkByMedium(student.getId(), 5, 2);
					float medium = 0;

					for (int i = 0; i < marks1.size(); i++) {

						medium += ((marks1.get(i).getCoefficient() + (marks2.get(i).getCoefficient() * 2)) / 3);

						if (i+1 == (marks1.size())) {
							medium = medium / marks1.size();
							break;
						}

						mark.setId(marks1.get(i).getId());
					}
					mark.setCoefficient(Float.valueOf(String.format(Locale.getDefault(), "%.1f" , medium)));
				}
				mark.setStudents(student);
				marks.add(mark);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		model.addAttribute("markList", marks);
		model.addAttribute("listSubject", subjectServiceImp.findAllSubjectAscId());
		
		return "/admin/mark/mark_management";
	}

	
	@GetMapping("/detail/mark/{studentId}")
	public String MarkDetails(Model model , @PathVariable("studentId") int studentId) {
		User student = studentServiceImp.getStudentById(studentId);
		Iterable<Subjects> subjects  = subjectServiceImp.getAllSubject();
		
		List<SubjectRequest>  list = subjectServiceImp.findByStudent(studentId , 1 ,1);
		
		model.addAttribute("student", student);
		model.addAttribute("subjects", subjects);
		
		
		
		return "/admin/mark/mark_details";
	}
}
