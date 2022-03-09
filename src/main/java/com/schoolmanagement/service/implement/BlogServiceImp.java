package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.repositories.BlogRepositories;
import com.schoolmanagement.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImp implements BlogService {
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
