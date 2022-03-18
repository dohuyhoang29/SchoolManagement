package com.schoolmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.request.SubjectRequest;

public interface SubjectService {
	Iterable<Subjects> getAllSubject();

	List<Subjects> findAllSubjectAscId();
	
	void SaveSubject(Subjects subjects);

	Subjects findBySubjectID(int id);

	Page<Subjects> getAllSubjectByPage(int pageNumber, String sortField, String sortDir);

	Page<Subjects> findSubjectByName(String name, int pageNumber, String sortField, String sortDir);

	Subjects findSubjectBySubjectName(String name);
	
	List<SubjectRequest> findByStudent(int studentId , int type , int semester);
}
