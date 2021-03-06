package com.schoolmanagement.repositories;

import com.schoolmanagement.model.request.AverageMarkRequest;
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
	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.studentId = :studentId AND m.type = :type AND m.semester = :semester")
	List<Mark> findByClassSubjectId(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("type") int type , @Param("semester") int semester );

	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.studentId = :studentId AND  m.semester = :semester")
	List<Mark> findByStudentSubject(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("semester") int semester );
	
	@Query(value="SELECT m FROM Mark m WHERE m.subjects.id = :subjectid AND m.studentId = :studentId AND m.type = :type AND m.semester = :semester")
	Mark findMediumScore(@Param("subjectid") int sbid , @Param("studentId") int sid , @Param("type") int type , @Param("semester") int semester );

	
	@Query(value="SELECT m FROM Mark m WHERE m.studentId = :studentId AND m.type = :type AND m.semester = :semester ORDER By m.subjects.id ")
	List<Mark> findAllMarkByMedium(@Param("studentId") int sid,@Param("type") int type  , @Param("semester") int semester);
	
	@Query(value="SELECT new com.schoolmanagement.model.request.MarkRequest(s.id, m.coefficient, m.semester,s.subjectName , u.fullName )"
			+ "FROM Mark m "
			+ "JOIN Subjects s ON m.subjectId = s.id "
			+ "JOIN User u ON m.updatedBy.id = u.id "
			+ "WHERE m.studentId = :studentId AND  m.type = 5")
	List<MarkRequest> listAverageSubject(@Param("studentId") int studentId);
	
	@Query(value ="SELECT AVG(m.coefficient) FROM Mark m WHERE m.studentId = :studentId AND m.type = 5 AND m.semester = :semester")
	Float Average( @Param("studentId") int studentId , @Param("semester") int semester );

	@Query(value ="SELECT  new com.schoolmanagement.model.request.AverageMarkRequest(m.studentId, m.semester, AVG(m.coefficient)) "
			+ "FROM Mark m WHERE m.type = 5 AND SUBSTRING(m.createdDate, 1, 4)  = :lastYear")
	List<AverageMarkRequest> getAllAverageMarkLastYear(@Param("lastYear") String lastYear);
	
	@Query(value="SELECT new com.schoolmanagement.model.request.MarkRequest(s.id, m.coefficient, m.semester,s.subjectName , u.fullName )"
			+ "FROM Mark m "
			+ "JOIN Subjects s ON m.subjectId = s.id "
			+ "JOIN User u ON m.updatedBy.id = u.id "
			+ "WHERE m.studentId = :studentId AND  m.type = 5 AND m.semester = :semester")
	List<MarkRequest> listAverageSubjectBySemester(@Param("studentId") int studentId , @Param("semester") int semester);

	
	@Query(value="SELECT new com.schoolmanagement.model.request.MarkRequest(m.id , m.coefficient) FROM Mark m WHERE m.studentId = :studentid AND m.type = :type AND m.semester = :semester ")
	MarkRequest findMarkMediumByStudent(@Param("studentid") int sid,@Param("type") int type  , @Param("semester") int semester);

	@Modifying
	@Transactional
	@Query(value ="UPDATE mark  SET coefficient = :coefficient WHERE id = :markId ", nativeQuery = true)
	void saveTypeMediumYear(@Param("coefficient") float coefficient , @Param("markId") int markId);

	@Query(value="SELECT m FROM Mark m WHERE  m.studentId = :studentId AND m.type = :type AND m.semester = :semester")
	Mark findAverageYear( @Param("studentId") int sid , @Param("type") int type , @Param("semester") int semester );

	
	@Query(value ="SELECT m FROM Mark m where m.studentId = :studentId GROUP BY m.studentId ")
	Mark StudentExist(@Param("studentId") int studentId);
	
}
