package com.schoolmanagement.repositories;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schoolmanagement.model.ClassTeacherSubejct;


@Repository
public interface ClassTeacherSubjectRepositories extends PagingAndSortingRepository<ClassTeacherSubejct, Integer> {
	@Query(value ="SELECT s FROM ClassTeacherSubejct s WHERE "
			+ "s.subjects.id = (:dataU) AND s.theClass.id = (:dataC)")
	ClassTeacherSubejct findByIdTeacherClass(@Param("dataU") int idu , @Param("dataC") int idC);
	
	@Query(value ="SELECT s FROM ClassTeacherSubejct s WHERE s.theClass.id = (:idc) ")
	Iterable<ClassTeacherSubejct> findByClassId(@Param("idc") int id);
	
}