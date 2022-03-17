package com.schoolmanagement.repositories;

import com.schoolmanagement.model.StudentEvaluate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentEvaluateRepositories extends PagingAndSortingRepository<StudentEvaluate, Integer> {
  @Query(value = "SELECT se FROM StudentEvaluate  se WHERE se.student.id = :studentId")
  StudentEvaluate findStudentEvaluateByStudentId(@Param("studentId") Integer id);
}
