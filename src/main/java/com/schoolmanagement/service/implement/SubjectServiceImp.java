package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.repositories.SubjectRepositories;
import com.schoolmanagement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}