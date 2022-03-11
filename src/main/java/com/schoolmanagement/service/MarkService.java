package com.schoolmanagement.service;

import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Mark;

@Service
public interface MarkService {
	void SaveMarkStudent(Mark mark);
}
