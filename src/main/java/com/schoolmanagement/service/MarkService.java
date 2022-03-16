package com.schoolmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Mark;

@Service
public interface MarkService {
	void SaveMarkStudent(Mark mark);
	
	List<Mark> findByOtherId(int sbid , int sid , int type , int semester ); 
}
