package com.schoolmanagement.repositories;

import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentEvaluateRepositories extends PagingAndSortingRepository<StudentEvaluate, Integer> {
	@Query(value = "SELECT se FROM StudentEvaluate  se WHERE se.student.id = :studentId AND se.semester = :semester")
	StudentEvaluate findStudentEvaluateByStudentId(@Param("studentId") Integer id, @Param("semester") Integer semester);

	
	@Query(value = "SELECT se FROM StudentEvaluate se WHERE se.student.id = :studentId ")
	List<StudentEvaluate> studentEvaluate(@Param("studentId") int studentId);
	
	Iterable<StudentEvaluate> findAllByStudent(User student);
}
