package com.schoolmanagement.service;

import com.schoolmanagement.model.Blog;

public interface BlogService {
  void SaveBlog(Blog blog);

  Iterable<Blog> FindAllBlog();

  Blog findByIdBlog(int id);
}
