package com.schoolmanagement.service;

import com.schoolmanagement.model.Subjects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface SubjectService {
  Iterable<Subjects> getAllSubject();

  void SaveSubject(Subjects subjects);

  Subjects findBySubjectID(int id);

  Page<Subjects> getAllSubjectByPage(int pageNumber, String sortField, String sortDir);

  Page<Subjects> findSubjectByName(String name, int pageNumber, String sortField,
      String sortDir);
}
