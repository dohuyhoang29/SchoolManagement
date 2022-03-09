package com.schoolmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.ClassTeacherSubejct;
import com.schoolmanagement.repositories.ClassTeacherSubjectRepositories;

@Service
public class ClassTeacherSubjectService {
	@Autowired
	private ClassTeacherSubjectRepositories classTeacherSubjectRepositories;
	
	public void Save(ClassTeacherSubejct classTeacherSubejct) {
		classTeacherSubjectRepositories.save(classTeacherSubejct);
		
	}
	
	public ClassTeacherSubejct FindbyIdOrther(int dataU , int dataC) {
		return classTeacherSubjectRepositories.findByIdTeacherClass(dataU, dataC);
	}
	
	public Iterable<ClassTeacherSubejct> findAllByClassId(int id){
		return classTeacherSubjectRepositories.findByClassId(id);
	}
	
	public Iterable<ClassTeacherSubejct> findAllTeacherSubject(){
		
		return classTeacherSubjectRepositories.findAll();
	}
	
//
}
