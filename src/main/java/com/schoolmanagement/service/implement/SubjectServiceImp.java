package com.schoolmanagement.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.request.CoefficientRequest;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.repositories.SubjectRepositories;
import com.schoolmanagement.service.SubjectService;

@Service
public class SubjectServiceImp implements SubjectService {

	@Autowired
	private SubjectRepositories subjectRepositories;

	@Override
	public Iterable<Subjects> getAllSubject() {
		return subjectRepositories.findAll();
	}

	@Override
	public void SaveSubject(Subjects subjects) {
		subjectRepositories.save(subjects);
	}

	@Override
	public Subjects findBySubjectID(int id) {
		return subjectRepositories.findById(id);
	}

	@Override
	public Page<Subjects> getAllSubjectByPage(int pageNumber, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		return subjectRepositories.findAll(page);
	}

	@Override
	public Page<Subjects> findSubjectByName(String name, int pageNumber, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		return subjectRepositories.subjectFind(name, page);
	}

	@Override
	public Subjects findSubjectBySubjectName(String name) {
		Optional<Subjects> subjects = subjectRepositories.findByName(name);
		if (subjects.isPresent()) {
			return subjects.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Subjects> findAllSubjectAscId() {

		return subjectRepositories.findAllMarkByASC();
	}

	@Override
	public List<MarkRequest> findByStudent(int studentId) {

		List<MarkRequest> listRq = subjectRepositories.findByStudent(studentId);
		Iterable<Subjects> subjects = subjectRepositories.findAll();
		List<MarkRequest> listRquest = new ArrayList<>();

		for (Subjects s : subjects) {

			List<CoefficientRequest> sc = new ArrayList<>();
			Integer subjectId = s.getId();
			MarkRequest sb = new MarkRequest();

			sb.setSubjectName(s.getSubjectName());
			sb.setSubjectId(subjectId);

			for (int i = 1; i <= 2; i++) {

				for (int j = 1; j <= 5; j++) {
					
					CoefficientRequest cr = new CoefficientRequest();

					cr.setSemester(i);
					cr.setType(j);
					cr.setCoefficient("");
					
					for (MarkRequest m : listRq) {

						if (subjectId == m.getSubjectId()) {
							
							if (i == m.getSemester()) {
								
								cr.setSemester(m.getSemester());

								if (j == m.getType()) {

									cr.setType(m.getType());
									cr.setCoefficient(m.getCoeff());
								}
							}
						}
					}
					
					sc.add(cr);
					sb.setCoefficients(sc);
				}
			}
			
			listRquest.add(sb);

		}

		return listRquest;
	}

	@Override
	public List<MarkRequest> findByStudentSemester(int studentId, int semester) {
		List<MarkRequest> listRq = subjectRepositories.findByStudentSemester(studentId,semester);
		Iterable<Subjects> subjects = subjectRepositories.findAll();
		List<MarkRequest> listRquest = new ArrayList<>();

		for (Subjects s : subjects) {

			List<CoefficientRequest> sc = new ArrayList<>();
			Integer subjectId = s.getId();
			int sm = semester;
			MarkRequest sb = new MarkRequest();
			
			sb.setSubjectName(s.getSubjectName());
			sb.setSubjectId(subjectId);

			
				for (int j = 1; j <= 5; j++) {
					
					CoefficientRequest cr = new CoefficientRequest();

					cr.setSemester(sm);
					cr.setType(j);
					cr.setCoefficient("");
					
					for (MarkRequest m : listRq) {

						if (subjectId == m.getSubjectId()) {
							
							if (sm == m.getSemester()) {
								
								cr.setSemester(m.getSemester());

								if (j == m.getType()) {

									cr.setType(m.getType());
									cr.setCoefficient(m.getCoeff());
								}
							}
						}
					}
					
					sc.add(cr);
					sb.setCoefficients(sc);
				}
			
			
			listRquest.add(sb);

		}

		return listRquest;
	}
}