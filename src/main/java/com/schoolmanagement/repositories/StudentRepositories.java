package com.schoolmanagement.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.MarkRequest;

public interface StudentRepositories extends PagingAndSortingRepository<User, Integer> {

	@Query(value = "SELECT u " + "FROM User u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "WHERE ur.pk.roleId = 4")
	List<User> findAllStudent();

	@Query(value = "SELECT u " + "FROM User u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "WHERE ur.pk.roleId = 4 AND u.aClass.id = :id")
	List<User> findAllStudentByClass(@Param("id") Integer id);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "WHERE ur.pk.roleId = 4 AND ui.admissionYear = :admissionYear")
	List<User> findAllByAdmissionYear(@Param("admissionYear") Integer admissionYear);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "INNER JOIN Class AS c ON c.id = u.aClass.id "
			+ "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% AND c.className LIKE %:className%")
	Page<User> findStudentByFullNameAndClass(@Param("fullName") String fullName, @Param("className") String className,
			Pageable pageable);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "INNER JOIN Class AS c ON c.id = u.aClass.id "
			+ "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
			+ "AND u.userInfo.status = :status AND u.aClass.className LIKE %:class%")
	Page<User> findStudentByFullNameAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("class") String className, Pageable pageable);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "INNER JOIN Class AS c ON c.id = u.aClass.id "
			+ "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
			+ "AND u.userInfo.status = :status AND u.aClass.grade = :grade "
			+ "AND u.aClass.className LIKE %:class% AND u.aClass.schoolYear = :schoolYear")
	Page<User> findStudent(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("grade") Integer grade, @Param("class") String className, @Param("schoolYear") Integer schoolYear,
			Pageable pageable);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "INNER JOIN Class AS c ON c.id = u.aClass.id "
			+ "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
			+ "AND u.userInfo.status = :status AND u.aClass.grade = :grade " + "AND u.aClass.className LIKE %:class%")
	Page<User> findStudentByGradeAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("grade") Integer grade, @Param("class") String className, Pageable pageable);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "INNER JOIN Class AS c ON c.id = u.aClass.id "
			+ "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
			+ "AND u.userInfo.status = :status AND u.aClass.className LIKE %:class% "
			+ "AND u.aClass.schoolYear = :schoolYear")
	Page<User> findStudentBySchoolYearAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("schoolYear") Integer schoolYear, @Param("class") String className, Pageable pageable);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "INNER JOIN Class AS c ON c.id = u.aClass.id "
			+ "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
			+ "AND c.grade = :grade AND c.className LIKE %:class%")
	Page<User> findStudentByGrade(@Param("fullName") String fullName, @Param("grade") Integer grade,
			@Param("class") String className, Pageable pageable);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "INNER JOIN Class AS c ON c.id = u.aClass.id "
			+ "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
			+ "AND c.className LIKE %:class% AND c.schoolYear = :schoolYear")
	Page<User> findStudentBySchoolYear(@Param("fullName") String fullName, @Param("class") String className,
			@Param("schoolYear") Integer schoolYear, Pageable pageable);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "INNER JOIN Class AS c ON c.id = u.aClass.id "
			+ "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
			+ "AND c.className LIKE %:class% AND c.schoolYear = :schoolYear " + "AND c.grade = :grade")
	Page<User> findStudentBySchoolYearAndGrade(@Param("fullName") String fullName, @Param("class") String className,
			@Param("schoolYear") Integer schoolYear, @Param("grade") Integer grade, Pageable pageable);

	@Query(value = "SELECT u " + "FROM User u " + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
			+ "INNER JOIN Role r ON r.id = ur.pk.roleId "
			+ "WHERE ur.pk.roleId = 4 AND u.aClass.id = (:classId) AND u.fullName LIKE %:fullName%")
	Page<User> findByClassId(@Param("classId") int id, @Param("fullName") String search, Pageable page);

	@Query(value = "SELECT u " + "FROM User u " + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
			+ "INNER JOIN Role r ON r.id = ur.pk.roleId "
			+ "WHERE ur.pk.roleId = 4 AND u.aClass.id = (:classId)")
	List<User> findByIdClass(@Param("classId") int id);

	@Query(value = "SELECT u " + "FROM User u " + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
			+ "INNER JOIN Role r ON r.id = ur.pk.roleId "
			+ "WHERE ur.pk.roleId = 4 AND u.aClass IN :classList "
			+ "AND u.fullName LIKE %:fullName% AND u.aClass.className LIKE %:className%")
	Page<User> findStudentByListClass(@Param("classList") Collection<Class> classList,
			@Param("fullName") String fullName, @Param("className") String className, Pageable pageable);

	@Query(value = "SELECT u " + "FROM User u " + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
			+ "INNER JOIN Role r ON r.id = ur.pk.roleId "
			+ "WHERE ur.pk.roleId = 4 AND u.aClass IN :classList "
			+ "AND u.fullName LIKE %:fullName% AND u.aClass.className LIKE %:className% "
			+ "AND u.aClass.grade = :grade")
	Page<User> findStudentByListClassAndGrade(@Param("classList") Collection<Class> classList,
			@Param("fullName") String fullName, @Param("className") String className, @Param("grade") Integer grade,
			Pageable pageable);

	@Query(value = "SELECT u " + "FROM User u " + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
			+ "INNER JOIN Role r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo ui ON ui.id = u.userInfo.id "
			+ "WHERE ur.pk.roleId = 4 AND u.aClass IS NULL")
	List<User> getAllStudentNotClass();

	@Query(value = "SELECT u " + "FROM User u " + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
			+ "INNER JOIN Role r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo ui ON ui.id = u.userInfo.id "
			+ "WHERE ur.pk.roleId = 4 AND u.aClass IS NULL " + "AND ui.admissionYear = :admissionYear - :grade + 10")
	List<User> getAllStudentNotClassByAdmissionYear(@Param("admissionYear") int admissionYear,
			@Param("grade") int grade);

	@Query(value = "SELECT COUNT(u.id) " + "FROM user AS u " + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
			+ "INNER JOIN role AS r ON r.id = ur.role_id " + "INNER JOIN user_info AS ui ON ui.id = u.user_info_id "
			+ "WHERE ur.role_id = 4 AND ui.status = 1", nativeQuery = true)
	int countAllStudent();

	@Query(value = "SELECT new com.schoolmanagement.model.request.MarkRequest(u.id , u.fullName , u.dob , u.address , m.coefficient , m.id) "
			+ "FROM User u " + "JOIN Mark m ON u.id = m.studentId " + "WHERE m.type = :type "
			+ "AND m.semester = :semester " + "AND m.subjectId = :subjectId " + "AND u.aClass.id = :classId")
	List<MarkRequest> findAllStudentHasMark(@Param("type") int type, @Param("semester") int semester,
			@Param("subjectId") int subjectId, @Param("classId") int classId);

	@Query(value = "SELECT COUNT(u.id) " + "FROM user AS u " + "INNER JOIN user_role AS ur ON u.id = ur.user_id  "
			+ "inner join role as r on r.id = ur.role_id  " + "INNER JOIN user_info AS ui ON u.user_info_id = ui.id "
			+ "WHERE ur.role_id = 4 AND ui.admission_year = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))", nativeQuery = true)
	int countAllNewStudentLastYear();

	@Query(value = "SELECT COUNT(u.id) " + "FROM user AS u " + "INNER JOIN user_role AS ur ON u.id = ur.user_id  "
			+ "inner join role as r on r.id = ur.role_id  " + "INNER JOIN user_info AS ui ON u.user_info_id = ui.id "
			+ "WHERE ur.role_id = 4 AND ui.admission_year = YEAR(CURDATE())", nativeQuery = true)
	int countAllNewStudentThisYear();

	@Query(value = "SELECT COUNT(u.id) " + "FROM user AS u " + "INNER JOIN user_role AS ur ON u.id = ur.user_id  "
			+ "inner join role as r on r.id = ur.role_id  " + "INNER JOIN user_info AS ui ON u.user_info_id = ui.id "
			+ "INNER JOIN class AS c ON c.id = ui.class_id "
			+ "WHERE ur.role_id = 4 AND c.grade = 12 AND ui.status = 3 "
			+ "AND ui.graduate_year = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))", nativeQuery = true)
	int countAllStudentGraduateLastYear();

	@Query(value = "SELECT COUNT(u.id) " + "FROM user AS u " + "INNER JOIN user_role AS ur ON u.id = ur.user_id  "
			+ "inner join role as r on r.id = ur.role_id  " + "INNER JOIN user_info AS ui ON u.user_info_id = ui.id "
			+ "INNER JOIN class AS c ON c.id = ui.class_id "
			+ "WHERE ur.role_id = 4 AND c.grade = 12 AND ui.status = 2 "
			+ "AND ui.graduate_year = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))", nativeQuery = true)
	int countAllStudentAbsentLastYear();

	
	@Query(value ="SELECT s FROM User s WHERE s.aClass.id = (:classId)")
	Page<User> findByIdClass(@Param("classId") int id , Pageable pageable);
	
	
}
