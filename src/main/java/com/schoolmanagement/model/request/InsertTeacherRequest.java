package com.schoolmanagement.model.request;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.validation.DuplicateEmail;
import com.schoolmanagement.validation.DuplicateUsername;
import com.schoolmanagement.validation.EndDateValid;
import com.schoolmanagement.validation.PasswordMatches;
import com.schoolmanagement.validation.StartDateValid;
import com.schoolmanagement.validation.TeacherDob;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatches
@StartDateValid
@EndDateValid
public class InsertTeacherRequest {

  @NotEmpty(message = "Enter full name")
  private String fullName;

  @DuplicateUsername
  @NotEmpty(message = "Enter username")
  private String username;

  @NotEmpty(message = "Enter password")
  private String password;

  @NotEmpty(message = "Enter confirm password")
  private String confirmPassword;

  @DuplicateEmail
  @NotEmpty(message = "Enter email")
  private String email;

  @Pattern(regexp="(^$|[0-9]{10})", message = "Is valid phone number")
  @NotEmpty(message = "Enter phone number")
  private String phone;

  @NotNull(message = "Enter date of birth")
  @TeacherDob
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate dob;

  @NotEmpty(message = "Enter address")
  private String address;

  private LocalDateTime createdDate;

  private LocalDateTime updatedDate;

  private String image;

  @Valid
  public TeacherInfoRequest userInfo;

  private Set<Role> roles = new HashSet<>();

  public void addRole(Role role) {
    this.roles.add(role);
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
