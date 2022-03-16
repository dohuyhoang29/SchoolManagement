package com.schoolmanagement.service.implement;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.repositories.BlogRepositories;
import com.schoolmanagement.service.BlogService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
}
