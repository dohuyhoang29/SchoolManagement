package com.schoolmanagement.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AttachmentController {

	@PostMapping("/image")
	@ResponseBody
	public String handlerFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		try {
			
			String root = "src/main/";
			String folder = "upload/image/blog_image/";
			String org_filename = file.getOriginalFilename();
			String str_filename = UUID.randomUUID().toString() + org_filename;

			if (!Files.isDirectory(Paths.get(root + folder))) {
				Files.createDirectories(Paths.get(root + folder));
			}
			
			Files.copy(file.getInputStream(), Paths.get(root + folder + str_filename));

			
			return "/" + folder + str_filename;
			
		} catch (Exception e) {
			e.printStackTrace();

			return "null";
		}
	}
	
	
	
	@PostMapping("/delete/image")
	@ResponseBody
	public void HandlerDeleteFIle(@RequestParam("src") String path) {
		String paths = path.replaceAll("http://localhost:8080", "");
		String root = "src/main";
		
		try {
			
			File fileToDelete = FileUtils.getFile(root + paths);
			boolean succes = FileUtils.deleteQuietly(fileToDelete);
			
			if(succes) {
				System.out.println("done!");
			}else {
				System.out.println("False!");
			}
			
		} catch (Exception e) {
			
		}
		
	}
}
