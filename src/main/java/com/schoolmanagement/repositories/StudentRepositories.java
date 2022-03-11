package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Student;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepositories extends PagingAndSortingRepository<Student, Integer> {

	List<Student> findAllByAdmissionYear(Integer admissionYear);

	@Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.aClass.className LIKE %:class%")
	Page<Student> findStudentByFullName(@Param("fullName") String fullName, @Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.status = :status AND s.aClass.className LIKE %:class%")
	Page<Student> findStudentByFullNameAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.status = :status AND s.aClass.grade = :grade AND s.aClass.className LIKE %:class% AND s.aClass.schoolYear = :schoolYear")
	Page<Student> findStudent(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("grade") Integer grade, @Param("class") String className, @Param("schoolYear") Integer schoolYear, Pageable pageable);

	@Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.status = :status AND s.aClass.grade = :grade AND s.aClass.className LIKE %:class%")
	Page<Student> findStudentByGradeAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("grade") Integer grade, @Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.status = :status AND s.aClass.className LIKE %:class% AND s.aClass.schoolYear = :schoolYear")
	Page<Student> findStudentBySchoolYearAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("schoolYear") Integer schoolYear, @Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.aClass.grade = :grade AND s.aClass.className LIKE %:class%")
	Page<Student> findStudentByGrade(@Param("fullName") String fullName, @Param("grade") Integer grade,
			@Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.aClass.className LIKE %:class% AND s.aClass.schoolYear = :schoolYear")
	Page<Student> findStudentBySchoolYear(@Param("fullName") String fullName, @Param("class") String className,
			@Param("schoolYear") Integer schoolYear, Pageable pageable);

	@Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE %:fullName% AND s.aClass.className LIKE %:class% AND s.aClass.schoolYear = :schoolYear AND s.aClass.grade = :grade")
	Page<Student> findStudentBySchoolYearAndGrade(@Param("fullName") String fullName, @Param("class") String className,
			@Param("schoolYear") Integer schoolYear, @Param("grade") Integer grade, Pageable pageable);

	@Query(value ="SELECT s FROM Student s WHERE s.aClass.id = (:classId) AND s.fullName LIKE %:fullName%")
	Page<Student> findByClassId(@Param("classId") int id, @Param("fullName") String search , Pageable page);
	
	@Query(value ="SELECT s FROM Student s WHERE s.aClass.id = (:classId)")
	List<Student> findByIdClass(@Param("classId") int id);
}
