package com.schoolmanagement.model.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherManagementRequest {
  private Integer id;
  private String fullName;
  private String email;
  private String phone;
  private String address;
  private LocalDate startDate;
  private LocalDate endDate;
  private Boolean deleted;
  private String image;
  private TeacherInfoRequest userInfo;

  public String getTeacherImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/teacher_image/" + image;
  }
}
