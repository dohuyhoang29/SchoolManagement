package com.schoolmanagement.controller.teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.service.implement.ClassServiceImp;
import com.schoolmanagement.service.implement.ClassTeacherSubjectServiceImp;
import com.schoolmanagement.service.implement.MarkServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import com.schoolmanagement.service.implement.TeacherServiceImp;
import com.schoolmanagement.service.implement.SubjectServiceImp;

@Controller
public class TeacherAddMarkController {
	@Autowired
	private ClassServiceImp classServiceImpl;
	
	@Autowired
	private StudentServiceImp studentServiceImp;
	
	@Autowired
	private TeacherServiceImp teacherServiceImp;

	@Autowired
	private SubjectServiceImp subjectServiceImp;

	@Autowired
	private ClassTeacherSubjectServiceImp classTeacherSubjectServiceImp;

	@Autowired
	private MarkServiceImp markServiceImp;

	@PreAuthorize("hasAuthority('TEACHER')")
	@GetMapping("/insert/mark/{classid}")
	public String IndexAddMark(Model model, @PathVariable("classid") int id, Authentication authentication) {
		com.schoolmanagement.model.Class c = classServiceImpl.getClassById(id);
		User u = teacherServiceImp.getUserByUsername(authentication.getName());
		List<User> s = studentServiceImp.findAllStudentByClassId(id);
		ClassTeacherSubject cts = classTeacherSubjectServiceImp.findByIdOther(u.getId(), c.getId());
		
		model.addAttribute("cst", cts);
		model.addAttribute("Studentlist", s);

		return "/admin/mark/mark_form";
	}

	@PostMapping("/save/mark")
	public ResponseEntity<Void> SaveMark(@RequestBody List<MarkRequest> mr) {
		for (MarkRequest m : mr) {
			List<Mark> list = new ArrayList<>();
			if(markServiceImp.findByOtherId(m.getSubjects(), m.getStudents(), m.getType(), m.getSemester()) != null) {
				list.addAll(markServiceImp.findByOtherId(m.getSubjects(), m.getStudents(), m.getType(), m.getSemester()));
			}
			for (int i = 0; i < m.getCoefficient().size(); i++) {
				Mark mark = new Mark();
				User user = userServiceImp.findByUserId(m.getCreatedBy());
				Student student = studentServiceImp.getStudentById(m.getStudents());
				Subjects subjects = subjectServiceImp.findBySubjectID(m.getSubjects());

				if(list.size() > 0  && list.get(i).getId() != 0) {
					mark.setId(list.get(i).getId());
				}
				mark.setCreatedBy(user);

				mark.setType(m.getType());
				mark.setSemester(m.getSemester());
				mark.setSubjects(subjects);
				mark.setStudents(student);
				mark.setUpdatedBy(user);
				mark.setUpdatedDate(LocalDate.now());
				mark.setCreatedDate(LocalDate.now());
				mark.setCoefficient(m.getCoefficient().get(i));
				markServiceImp.SaveMarkStudent(mark);
			}


		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
