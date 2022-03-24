package com.schoolmanagement.model.request;

import com.schoolmanagement.validation.DuplicateEmail;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DuplicateEmail
public class EditTeacherRequest {

  private Integer id;

  @NotEmpty(message = "Enter username")
  private String username;

  @NotEmpty(message = "Enter email")
  private String email;

  @NotEmpty(message = "Enter full Name")
  private String fullName;

  @NotEmpty(message = "Enter phone number")
  private String phone;

  @NotNull(message = "Enter date of birth")
  private LocalDate dob;

  @NotEmpty(message = "Enter address")
  private String address;

  @Valid
  private TeacherInfoRequest userInfo;

  @NotEmpty(message = "Choose a image")
  private String image;

  public String getTeacherImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/teacher_image/" + image;
  }


}
