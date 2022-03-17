package com.schoolmanagement.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.service.implement.MarkServiceImp;
import com.schoolmanagement.service.implement.SubjectServiceImp;

@Controller
public class MarkController {
	@Autowired
	private SubjectServiceImp subjectServiceImp;
	
	@Autowired
	private MarkServiceImp markServiceImp;
	
	@GetMapping("/show/mark")
	public String IndexMark(Model model) {
		model.addAttribute("listSubject", subjectServiceImp.findAllSubjectAscId());
		
		
		return "/admin/mark/mark_management";
	}
	
//	@GetMapping("/marklist")
//	public ResponseEntity<List<MarkRequest>> getCompanyList() {
//		List<Mark> mark = new ArrayList<>();
//		if(markServiceImp.findAllMarkByMedium(5).size() >0 ) {
//			mark.addAll(markServiceImp.findAllMarkByMedium(5));
//		}
//		
//		return new ResponseEntity<List<MarkRequest>>(, HttpStatus.OK);
//	}
}
