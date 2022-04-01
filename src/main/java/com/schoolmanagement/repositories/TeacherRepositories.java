package com.schoolmanagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanagement.model.User;

public interface TeacherRepositories extends PagingAndSortingRepository<User, Integer> {

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "WHERE ur.pk.roleId = 2 AND u.fullName LIKE %:fullName%")
	Page<User> searchUsersByFullName(@Param("fullName") String fullName, Pageable pageable);

	@Query(value = "SELECT u " + "FROM User AS u " + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
			+ "INNER JOIN Role AS r ON r.id = ur.pk.roleId " + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
			+ "WHERE ur.pk.roleId = 2 AND u.fullName LIKE %:fullName% AND ui.deleted = :deleted")
	Page<User> searchUsersByFullNameAndDeleted(@Param("fullName") String fullName, @Param("deleted") Boolean deleted,
			Pageable pageable);

	@Query(value = "SELECT COUNT(u.id) " + "FROM user AS u " + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
			+ "INNER JOIN role AS r ON r.id = ur.role_id " + "INNER JOIN user_info AS ui ON ui.id = u.user_info_id "
			+ "WHERE ur.role_id = 2 AND ui.deleted = 0", nativeQuery = true)
	int countAllTeacher();

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u SET u.userInfo.deleted = :deleted WHERE u.id = :id")
	void changeDeleted(@Param("deleted") Boolean deleted, @Param("id") Integer id);
}
