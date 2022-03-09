package com.schoolmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.repositories.ClassTeacherSubjectRepositories;

@Service
public class ClassTeacherSubjectServiceImp implements ClassTeacherSubjectService{
	@Autowired
	private ClassTeacherSubjectRepositories classTeacherSubjectRepositories;

	@Override
	public void Save(ClassTeacherSubject classTeacherSubject) {
		classTeacherSubjectRepositories.save(classTeacherSubject);
		
	}

	@Override
	public ClassTeacherSubject findByIdOther(int dataU , int dataC) {
		return classTeacherSubjectRepositories.findByIdTeacherClass(dataU, dataC);
	}

	@Override
	public Iterable<ClassTeacherSubject> findAllByClassId(int id){
		return classTeacherSubjectRepositories.findByClassId(id);
	}

	@Override
	public Iterable<ClassTeacherSubject> findAllTeacherSubject(){
		
		return classTeacherSubjectRepositories.findAll();
	}
}
