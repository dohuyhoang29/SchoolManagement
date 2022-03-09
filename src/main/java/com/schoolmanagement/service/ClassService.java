package com.schoolmanagement.service;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.repositories.ClassRepositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

	@Autowired
	private ClassRepositories classRepositories;

	public void saveClass(Class aClass) {
		classRepositories.save(aClass);
	}

	public Class getClassById(Integer id) {
		return classRepositories.findById(id).get();
	}

	public Iterable<Class> getAllClass() {
		return classRepositories.findAll();
	}
	
	public Page<Class> getAllClassPage(String b , int page){
		Pageable pages = PageRequest.of(page -1, 10);
		
		if(b == "") {
			
			return classRepositories.findAll(pages);
		}else {
			return classRepositories.listClass("%"+b+"%",pages);
		}
		
	}
	
}
