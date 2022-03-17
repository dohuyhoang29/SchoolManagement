package com.schoolmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schoolmanagement.model.Mark;

@Repository
public interface MarkReponsitories extends PagingAndSortingRepository<Mark, Integer> {
	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.students.id = :studentId AND m.type = :type AND m.semester = :semester")
	List<Mark> findByClassSubjectId(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("type") int type , @Param("semester") int semester );

	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.students.id = :studentId AND  m.semester = :semester")
	List<Mark> finbyStudentSubect(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("semester") int semester );
	
	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.students.id = :studentId AND m.type = :type AND m.semester = :semester")
	Mark findMediumscore(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("type") int type , @Param("semester") int semester );

	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.students.id = :studentId AND m.type = :type AND m.semester = :semester")
	List<Mark> find(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("type") int type  );

	@Query(value="SELEct m FROM Mark m WHERE m.type = :type ORDER BY m.id , m.subjects.id")
	List<Mark> findAllMarkByMedium(@Param("type") int type );
}
