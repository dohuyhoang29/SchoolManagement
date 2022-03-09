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

  private String fullName;

  private String username;

  private String password;

  @NotEmpty(message = "Enter email")
  private String email;

  private String phone;

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
  private String image;

  public String getUserImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/user_image/" + image;
  }


}
