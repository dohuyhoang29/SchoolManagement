package com.schoolmanagement.controller.admin;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.BlogService;
import com.schoolmanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class BlogController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private UserService userService;
	
	@GetMapping("show/blog")
	public String IndexBlog(Model model, Authentication authentication) {
		LocalDate date = LocalDate.now();
		Iterable<Blog> blogs = blogService.FindAllBlog();
		model.addAttribute("blogs", blogs);
		model.addAttribute("datenow", date);
		
		return "/admin/blog/blog_manage";
	}
	
	@GetMapping("/insert/blog")
	public String InsertBlog(Model model) {
		User user = userService.findbyUserid(8);
		model.addAttribute("blogs", new Blog());
		model.addAttribute("users", user);

		return "/admin/blog/form_blog";
	}
	
	@PostMapping("/save/blog")
	public String save(Model model, @Valid Blog blogs) {
	
		blogs.setCreatedDate(LocalDateTime.now());
		
		
		blogs.setUpdatedDate(LocalDateTime.now());
		blogService.SaveBlog(blogs);

		return "redirect:/insert/blog";
	}
//    
	@GetMapping("/edit/blog/{id}")
	public String Edit(Model model , @PathVariable("id") int id) {
		Blog blogs = blogService.findByIdBlog(id);
		User user = userService.findbyUserid(blogs.getUser().getId());
		model.addAttribute("blogs", blogs);
		model.addAttribute("users", user);

		return "/admin/blog/form_blog";
	}
	
	@GetMapping("/detail/blog/{id}")
	public String ViewDetail(Model model , @PathVariable("id")  int id) {
		Blog blogs = blogService.findByIdBlog(id);
	
		model.addAttribute("blogs", blogs);
		
		
		return "/admin/blog/blog_detail";
	}
}