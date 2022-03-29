package com.schoolmanagement.controller.admin;

import com.schoolmanagement.service.BlogService;
import com.schoolmanagement.service.TeacherService;
import com.schoolmanagement.service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.schoolmanagement.model.Blog;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.implement.BlogServiceImp;
import com.schoolmanagement.service.implement.TeacherServiceImp;


@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;

	@GetMapping("/show/blog")
	public String IndexBlog(Model model, Authentication authentication) {
		return showBlogByPage(model, 1, "", "", "id", "asc", "");
	}

	// paging
	@GetMapping("/show/blog/{page}")
	public String showBlogByPage(Model model, @PathVariable("page") Integer currentPage,
			@Param("search") String search, @Param("fromDate") String fromDate,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("toDate") String toDate) {
		Page<Blog> page = blogService.searchBlog(search, fromDate, toDate,currentPage, sortField, sortDir);

		long totalPages = page.getTotalPages();
		List<Blog> list = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("search", search);
		model.addAttribute("sortField", sortField);
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
	public String save(Model model, @Valid Blog blogs, Authentication authentication,
			@RequestParam("fileImages") MultipartFile multipartFile) throws IOException {
		
		try {
			// copy image
			String root = "src/main/";
			String folder = "upload/image/blog_image/cover_image/";
			String org_filename = multipartFile.getOriginalFilename();
			String str_filename = "";

			if (org_filename != null && !org_filename.isEmpty()) {
				str_filename = UUID.randomUUID() + org_filename.substring(org_filename.lastIndexOf('.'));

				if (!Files.exists(Paths.get(root + folder))) {
					Files.createDirectories(Paths.get(root + folder));
				}
				Files.copy(multipartFile.getInputStream(), Paths.get(root + folder + str_filename),
						StandardCopyOption.REPLACE_EXISTING);

				blogs.setImages(str_filename);
			}

			User user = userService.getUserByUsername(authentication.getName());
			blogs.setUser(user);
			blogs.setCreatedDate(LocalDate.now());
			blogs.setUpdatedDate(LocalDateTime.now());
			blogService.SaveBlog(blogs);

			return "redirect:/show/blog";
		} catch (Exception e) {
			// TODO: handle exception
			return "/admin/blog/form_blog";
		}



	}

//  Edit
	@GetMapping("/edit/blog/{id}")
	public String Edit(Model model , @PathVariable("id") int id) {
		Blog blogs = blogService.findByIdBlog(id);
		User user = teacherService.findByUserId(blogs.getUser().getId());
		model.addAttribute("blogs", blogs);
		model.addAttribute("users", user);

		return "/admin/blog/form_blog";
	}
// Delete
	@GetMapping("/delete/blog/{id}")
	public String DeleteBlog(@PathVariable("id") int blogId) {

		blogService.DeleteBlog(blogId);

		return "redirect:/show/blog";
	}

	// detail
	@GetMapping("/detail/blog/{id}")
	public String ViewDetail(Model model , @PathVariable("id")  int id) {
		Blog blogs = blogService.findByIdBlog(id);
	
		model.addAttribute("blogs", blogs);

		return "/admin/blog/blog_detail";
	}

	// search
	@GetMapping("/show/blog/search")
	public String searchBlog(@RequestParam(value = "search") String search,
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate, Model model){
		return showBlogByPage(model, 1, search, fromDate, "id", "asc", toDate);
	}
}
