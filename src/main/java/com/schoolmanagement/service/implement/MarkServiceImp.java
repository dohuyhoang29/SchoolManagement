package com.schoolmanagement.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.repositories.MarkRepositories;
import com.schoolmanagement.service.MarkService;

@Service
public class MarkServiceImp implements MarkService {
	@Autowired
	private MarkRepositories markRepositories;
	
	@Override
	public void SaveMarkStudent(Mark mark) {
		
		markRepositories.save(mark);
	}

	@Override
	public List<Mark> findByOtherId(int sbid, int sid, int type, int semester ) {
		
		return markRepositories.findByClassSubjectId(sbid, sid, type, semester);
	}

	@Override
	public List<Mark> findByStudentSubject(int sbid, int sid, int semester) {
		
		return markRepositories.finbyStudentSubect(sbid, sid, semester);
	}

	@Override
	public Mark findMediumScore(int sbid, int sid, int type, int semester) {
		
		return markRepositories.findMediumscore(sbid, sid, type, semester);
	}

	@Override
	public Iterable<Mark> findAllMark() {
		
		return markRepositories.findAll();
	}
	
	@Override
	public List<Mark> findAllMarkByMedium(int studentid ,int type , int semseter) {
		
		return markRepositories.findAllMarkByMedium(studentid, type, semseter);
	}

	

}
