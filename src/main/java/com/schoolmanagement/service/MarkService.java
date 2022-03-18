package com.schoolmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Mark;

@Service
public interface MarkService {
	void SaveMarkStudent(Mark mark);
	
	List<Mark> findByOtherId(int sbid , int sid , int type , int semester ); 
	
	List<Mark> findByStudentSubject(int sbid , int sid  , int semester ); 
	
	Mark findMediumScore(int sbid , int sid , int type , int semester ); 
	
	Iterable<Mark> findAllMark();
	
	List<Mark> findAllMarkByMedium(int studentid ,int type , int semester);
	
	
}
