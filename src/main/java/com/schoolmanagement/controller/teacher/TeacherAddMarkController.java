package com.schoolmanagement.controller.teacher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.CoefficientRequest;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.service.ClassService;
import com.schoolmanagement.service.ClassTeacherSubjectService;
import com.schoolmanagement.service.MarkService;
import com.schoolmanagement.service.StudentService;
import com.schoolmanagement.service.SubjectService;
import com.schoolmanagement.service.UserService;

@Controller
public class TeacherAddMarkController {
	@Autowired
	private ClassService classService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UserService userService;

	@Autowired
	private ClassTeacherSubjectService classTeacherSubjectService;

	@Autowired
	private MarkService markService;

	
	@PreAuthorize("hasAuthority('TEACHER')")
	@GetMapping("/insert/mark/{classid}")
	public String IndexAddMark(Model model, @PathVariable("classid") int id,
			@AuthenticationPrincipal AccountDetails accountDetails) {

		User teacher = userService.getUserByUsername(accountDetails.getUsername());

		List<ClassTeacherSubject> cts = classTeacherSubjectService.findByIdOther(teacher.getId(), id);

		return IndextAddMarkPageStudent(model, 1, id, 1, 1,cts.get(0).getSubjects().getId() ,accountDetails);
	}

	@GetMapping("/insert/mark/show/{page}/{s0}/{type}/{s1}/{semester}/{s2}/{classId}/{s3}/{subjectId}")
	public String showByType(Model model, @PathVariable("page") int page,
			@PathVariable("type") int type,@PathVariable("subjectId") int subjectId ,
			@PathVariable("semester") int semester, @PathVariable("classId") int classId,
			@AuthenticationPrincipal AccountDetails accountDetails) {

		return IndextAddMarkPageStudent(model, page, classId, type, semester, subjectId, accountDetails);
	}

	@GetMapping("/page/insert/mark/{page}")
	public String IndextAddMarkPageStudent(Model model, @PathVariable("page") int currentPage,
			@Param("classId") int classId, @Param("type") int type,
			@Param("semester") int semester,@Param("subjectId") int subjectId ,
			@AuthenticationPrincipal AccountDetails accountDetails) {
		Page<User> studentPages = studentService.findAllStudentByClassId(classId, currentPage);

		int totalPages = studentPages.getTotalPages();

		List<User> students = studentPages.getContent();

		Class clas = classService.getClassById(classId);
		
		User teacher = userService.getUserByUsername(accountDetails.getUsername());
		
		List<ClassTeacherSubject> cts = classTeacherSubjectService.findByIdOther(teacher.getId(), classId);
		
		List<MarkRequest> markRq = new ArrayList<>();
		
		Subjects subjects = subjectService.findBySubjectID(subjectId);
		
		List<Integer> success = new ArrayList<>();
		
		List<Mark> lengthMarkStudent = new ArrayList<>();
		
		for (User u : students) {
			
			MarkRequest markRequest = new MarkRequest();
			
			markRequest.setStudentId(u.getId());
			markRequest.setStudentName(u.getFullName());
			markRequest.setDateOfBirth(u.getDob());
			markRequest.setAddress(u.getAddress());

			List<MarkRequest> listStudentHasMark = studentService.findAllStudentHasMark(type, semester,
					subjects.getId(), clas.getId());

			if (listStudentHasMark != null && listStudentHasMark.size() > 0) {
				
				List<CoefficientRequest> marks = new ArrayList<>();

				for (MarkRequest mr : listStudentHasMark) {
					
					CoefficientRequest cr = new CoefficientRequest();
					
					if (markRequest.getStudentId() == mr.getStudentId()) {
						cr.setId(mr.getMarkId());
						cr.setCoeffi(mr.getCoefficient());
						marks.add(cr);
					}
				}
				
				markRequest.setCoefficients(marks);

			}
			markRq.add(markRequest);
			if (markService.findByStudentSubject(subjects.getId(), u.getId(),1) != null) {
				
				lengthMarkStudent = markService.findByStudentSubject(subjects.getId(), u.getId(),1);
				
				if(lengthMarkStudent.size() > 7) {
					success.add(1);
				}
			}
		}

		int result = 0;
		if(success.size() == students.size()) {
			
			result = 1;
		}

		model.addAttribute("result", result);
		model.addAttribute("teacher", teacher);
		model.addAttribute("class", clas);
		model.addAttribute("cts", cts);
		model.addAttribute("Studentlist", students);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("marklist", markRq);
		model.addAttribute("type", type);
		model.addAttribute("semester", semester);
		model.addAttribute("subjects", subjects);

		return "/admin/mark/mark_form";
	}

//

	@PostMapping("/save/mark")
	public ResponseEntity<Integer> ChangeAndSaveMark(@RequestBody MarkRequest markRequest) {
		
		Mark mark = markService.SaveMark(markRequest);
		
		return new ResponseEntity<Integer>(mark.getId(), HttpStatus.OK);
	}

}
