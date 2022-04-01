package com.schoolmanagement.model.request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.schoolmanagement.validation.AdmissionYearValid;
import com.schoolmanagement.validation.DuplicateEmail;
import com.schoolmanagement.validation.GraduateYearValid;
import com.schoolmanagement.validation.StudentDob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DuplicateEmail
@AdmissionYearValid
@GraduateYearValid
public class StudentRequest {

  private Integer id;

  @NotEmpty(message = "Enter full name")
  private String fullName;

  @NotEmpty(message = "Enter email")
  private String email;

  @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid phone number")
  @NotEmpty(message = "Enter phone number")
  private String phone;

  @NotNull(message = "Enter date of birth")
  @StudentDob
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate dob;

  @NotEmpty(message = "Enter address")
  private String address;

  private String image;

  @Valid
  public StudentInfoRequest userInfo;

  public String getStudentImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/student_image/" + image;
  }

  public void setImage(MultipartFile multipartFile, String folderSrc) throws IOException {
    String root = "src/main/";
    String imageTeacher = multipartFile.getOriginalFilename();
    String str_filename = "";
    if (imageTeacher != null && !imageTeacher.isEmpty()) {
      str_filename = UUID.randomUUID() + imageTeacher.substring(imageTeacher.lastIndexOf('.'));

      if (!Files.exists(Paths.get(root + folderSrc))) {
        Files.createDirectories(Paths.get(root + folderSrc));
      }
      Files.copy(multipartFile.getInputStream(), Paths.get(root + folderSrc + str_filename),
          StandardCopyOption.REPLACE_EXISTING);

      this.image = str_filename;
    }
  }
}
