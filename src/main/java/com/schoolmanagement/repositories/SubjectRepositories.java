package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Subjects;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepositories extends PagingAndSortingRepository<Subjects, Integer> {

  Subjects findById(int integer);

  Iterable<Subjects> findAllBySubjectName(String subjectName);

  @Query(value = "SELECT s FROM Subjects s WHERE s.subjectName = (:Data)")
  Optional<Subjects> findByName(@Param("Data") String Data);

  @Query(value = "SELECT s FROM Subjects s WHERE s.subjectName LIKE %:data%")
  Page<Subjects> subjectFind(@Param("data") String data, Pageable pageable);
}
