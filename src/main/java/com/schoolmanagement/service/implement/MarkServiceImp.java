package com.schoolmanagement.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.repositories.MarkReponsitories;
import com.schoolmanagement.service.MarkService;

@Service
public class MarkServiceImp implements MarkService {
	@Autowired
	private MarkReponsitories markReponsitories;
	
	@Override
	public void SaveMarkStudent(Mark mark) {
		
		markReponsitories.save(mark);
	}

	@Override
	public List<Mark> findByOtherId(int sbid, int sid, int type, int semester ) {
		
		return markReponsitories.findByClassSubjectId(sbid, sid, type, semester);
	}

	@Override
	public List<Mark> findByStudentSubject(int sbid, int sid, int semester) {
		
		return markReponsitories.finbyStudentSubect(sbid, sid, semester);
	}

	@Override
	public Mark findMediumScore(int sbid, int sid, int type, int semester) {
		
		return markReponsitories.findMediumscore(sbid, sid, type, semester);
	}

	@Override
	public Iterable<Mark> findAllMark() {
		
		return markReponsitories.findAll();
	}
	
	@Override
	public List<Mark> findAllMarkByMedium(int studentid ,int type , int semseter) {
		
		return markReponsitories.findAllMarkByMedium(studentid, type, semseter);
	}

	

}
