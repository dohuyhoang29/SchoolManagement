package com.schoolmanagement.controller.teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.service.implement.ClassServiceImp;
import com.schoolmanagement.service.implement.ClassTeacherSubjectServiceImp;
import com.schoolmanagement.service.implement.MarkServiceImp;
import com.schoolmanagement.service.implement.StudentServiceImp;
import com.schoolmanagement.service.implement.SubjectServiceImp;
import com.schoolmanagement.service.implement.TeacherServiceImp;

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
	public String IndexAddMark(Model model, @PathVariable("classid") int id,
			@AuthenticationPrincipal AccountDetails accountDetails) {
		com.schoolmanagement.model.Class c = classServiceImpl.getClassById(id);
		User u = teacherServiceImp.getUserByUsername(accountDetails.getUsername());
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
			if (markServiceImp.findByOtherId(m.getSubjects(), m.getStudents(), m.getType(), m.getSemester()) != null) {
				list.addAll(
						markServiceImp.findByOtherId(m.getSubjects(), m.getStudents(), m.getType(), m.getSemester()));
			}
			User user = teacherServiceImp.findByUserId(m.getCreatedBy());
			User student = studentServiceImp.getStudentById(m.getStudents());
			Subjects subjects = subjectServiceImp.findBySubjectID(m.getSubjects());

			for (int i = 0; i < m.getCoefficient().size(); i++) {
				Mark mark = new Mark();
				if (list.size() > 0 && list.get(i).getId() != 0) {
					mark.setId(list.get(i).getId());
				}
				mark.setCreatedDate(LocalDate.now());
				mark.setCreatedBy(user);
				mark.setType(m.getType());
				mark.setSemester(m.getSemester());
				mark.setSubjects(subjects);
				mark.setStudents(student);
				mark.setUpdatedBy(user);
				mark.setUpdatedDate(LocalDate.now());
				mark.setCoefficient(m.getCoefficient().get(i));

				markServiceImp.SaveMarkStudent(mark);

			}

			if (markServiceImp.findByStudentSubject(m.getSubjects(), m.getStudents(), m.getSemester()) != null) {
				Mark marks = new Mark();
				List<Mark> listMarkSubject = new ArrayList<>();
				listMarkSubject.addAll(markServiceImp.findByStudentSubject(m.getSubjects(), m.getStudents(), m.getSemester()));
				if (listMarkSubject.size() > 7) {
					Mark mediumscore = markServiceImp.findMediumScore(m.getSubjects(), m.getStudents(), 5,
							m.getSemester());
					if (mediumscore != null && mediumscore.getId() != 0) {
						marks.setId(mediumscore.getId());
					}

					marks.setCreatedDate(LocalDate.now());
					marks.setCreatedBy(user);
					marks.setSemester(m.getSemester());
					marks.setSubjects(subjects);
					marks.setStudents(student);
					marks.setUpdatedBy(user);
					marks.setUpdatedDate(LocalDate.now());
					marks.setType(5);

					if (listMarkSubject.size() > 0) {
						float areaMarksubject = 0;
						for (int j = 0; j < listMarkSubject.size(); j++) {

							if (listMarkSubject.get(j).getType() == 1) {
								areaMarksubject += listMarkSubject.get(j).getCoefficient();
							}

							if (listMarkSubject.get(j).getType() == 2) {
								areaMarksubject += listMarkSubject.get(j).getCoefficient();
							}

							if (listMarkSubject.get(j).getType() == 3) {
								areaMarksubject += (listMarkSubject.get(j).getCoefficient() * 2);
							}

							if (listMarkSubject.get(j).getType() == 4) {
								areaMarksubject += (listMarkSubject.get(j).getCoefficient() * 3);
							}

							if (j == 7) {
								areaMarksubject = areaMarksubject / 12;
							}
						}

						marks.setCoefficient(
								Float.valueOf(String.format(Locale.getDefault(), "%.1f", areaMarksubject)));

						markServiceImp.SaveMarkStudent(marks);

					}
				}

			}
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
