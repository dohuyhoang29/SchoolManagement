package com.schoolmanagement.service;

import com.schoolmanagement.model.Blog;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

public interface BlogService {
  void SaveBlog(Blog blog);

  Iterable<Blog> FindAllBlog();

  Blog findByIdBlog(int id);

  Page<Blog> searchBlog(String search, String fromDate, String toDate, int currentPage, String sortField, String sortDir);
}
