package com.schoolmanagement.controller.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.implement.BlogServiceImp;
import com.schoolmanagement.service.implement.TeacherServiceImp;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
	@Autowired
	private BlogServiceImp blogServiceImp;

	@Autowired
	private TeacherServiceImp teacherServiceImp;
	
	@GetMapping("/show/blog")
	public String IndexBlog(Model model, Authentication authentication) {
		return showBlogByPage(model, 1, "", "", "id", "asc", "");
	}

	@GetMapping("/show/blog/{page}")
	public String showBlogByPage(Model model, @PathVariable("page") Integer currentPage,
			@Param("search") String search, @Param("fromDate") String fromDate,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("toDate") String toDate) {
		Page<Blog> page = blogServiceImp.searchBlog(search, fromDate, toDate,currentPage, sortField, sortDir);

		long totalPages = page.getTotalPages();
		List<Blog> list = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("search", search);
		model.addAttribute("sortField", sortField	);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("blogs", list);

		String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);

		return "/admin/blog/blog_manage";
	}
	
	@GetMapping("/insert/blog")
	public String InsertBlog(Model model) {
		model.addAttribute("blogs", new Blog());

		return "/admin/blog/form_blog";
	}
	
	@PostMapping("/save/blog")
	public String save(Model model, @Valid Blog blogs , Authentication authentication) {
		
		User user = teacherServiceImp.getUserByUsername(authentication.getName());
		blogs.setUser(user);
		blogs.setCreatedDate(LocalDate.now());
		blogs.setUpdatedDate(LocalDateTime.now());
		blogServiceImp.SaveBlog(blogs);

		return "redirect:/show/blog";
	}
//    
	@GetMapping("/edit/blog/{id}")
	public String Edit(Model model , @PathVariable("id") int id) {
		Blog blogs = blogServiceImp.findByIdBlog(id);
		User user = teacherServiceImp.findByUserId(blogs.getUser().getId());
		model.addAttribute("blogs", blogs);
		model.addAttribute("users", user);

		return "/admin/blog/form_blog";
	}
	
	@GetMapping("/detail/blog/{id}")
	public String ViewDetail(Model model , @PathVariable("id")  int id) {
		Blog blogs = blogServiceImp.findByIdBlog(id);
	
		model.addAttribute("blogs", blogs);

		return "/admin/blog/blog_detail";
	}

	@GetMapping("/show/blog/search")
	public String searchBlog(@RequestParam(value = "search") String search,
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate, Model model){
		return showBlogByPage(model, 1, search, fromDate, "id", "asc", toDate);
	}
}
