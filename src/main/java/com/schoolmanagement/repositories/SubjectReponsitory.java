package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Subjects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectReponsitory extends PagingAndSortingRepository<Subjects , Integer> {
    Subjects findById(int integer);

    @Query(value = "SELECT s FROM Subjects s WHERE s.subjectName = (:Data)")
    Subjects findByName(@Param("Data") String Data);

    @Query(value = "SELECT s FROM Subjects s WHERE s.subjectName LIKE (:data)")
    Page<Subjects> subjectFind(@Param("data") String data , Pageable pageable);
}