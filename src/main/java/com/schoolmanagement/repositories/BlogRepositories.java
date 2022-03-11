package com.schoolmanagement.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.schoolmanagement.model.Blog;

@Repository
public interface BlogRepositories extends PagingAndSortingRepository<Blog , Integer> {

	 Blog findById(int idneed);
}
