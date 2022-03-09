package com.schoolmanagement.service;

import com.schoolmanagement.model.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
  void saveStudent(Student student);

  Iterable<Student> getAllStudent();

  Page<Student> getAllStudentByPages(int pageNumber, String sortField, String sortDir);

  Student getStudentById(Integer id);

  Page<Student> searchStudent(String fullName, String status, int pageNumber, String sortField,
      String sortDir);

  Page<Student> getAllStudentByClass(int pageNumber);
}
