package com.schoolmanagement.service;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.repositories.SubjectRepositories;
import com.schoolmanagement.validation.FieldValueExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SubjectService implements FieldValueExists {

  @Autowired
  private SubjectRepositories subjectRepositories;

  public Iterable<Subjects> getAllSubject() {
    return subjectRepositories.findAll();
  }

  public void SaveSubject(Subjects subjects) {
    subjectRepositories.save(subjects);
  }

  public Subjects findBySubjectID(int id) {
    return subjectRepositories.findById(id);
  }

  public Page<Subjects> getAllSubjectByPage(int pageNumber, String sortField, String sortDir) {
    Sort sort = Sort.by(sortField);
    sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
    Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

    return subjectRepositories.findAll(page);
  }

  public Page<Subjects> findSubjectByName(String name, int pageNumber, String sortField,
      String sortDir) {
    Sort sort = Sort.by(sortField);
    sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
    Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

    return subjectRepositories.subjectFind(name, page);
  }


  @Override
  public boolean fieldValueExists(Object value, String fieldName)
      throws UnsupportedOperationException {

    if (!fieldName.equals("subjectName")) {
      throw new UnsupportedOperationException("Field name not supported");
    }

    if (value == null) {
      return false;
    }

    return !this.subjectRepositories.findByName(value.toString()).isPresent() && fieldName != null;
  }
}