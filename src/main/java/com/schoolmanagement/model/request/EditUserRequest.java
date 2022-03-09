package com.schoolmanagement.model.request;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
public class EditUserRequest {

  private Integer id;

  @NotEmpty(message = "Enter full Name")
  private String fullName;

  @NotEmpty(message = "Enter User Name")
  private String username;

  @NotEmpty(message = "Enter email")
  private String email;

  @NotEmpty(message = "Enter password")
  private String password;

  @NotEmpty(message = "Enter phone number")
  private String phone;

  @NotNull(message = "Enter date of birth")
  private LocalDate dob;

  @NotEmpty(message = "Enter address")
  private String address;

  @NotNull(message = "Enter start date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate startDate;

  @NotNull(message = "Enter end date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate endDate;

  @NotNull(message = "Choose status")
  private Boolean deleted;

  @Column(name = "image")
  @NotEmpty(message = "Choose a image")
  private String image;

  public String getUserImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/user_image/" + image;
  }


}
