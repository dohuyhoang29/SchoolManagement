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

}
