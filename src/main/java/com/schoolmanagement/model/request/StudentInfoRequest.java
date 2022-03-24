package com.schoolmanagement.model.request;

import com.schoolmanagement.model.Class;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentInfoRequest {

  @NotNull(message = "Enter admission Year")
  private Integer admissionYear;

  @NotNull(message = "Enter graduate Year")
  private Integer graduateYear;

  @NotNull(message = "Choose a status")
  private Integer status;
}
