package com.schoolmanagement.repositories;

import com.schoolmanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;
import java.util.List;

@Repository
public interface UserRepositories extends PagingAndSortingRepository<User, Integer> {

	@Query(value = "SELECT u FROM User u WHERE u.email = :email")
	User findAllByEmail(@Param("email") String email);

	@Query(value = "SELECT u FROM User u WHERE u.username = :username")
	User findUserByUsername(String username);

	@Query(value = "SELECT u FROM User u WHERE u.fullName LIKE %:fullName%")
	Page<User> searchUsersByFullName(@Param("fullName") String fullName, Pageable pageable);

	@Query(value = "SELECT u FROM User u WHERE u.fullName LIKE %:fullName% AND u.deleted = :deleted")
	Page<User> searchUsersByFullNameAndDeleted(@Param("fullName") String fullName, @Param("deleted") Boolean deleted,
			Pageable pageable);

	User findById(int integer);

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u SET u.deleted = :deleted WHERE u.id = :id")
	void changeDeleted(@Param("deleted") Boolean deleted, @Param("id") Integer id);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM  user_role WHERE role_id = :role_id AND user_id = :user_id", nativeQuery = true )
	void deleterole(@Param("role_id") int roleid , @Param("user_id") int userid);
	
	
}
