package com.schoolmanagement.service;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.repositories.SubjectReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

  @Autowired
  private SubjectReponsitory subjectReponsitory;

  public Iterable<Subjects> FindAllSubject() {
    return subjectReponsitory.findAll();
  }

  public void SaveSubject(Subjects subjects) {
    subjectReponsitory.save(subjects);
  }

  public Subjects findBySubjectID(int id) {
    return subjectReponsitory.findById(id);
  }

  public Page<Subjects> subjectsPage(int page, String find) {
    Pageable pageable = PageRequest.of(page - 1, 2);
    if (find != "") {

      return subjectReponsitory.subjectFind("%" + find + "%", pageable);
    }

    return subjectReponsitory.findAll(pageable);
  }

  public Subjects findBySubjectName(String name) {
    return subjectReponsitory.findByName(name);
  }


}