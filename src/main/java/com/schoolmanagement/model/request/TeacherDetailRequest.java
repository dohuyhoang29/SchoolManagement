package com.schoolmanagement.model.request;

import com.schoolmanagement.model.Subjects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDetailRequest {
  private Integer id;
  private String username;
  private String email;
  private String address;
  private String phone;
  private String fullName;
  private LocalDate dob;
  private Boolean deleted;
  private LocalDate startDate;
  private LocalDate endDate;
  private Set<Subjects> subjects = new HashSet<>();
  private String image;
  private TeacherInfoRequest userInfo;

  public String getTeacherImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/teacher_image/" + image;
  }
}
