package com.schoolmanagement.service;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.ClassTeacherSubject;

import java.util.List;
import java.util.Set;

public interface ClassTeacherSubjectService {
	void Save(ClassTeacherSubject classTeacherSubject);

	List<ClassTeacherSubject> findByIdOther(int dataU, int dataC);

	ClassTeacherSubject findById(int teacherId, int classId , int subjectId);

	Iterable<ClassTeacherSubject> findAll();
	
	Iterable<ClassTeacherSubject> findAllByClassId(int id);

	Set<Class> findAllByTeacher(Integer id);
}
