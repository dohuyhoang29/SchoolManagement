package com.schoolmanagement.controller.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.service.implement.BlogServiceImp;

@Controller
public class BlogUserController {
	@Autowired
	private BlogServiceImp blogServiceImp;
	
	@GetMapping("/blog")
	public String IndexBlogUser(Model model ) {
		
		return PageIndextBlogUserScreen(model, 1);
	}
	
	@GetMapping("/blog/page/{pageNumber}")
	public String PageIndextBlogUserScreen(Model model , @PathVariable("pageNumber") int pageNumber) {
		Page<Blog>  pageBlog = blogServiceImp.PagingBlogUserScreen(pageNumber);
		
		int totalPages = pageBlog.getTotalPages();
		
		List<Blog> listBlog = pageBlog.getContent();
		
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("listBlog", listBlog);
		
		return "/user/blog";
	}
	
}
