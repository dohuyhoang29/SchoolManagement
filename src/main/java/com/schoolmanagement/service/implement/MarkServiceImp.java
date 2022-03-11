package com.schoolmanagement.service.implement;

import org.springframework.beans.factory.annotation.Autowired;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.repositories.MarkReponsitories;
import com.schoolmanagement.service.MarkService;

public class MarkServiceImp implements MarkService {
	@Autowired
	private MarkReponsitories markReponsitories;
	
	@Override
	public void SaveMarkStudent(Mark mark) {
		
		markReponsitories.save(mark);
	}

}
