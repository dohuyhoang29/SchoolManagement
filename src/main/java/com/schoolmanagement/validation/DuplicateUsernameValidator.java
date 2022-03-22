package com.schoolmanagement.validation;

import com.schoolmanagement.service.TeacherService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicateUsernameValidator implements ConstraintValidator<DuplicateUsername, String> {
  @Autowired
  private TeacherService teacherService;


  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (teacherService == null) {
      return true;
    }

    return teacherService.getUserByUsername(s) == null;
  }
}
