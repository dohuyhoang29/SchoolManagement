package com.schoolmanagement.controller.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.service.BlogService;

@Controller
public class BlogUserController {
	@Autowired
	private BlogService  blogService;
	
	@GetMapping("/blog")
	public String IndexBlogUser(Model model ) {
		
		return PageIndextBlogUserScreen(model, 1);
	}
	
	@GetMapping("/blog/page/{pageNumber}")
	public String PageIndextBlogUserScreen(Model model , @PathVariable("pageNumber") int pageNumber) {
		Page<Blog>  pageBlog = blogService.PagingBlogUserScreen(pageNumber , 9);
		
		int totalPages = pageBlog.getTotalPages();
		
		List<Blog> listBlog = pageBlog.getContent();
		
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("listBlog", listBlog);
		
		return "/user/blog";
	}
	
	@GetMapping("/blog/detail/{id}")
	public String BlogViewDetail(Model model , @PathVariable("id") int blogId) {
		Blog blog = blogService.findByIdBlog(blogId);
		
		Page<Blog>  pageBlog = blogService.PagingBlogUserScreen(1 , 4);
		
		List<Blog> listBlog = pageBlog.getContent();
		
		List<Blog> list = new ArrayList<>();
		
		for(int i =0 ; i < listBlog.size(); i ++) {
			if(listBlog.get(i).getId() == blog.getId()) {
				
			}else {
				list.add(listBlog.get(i));
			}
		}
		
		if(list.size() > 3) {
			list.remove(list.get(3));
		}
		
		model.addAttribute("blog", blog);
		model.addAttribute("listBlog", list);
		
		
		return "/user/blog_detail";
	}
}
