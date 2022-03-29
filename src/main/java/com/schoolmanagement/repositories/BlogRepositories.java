package com.schoolmanagement.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schoolmanagement.model.Blog;

@Repository
public interface BlogRepositories extends PagingAndSortingRepository<Blog, Integer> {
	Blog findById(int id);

	@Query(value="SELECT b FROM Blog b ORDER BY b.updatedDate DESC")
	Iterable<Blog> findAllBlog();
	
	@Query(value = "SELECT b FROM Blog b WHERE b.header LIKE %:header%")
	Page<Blog> findBlogByHeader(@Param("header") String header, Pageable pageable);

	@Query(value = "SELECT b FROM Blog b WHERE b.header LIKE %:header% AND b.createdDate >= :createdDate")
	Page<Blog> findAllByHeaderAndFromDate(@Param("header") String header, @Param("createdDate") LocalDate fromDate,
			Pageable pageable);

	@Query(value = "SELECT b FROM Blog b WHERE b.header LIKE %:header% AND b.createdDate <= :createdDate")
	Page<Blog> findAllByHeaderAndToDate(@Param("header") String header, @Param("createdDate") LocalDate createdDate,
			Pageable pageable);

	@Query(value = "SELECT b FROM Blog b WHERE b.header LIKE %:header% AND b.createdDate BETWEEN :createdDate AND :updatedDate")
	Page<Blog> findAllByHeaderAndFromDateAndToDate(@Param("header") String header,
			@Param("createdDate") LocalDate createdDate, @Param("updatedDate") LocalDate updatedDate,
			Pageable pageable);

	@Query(value = "SELECT COUNT (b.id) FROM Blog b")
	int countAll();
}
