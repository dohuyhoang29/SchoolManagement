package com.schoolmanagement.repositories;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schoolmanagement.model.ClassTeacherSubject;


@Repository
public interface ClassTeacherSubjectRepositories extends PagingAndSortingRepository<ClassTeacherSubject, Integer> {
	@Query(value ="SELECT s FROM ClassTeacherSubject s WHERE "
			+ "s.subjects.id = (:dataU) AND s.theClass.id = (:dataC)")
  ClassTeacherSubject findByIdTeacherClass(@Param("dataU") int idu , @Param("dataC") int idC);
	
	@Query(value ="SELECT s FROM ClassTeacherSubject s WHERE s.theClass.id = (:idc) ")
	Iterable<ClassTeacherSubject> findByClassId(@Param("idc") int id);

	@Query(value = "SELECT cts FROM ClassTeacherSubject cts WHERE cts.users.id = :id")
	Iterable<ClassTeacherSubject> findByTeacherId(@Param("id") Integer id);
}
