package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Student;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepositories extends PagingAndSortingRepository<Student, Integer> {

  List<Student> findAllByAdmissionYear(Integer admissionYear);

  @Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName%")
  Page<Student> findStudentByFullName(@Param("fullName") String fullName, Pageable pageable);

  @Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.status = :status")
  Page<Student> findStudentByFullNameAndStatus(@Param("fullName") String fullName,
      @Param("status") Integer status, Pageable pageable);
}
