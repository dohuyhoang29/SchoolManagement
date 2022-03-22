package com.schoolmanagement.model.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
public class TeacherInfoRequest {

  @NotNull(message = "Enter start date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  public LocalDate startDate;

  @NotNull(message = "Enter end date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  public LocalDate endDate;

  @NotNull(message = "Choose a status")
  public Boolean deleted;

}
