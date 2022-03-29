package com.schoolmanagement.service.implement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

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
		return blogRepositories.findById(id);
	}

	@Override
	public Page<Blog> searchBlog(String search, String fromDate, String toDate,
			int currentPage, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ?sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(currentPage - 1, 10, sort);

		DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toFormatter();
		if (fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
			return blogRepositories.findBlogByHeader(search, pageable);
		}
		if (!fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
			return blogRepositories.findAllByHeaderAndFromDate(search, LocalDate.parse(fromDate, dateTimeFormatter), pageable);
		}
		if (fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
			return blogRepositories.findAllByHeaderAndToDate(search, LocalDate.parse(toDate, dateTimeFormatter), pageable);
		}
		if (!fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
			return blogRepositories.findAllByHeaderAndFromDateAndToDate(search, LocalDate.parse(fromDate, dateTimeFormatter), LocalDate.parse(toDate, dateTimeFormatter), pageable);
		}

		return null;
	}

	@Override
	public int countAllBlog() {
		return blogRepositories.countAll();
	}

	@Override
	public void DeleteBlog(int id) {
		Blog blog = blogRepositories.findById(id);
		
		blogRepositories.delete(blog);
	}

	@Override
	public Page<Blog> PagingBlogUserScreen(int page) {
		Pageable pageable = PageRequest.of(page -1, 9);
		
		return blogRepositories.findAll(pageable);
	}
}
