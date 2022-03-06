package com.schoolmanagement.controller.admin;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class AttachmentController {

  @PostMapping("/image")
  @ResponseBody
  public String handlerFileUpload(@RequestParam("file") MultipartFile file,
      HttpServletRequest request)
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
}
