package com.schoolmanagement.validation;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentDobValidator implements ConstraintValidator<StudentDob, LocalDate> {

  @Override
  public boolean isValid(LocalDate localDate,
      ConstraintValidatorContext constraintValidatorContext) {
    if (localDate == null) {
      return true;
    }

    return LocalDate.now().getYear() - localDate.getYear() >= 16;
  }
}
