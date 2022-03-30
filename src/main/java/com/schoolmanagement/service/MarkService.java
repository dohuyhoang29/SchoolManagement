package com.schoolmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.request.MarkRequest;

@Service
public interface MarkService {
	void SaveMarkStudent(Mark mark);
	
	List<Mark> findByOtherId(int subjectId , int studentid , int type , int semester ); 
	
	List<Mark> findByStudentSubject(int subjectId , int studentid  , int semester ); 
	
	Mark findMediumScore(int subjectId , int studentid , int type , int semester ); 
	
	Iterable<Mark> findAllMark();
	
	List<Mark> findAllMarkByMedium(int studentid ,int type , int semester);
	
	Mark SaveAndFind(Mark mark);
	
	List<MarkRequest> listAverageSubject(int studentId);
	
	Mark SaveMark(MarkRequest markRequest);
	
	Float Average(int studentid  , int semester);
}
