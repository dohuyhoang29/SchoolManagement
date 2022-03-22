package com.schoolmanagement.validation;

import com.schoolmanagement.model.User;
import com.schoolmanagement.service.TeacherService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicateEmailValidator implements ConstraintValidator<DuplicateEmail, String> {
  @Autowired
  private TeacherService teacherService;

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (teacherService == null) {
      return true;
    }

    return teacherService.getUserByEmail(s) == null;
  }
}
