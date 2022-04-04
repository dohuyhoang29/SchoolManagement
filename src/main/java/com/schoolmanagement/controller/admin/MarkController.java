package com.schoolmanagement.controller.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
	public String MarkIndex(Model model) {
		
		
		return PagingMarkIndex(model ,1, "",2022, 10 , 1 );
	}
	
	@GetMapping("/show/mark/page/{pageNumber}")
	public String PagingMarkIndex(Model model , @PathVariable("pageNumber") int currentPage,
			@Param("studentName") String studentName , 
			@Param("schoolYear") int schoolYear , @Param("grade") int grade 
			, @Param("average") Integer average ) {
		
		int year = LocalDate.now().getYear();
		List<Integer> listYear = new ArrayList<>();
		
		for(int i = 2000 ; i <= year ; i++) {
			listYear.add(i);
		}
		
		Page<User> pageUserMark = studentService.PagingMarkIndex(studentName, schoolYear , grade , average,currentPage);
		
		int totalPages = pageUserMark.getTotalPages();
		
		List<User> listUser = pageUserMark.getContent();
		
		List<Mark> list = markService.IndexMarkView(listUser);
		
		model.addAttribute("markList", list);
		model.addAttribute("grade", grade);
		model.addAttribute("studentName", studentName);
		model.addAttribute("schoolYear", schoolYear);
		model.addAttribute("average", average);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("listYear", listYear);
		
		return "/admin/mark/mark_management";
	}
	
	
	@GetMapping("/detail/mark/{studentId}")
	public String MarkDetails(Model model, @PathVariable("studentId") int studentId) {

		User student = studentService.getStudentById(studentId);

		List<MarkRequest> listRquest = subjectService.findByStudent(studentId);

		model.addAttribute("student", student);
		model.addAttribute("listRquest", listRquest);

		return "/admin/mark/mark_details";
	}
	
	
	@GetMapping("/show/mark/search")
	public String SearchMarkList(Model model , @Param("studentName") String studentName , 
			@Param("schoolYear") int schoolYear , @Param("grade") int grade 
			, @Param("average") Integer average) {
		
		return PagingMarkIndex(model ,1, studentName,schoolYear, grade , average);
	}
}
