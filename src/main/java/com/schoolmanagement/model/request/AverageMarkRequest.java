package com.schoolmanagement.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AverageMarkRequest {
  private Integer studentId;
  private Integer semester;
  private Double coefficient;
}
