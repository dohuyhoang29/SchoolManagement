package com.schoolmanagement.model.request;

import com.schoolmanagement.model.UserInfo;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
public class EditTeacherRequest {

  private Integer id;
  private String username;

  @NotEmpty(message = "Enter full Name")
  private String fullName;

  @NotEmpty(message = "Enter email")
  private String email;

  @NotEmpty(message = "Enter phone number")
  private String phone;

  @NotNull(message = "Enter date of birth")
  private LocalDate dob;

  @NotEmpty(message = "Enter address")
  private String address;

  private TeacherInfoRequest userInfo;

  @NotEmpty(message = "Choose a image")
  private String image;

  public String getUserImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/teacher_image/" + image;
  }


}
