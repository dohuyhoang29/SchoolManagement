package com.schoolmanagement.repositories;



import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schoolmanagement.model.ClassTeacherSubject;


@Repository
public interface ClassTeacherSubjectRepositories extends PagingAndSortingRepository<ClassTeacherSubject, Integer> {
	@Query(value ="SELECT s FROM ClassTeacherSubject s WHERE "
			+ "s.users.id = :dataU AND s.theClass.id = :dataC")
	List<ClassTeacherSubject> findByIdTeacherClass(@Param("dataU") int idu , @Param("dataC") int idC);
	
	@Query(value ="SELECT s FROM ClassTeacherSubject s WHERE "
			+ "s.users.id = :userId AND s.theClass.id = :classId AND s.subjects.id = :subjectId")
	ClassTeacherSubject findByIdTeacherClass(@Param("userId") int userId , @Param("classId") int classId , @Param("subjectId") int subjectId);
	
	
	@Query(value ="SELECT s FROM ClassTeacherSubject s WHERE s.theClass.id = (:idc) ")
	Iterable<ClassTeacherSubject> findByClassId(@Param("idc") int id);

	@Query(value = "SELECT cts FROM ClassTeacherSubject cts WHERE cts.users.id = :id")
	Set<ClassTeacherSubject> findByTeacherId(@Param("id") Integer id);

}
