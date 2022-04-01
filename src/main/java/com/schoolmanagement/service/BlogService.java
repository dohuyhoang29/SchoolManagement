package com.schoolmanagement.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.schoolmanagement.model.Blog;

public interface BlogService {
	void SaveBlog(Blog blog);

	Iterable<Blog> FindAllBlog();

	Blog findByIdBlog(int id);

	Page<Blog> searchBlog(String search, LocalDate fromDate, LocalDate toDate, int currentPage, String sortField,
			String sortDir);

	void DeleteBlog(int id);

	Page<Blog> PagingBlogUserScreen(int page , int size);

	int countAllBlog();
}
