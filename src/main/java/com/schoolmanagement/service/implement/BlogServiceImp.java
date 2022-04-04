package com.schoolmanagement.service.implement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.repositories.BlogRepositories;
import com.schoolmanagement.service.BlogService;

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
		
		return blogRepositories.findAllBlog();
	}

	@Override
	public Blog findByIdBlog(int id) {
		
		return blogRepositories.findById(id).get();
	}

	@Override
	public Page<Blog> searchBlog(String search, String fDate, String tDate,
			int currentPage, String sortField, String sortDir) {
		
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ?sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(currentPage - 1, 12, sort);

		LocalDate fromDate = LocalDate.parse(fDate.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		LocalDate toDate = LocalDate.parse(tDate.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));



		Page<Blog> pageBlog = null;

		if (fromDate == null && toDate == null) {
			
			pageBlog = blogRepositories.findBlogByHeader(search, pageable);
		}

		if (fromDate != null && toDate == null) {
			
			pageBlog = blogRepositories.findAllByHeaderAndFromDate(search, fromDate, pageable);
		}

		if (fromDate == null && toDate != null) {
			
			pageBlog = blogRepositories.findAllByHeaderAndToDate(search, toDate, pageable);
		}

		if (fromDate != null && toDate != null) {
			
			pageBlog = blogRepositories.findAllByHeaderAndFromDateAndToDate(search, fromDate, toDate, pageable);
		}

		return pageBlog ;
	}

	@Override
	public int countAllBlog() {
		
		return blogRepositories.countAll();
	}

	@Override
	public void DeleteBlog(int id) {
		Blog blog = blogRepositories.findById(id).get();
		
		blogRepositories.delete(blog);
	}

	@Override
	public Page<Blog> PagingBlogUserScreen(int page , int size) {
		Pageable pageable = PageRequest.of(page -1, size);
		
		return blogRepositories.findAllByBlogNew(pageable);
	}
}
