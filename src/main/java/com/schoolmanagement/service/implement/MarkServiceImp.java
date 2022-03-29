package com.schoolmanagement.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.request.CoefficientRequest;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.repositories.MarkRepositories;
import com.schoolmanagement.repositories.SubjectRepositories;
import com.schoolmanagement.service.MarkService;

@Service
public class MarkServiceImp implements MarkService {
	@Autowired
	private MarkRepositories markRepositories;

	@Autowired
	private SubjectRepositories subjectRepositories;

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

		return markRepositories.finbyStudentSubect(subjectId, studentId, semester);
	}

	@Override
	public Mark findMediumScore(int subjectId, int studentId, int type, int semester) {

		return markRepositories.findMediumscore(subjectId, studentId, type, semester);
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

}
