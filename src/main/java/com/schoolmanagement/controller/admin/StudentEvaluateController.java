package com.schoolmanagement.controller.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.service.MarkService;
import com.schoolmanagement.service.StudentEvaluateService;
import com.schoolmanagement.service.StudentService;

@Controller
public class StudentEvaluateController {
	@Autowired
	private StudentService  studentService;

	@Autowired
	private StudentEvaluateService studentEvaluateService;

	@Autowired
	private MarkService markService;

	@PreAuthorize("hasAnyAuthority('HOMEROOM_TEACHER')")
	@GetMapping("/insert/evaluate/{student-id}/{semester}")
	public String insertStudentEvaluate(@PathVariable("student-id") Integer studentId, Model model,
			@PathVariable("semester") Integer semester) {

		List<Mark> marks = markService.findAllMarkByMedium(studentId, 5, semester);
		StudentEvaluate evaluate = studentEvaluateService.findStudentEvaluateByStudentId(studentId, semester);

		float average = 0;
		
		for (int i = 0; i < marks.size(); i++) {
			
			average += marks.get(i).getCoefficient();
			
			if (i + 1 == marks.size()) {
				average = average / marks.size();
			}

		}

		int status = 0;
		
		if (average >= 8) {
			
			status = 1;
		} else if (average >= 6.5 && average < 8) {
			
			status = 2;
		} else if (average >= 5 && average < 6.5) {
			
			status = 3;
		} else {
			
			status = 4;
		}

		if (evaluate == null) {
			
			User student = studentService.getStudentById(studentId);
			StudentEvaluate studentEvaluate = new StudentEvaluate();

			studentEvaluate.setStudent(student);
			studentEvaluate.setSemester(semester);

			model.addAttribute("student", student);
			model.addAttribute("studentEvaluate", studentEvaluate);
		} else {
			
			model.addAttribute("student", evaluate.getStudent());
			model.addAttribute("studentEvaluate", evaluate);
		}

		model.addAttribute("status", status);
		model.addAttribute("average", Float.valueOf(String.format(Locale.getDefault(), "%.1f", average)));

		return "/admin/student/student_evaluate";
	}

	@PreAuthorize("hasAnyAuthority('HOMEROOM_TEACHER')")
	@PostMapping("/evaluate/save/{id}")
	public String saveStudentEvaluate(@Valid StudentEvaluate studentEvaluate, BindingResult result,
			@AuthenticationPrincipal AccountDetails accountDetails, @PathVariable("id") Integer id, Model model) {
		
		User student = studentService.getStudentById(id);
		User user = accountDetails.getUser();
		
		studentEvaluate.setCreatedDate(LocalDate.now());
		studentEvaluate.setUpdatedDate(LocalDate.now());
		studentEvaluate.setStudent(student);

		if (result.hasErrors()) {
			
			studentEvaluate.setStudent(student);

			model.addAttribute("student", student);
			model.addAttribute("studentEvaluate", studentEvaluate);

			return "/admin/student/student_evaluate";
		}

		if (studentEvaluate.getId() == null) {
			
			studentEvaluate.setCreatedBy(user);
			studentEvaluate.setUpdatedBy(user);
		} else {
			
			studentEvaluate.setUpdatedBy(user);
		}

		studentEvaluateService.saveStudentEvaluate(studentEvaluate);
		
		return "redirect:/show/student";
	}

	@GetMapping("/show/student-class-detail/{id}")
	public String showStudentClassDetail(Model model, @PathVariable("id") Integer id) {

		User studentById = studentService.getStudentById(id);
		List<StudentEvaluate> studentEvaluateList = studentEvaluateService.studentEvaluate(id);
		List<MarkRequest> markList = markService.listAverageSubject(id);

		model.addAttribute("markList", markList);
		model.addAttribute("studentById", studentById);
		model.addAttribute("studentEvaluateList", studentEvaluateList);

		return "/admin/student/student_class_detail";
	}
}
