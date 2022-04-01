package com.schoolmanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.model.request.SchoolYearClassRequest;

public interface ClassRepositories extends PagingAndSortingRepository<Class, Integer> {

	
	@Query(value="SELECT c FROM Class c WHERE c.className LIKE %:dataFind%")
	Page<Class> listClass(@Param("dataFind") String data , Pageable pageable);

	@Query(value = "SELECT new com.schoolmanagement.model.request.SchoolYearClassRequest(c.schoolYear) FROM Class c GROUP BY c.schoolYear ORDER BY c.schoolYear ASC")
	List<SchoolYearClassRequest> getSchoolYear ();

	Optional<Class> getClassByClassName(String className);

	@Query(value = "SELECT c FROM Class c JOIN ClassTeacherSubject cts ON c.id = cts.theClass.id WHERE cts.users.id = :teacherID GROUP BY c.id")
	Page<Class> getAllByTeacherId(@Param("teacherID") Integer id, Pageable pageable);

	@Query(value = "SELECT COUNT(c.id) from Class c")
	int countAll();
	
	
	@Query(value="SELECT new com.schoolmanagement.model.request.MarkRequest( GROUP_CONCAT(m.coefficient)  )  "
			+ "FROM Class c "
			+ "JOIN User u ON u.aClass.id = c.id "
			+ "JOIN Mark m ON m.studentId = u.id "
			+ "JOIN Subjects s ON s.id = m.subjectId "
			+ "WHERE u.id = :studentId AND c.id = :classId AND s.id = :subjectId AND m.type = 5")
	MarkRequest DataExport(@Param("studentId") int StudentId , @Param("classId") int classId , @Param("subjectId") int subjectId );
}
