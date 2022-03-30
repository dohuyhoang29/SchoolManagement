package com.schoolmanagement.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schoolmanagement.model.Mark;
import com.schoolmanagement.model.request.MarkRequest;

@Repository
public interface MarkRepositories extends PagingAndSortingRepository<Mark, Integer> {
	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.students.id = :studentId AND m.type = :type AND m.semester = :semester")
	List<Mark> findByClassSubjectId(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("type") int type , @Param("semester") int semester );

	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.students.id = :studentId AND  m.semester = :semester")
	List<Mark> finbyStudentSubect(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("semester") int semester );
	
	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.students.id = :studentId AND m.type = :type AND m.semester = :semester")
	Mark findMediumscore(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("type") int type , @Param("semester") int semester );

	
	@Query(value="SELECT m FROM Mark m WHERE m.students.id = :studentid AND m.type = :type AND m.semester = :semester ORDER By m.subjects.id ")
	List<Mark> findAllMarkByMedium(@Param("studentid") int sid,@Param("type") int type  , @Param("semester") int semester);
	
	@Query(value="SELECT new com.schoolmanagement.model.request.MarkRequest(s.id, m.coefficient, m.semester,s.subjectName , u.fullName )"
			+ "FROM Mark m "
			+ "JOIN Subjects s ON m.subjectId = s.id "
			+ "JOIN User u ON m.updatedBy = u.id "
			+ "WHERE m.studentId = :studentId AND  m.type = 5")
	List<MarkRequest> listAverageSubject(@Param("studentId") int studentId);
	
	@Query(value ="SELECT AVG(m.coefficient) FROM Mark m WHERE m.studentId = :studentId AND m.type = 5 AND m.semester = :semester")
	float Average( @Param("studentId") int studentId , @Param("semester") int semester );
	
	@Query(value="SELECT new com.schoolmanagement.model.request.MarkRequest(s.id, m.coefficient, m.semester,s.subjectName , u.fullName )"
			+ "FROM Mark m "
			+ "JOIN Subjects s ON m.subjectId = s.id "
			+ "JOIN User u ON m.updatedBy = u.id "
			+ "WHERE m.studentId = :studentId AND  m.type = 5 AND m.semester = :semester")
	List<MarkRequest> listAverageSubjectBySemester(@Param("studentId") int studentId , @Param("semester") int semester);

	
	@Query(value="SELECT new com.schoolmanagement.model.request.MarkRequest(m.id) FROM Mark m WHERE m.students.id = :studentid AND m.type = :type AND m.semester = :semester ")
	MarkRequest findMarkMediumByStudent(@Param("studentid") int sid,@Param("type") int type  , @Param("semester") int semester);

	@Modifying
	@Transactional
	@Query(value ="UPDATE mark  SET coefficient = :coefficient WHERE id = :markId ", nativeQuery = true)
	void saveTypeMediumYear(@Param("coefficient") float coefficient , @Param("markId") int markId);

}
