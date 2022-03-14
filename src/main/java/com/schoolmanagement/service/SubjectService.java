package com.schoolmanagement.service;

import org.springframework.data.domain.Page;

import com.schoolmanagement.model.Subjects;

public interface SubjectService {
  Iterable<Subjects> getAllSubject();

  void SaveSubject(Subjects subjects);

  Subjects findBySubjectID(int id);

  Page<Subjects> getAllSubjectByPage(int pageNumber, String sortField, String sortDir);

  Page<Subjects> findSubjectByName(String name, int pageNumber, String sortField,
      String sortDir);

  Subjects findSubjectBySubjectName(String name);
}
