package com.schoolmanagement.service.implement;


import com.schoolmanagement.model.Class;
import com.schoolmanagement.service.ClassTeacherSubjectService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.ClassTeacherSubject;
import com.schoolmanagement.repositories.ClassTeacherSubjectRepositories;

@Service
public class ClassTeacherSubjectServiceImp implements ClassTeacherSubjectService {
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
	public Set<Class> findAllByTeacher(Integer id) {
		Set<Class> classList = new HashSet<>();
		for (ClassTeacherSubject cts : classTeacherSubjectRepositories.findByTeacherId(id)) {
			classList.add(cts.getTheClass());
		}

		return classList;
	}
}
