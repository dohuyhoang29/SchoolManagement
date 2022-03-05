package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Blog;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepositories extends PagingAndSortingRepository<Blog , Integer> {

	 Blog findById(int idneed);
}
