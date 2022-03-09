package com.schoolmanagement.service;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.repositories.BlogRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImp implements BlogService{
	@Autowired
	private BlogRepositories blogRepositories;

	@Override
	public void SaveBlog(Blog blog) {
		blogRepositories.save(blog);
	}

	@Override
	public Iterable<Blog> FindAllBlog(){
		return blogRepositories.findAll();
	}

	@Override
	public Blog findByIdBlog(int id) {
		return blogRepositories.findById(id);
	}
}
