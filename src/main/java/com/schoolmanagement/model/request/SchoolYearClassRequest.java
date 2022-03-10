package com.schoolmanagement.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchoolYearClassRequest {
  private Integer year;

  public SchoolYearClassRequest (Integer year) {
    this.year = year;
  }
}
