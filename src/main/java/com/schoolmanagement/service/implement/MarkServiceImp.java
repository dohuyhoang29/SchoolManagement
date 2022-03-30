package com.schoolmanagement.service.implement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.CoefficientRequest;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.repositories.MarkRepositories;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.repositories.SubjectRepositories;
import com.schoolmanagement.repositories.TeacherRepositories;
import com.schoolmanagement.service.MarkService;

@Service
public class MarkServiceImp implements MarkService {
	@Autowired
	private MarkRepositories markRepositories;

	@Autowired
	private SubjectRepositories subjectRepositories;

	@Autowired
	private TeacherRepositories teacherRepositories;
	
	@Autowired
	private StudentRepositories studentRepositories;
	
	
	@Override
	public void SaveMarkStudent(Mark mark) {

		markRepositories.save(mark);
	}

	@Override
	public List<Mark> findByOtherId(int subjectId, int studentId, int type, int semester) {

		return markRepositories.findByClassSubjectId(subjectId, studentId, type, semester);
	}

	@Override
	public List<Mark> findByStudentSubject(int subjectId, int studentId, int semester) {

		return markRepositories.finByStudentSubject(subjectId, studentId, semester);
	}

	@Override
	public Mark findMediumScore(int subjectId, int studentId, int type, int semester) {

		return markRepositories.findMediumScore(subjectId, studentId, type, semester);
	}

	@Override
	public Iterable<Mark> findAllMark() {

		return markRepositories.findAll();
	}

	@Override
	public List<Mark> findAllMarkByMedium(int studentid, int type, int semseter) {

		return markRepositories.findAllMarkByMedium(studentid, type, semseter);
	}

	@Override
	public Mark SaveAndFind(Mark mark) {

		return markRepositories.save(mark);
	}

	@Override
	public List<MarkRequest> listAverageSubject(int studentId) {
		
		List<MarkRequest> listMark = markRepositories.listAverageSubject(studentId);
		List<MarkRequest> listMarkAverage = new ArrayList<>();
		Iterable<Subjects> listSubjects = subjectRepositories.findAll();
		
		for (Subjects subject : listSubjects) {
			
			MarkRequest markRequest = new MarkRequest();
			markRequest.setSubjectId(subject.getId());
			markRequest.setSubjectName(subject.getSubjectName());

			List<CoefficientRequest> ListAverage = new ArrayList<>();

			for (MarkRequest sRequest : listMark) {
				
				CoefficientRequest average = new CoefficientRequest();
				
				if (sRequest.getSubjectId() == markRequest.getSubjectId()) {
					average.setCoeffi(sRequest.getCoefficient());
					average.setSemester(sRequest.getSemester());
					ListAverage.add(average);
					markRequest.setFullNameTeacher(sRequest.getFullNameTeacher());
				}

			}
			
			markRequest.setCoefficients(ListAverage);
			listMarkAverage.add(markRequest);
		}

		return listMarkAverage;
	}

	@Override
	public Float Average(int studentid, int semester) {

		return markRepositories.Average(studentid, semester);
	}

	@Override
	public Mark SaveMark(MarkRequest markRequest) {
		Mark mark = new Mark();

		User user = teacherRepositories.findById(markRequest.getCreatedBy()).get();
		User student = studentRepositories.findById(markRequest.getStudents()).get();
		Subjects subjects = subjectRepositories.findById(markRequest.getSubjects()).get();

		List<Subjects> listSubject = (List<Subjects>)subjectRepositories.findAll();
		
		if (markRequest.getMarkId() > 0 && markRequest.getMarkId() != null) {
			
			mark.setId(markRequest.getMarkId());
			
		}

		mark.setCoefficient(markRequest.getCoefficient());
		mark.setCreatedBy(user);
		mark.setStudents(student);
		mark.setSubjects(subjects);
		mark.setType(markRequest.getType());
		mark.setUpdatedBy(user);
		mark.setSemester(markRequest.getSemester());
		mark.setCreatedDate(LocalDate.now());
		mark.setUpdatedDate(LocalDate.now());

		markRepositories.save(mark);

		if (markRepositories.finbyStudentSubect(markRequest.getSubjects(), markRequest.getStudents(),
				markRequest.getSemester()) != null) {
			
			Mark marks = new Mark();
			
			List<Mark> listMarkSubject = new ArrayList<>();
			
			listMarkSubject.addAll(markRepositories.finbyStudentSubect(markRequest.getSubjects(),
					markRequest.getStudents(), markRequest.getSemester()));
			
			
			if (listMarkSubject.size() > 7) {
				
				Mark mediumscore = markRepositories.findMediumscore(markRequest.getSubjects(), markRequest.getStudents(),
						5, markRequest.getSemester());
				
				if (mediumscore != null && mediumscore.getId() != 0) {
					marks.setId(mediumscore.getId());
				}

				marks.setCreatedDate(LocalDate.now());
				marks.setCreatedBy(user);
				marks.setSemester(markRequest.getSemester());
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

					marks.setCoefficient(Float.valueOf(String.format(Locale.getDefault(), "%.1f", areaMarksubject)));

					markRepositories.save(marks);

				}
			}
		}
		
		
		if(markRepositories.listAverageSubject(student.getId()).size() == (listSubject.size() *2) ) {
			Mark request = new Mark();
			float medium = 0;
			
			MarkRequest markOptional = markRepositories.findMarkMediumByStudent(student.getId() , 6 ,0);
			
			if(markOptional != null && markOptional.getMarkId() != 0 ) {
				request.setId(markOptional.getMarkId());
			}
			
			
			if (markRepositories.Average(student.getId(), 1) > 0
					&& markRepositories.Average(student.getId(), 2) > 0 ) {
				
				float medium1 = markRepositories.Average(student.getId(), 1);
				float medium2 = markRepositories.Average(student.getId(), 2);
				
				medium = (medium1 + (medium2 * 2)) / 3;
					
				request.setCoefficient(Float.valueOf(String.format(Locale.getDefault(), "%.2f" , medium)));
			
			}else {
				
				request.setCoefficient(medium);
			}
			
			request.setStudents(student);
			request.setType(6);
			request.setSemester(0);
			request.setCreatedDate(LocalDate.now());
			request.setUpdatedDate(LocalDate.now());
			
			if(request.getId() != 0) {
				
				markRepositories.saveTypeMediumYear(request.getCoefficient(), request.getId());
			}else {
				
				markRepositories.save(request);
			}
			
			
		}
		
		return mark;
	}

}
