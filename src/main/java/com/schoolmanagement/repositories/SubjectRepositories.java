package com.schoolmanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.model.request.MarkRequest;

@Repository
public interface SubjectRepositories extends PagingAndSortingRepository<Subjects, Integer> {

	Subjects findById(int integer);

	Iterable<Subjects> findAllBySubjectName(String subjectName);

	@Query(value = "SELECT s FROM Subjects s WHERE s.subjectName = (:Data)")
	Optional<Subjects> findByName(@Param("Data") String Data);

	@Query(value = "SELECT s FROM Subjects s WHERE s.subjectName LIKE %:data%")
	Page<Subjects> subjectFind(@Param("data") String data, Pageable pageable);
	
	@Query(value="SELECT s FROM Subjects s ORDER BY s.id ASC")
	List<Subjects> findAllMarkByASC();

	@Transactional
	@Query(value = "SELECT new com.schoolmanagement.model.request.MarkRequest(s.subjectName, s.id, m.studentId, m.type, m.semester, "
			+ " GROUP_CONCAT(m.coefficient ) ) "
			+ " FROM Subjects s "
			+ " JOIN Mark m ON s.id = m.subjectId "
			+ " WHERE m.studentId = :studentId "
			+ " GROUP BY s.subjectName, " 
			+ " s.id, m.subjectId, m.type , m.semester")
	List<MarkRequest> findByStudent(@Param("studentId") int id );
	
	
	@Query(value = "SELECT new com.schoolmanagement.model.request.MarkRequest(s.subjectName, s.id, m.studentId, m.type, m.semester, "
			+ " GROUP_CONCAT(m.coefficient ) ) "
			+ " FROM Subjects s "
			+ " JOIN Mark m ON s.id = m.subjectId "
			+ " WHERE m.studentId = :studentId AND m.semester = :semester "
			+ " GROUP BY s.subjectName, " 
			+ " s.id, m.subjectId, m.type , m.semester")
	List<MarkRequest> findByStudentSemester(@Param("studentId") int id , @Param("semester") int semester );

}
