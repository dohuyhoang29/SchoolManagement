package com.schoolmanagement.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.schoolmanagement.repositories.UserRepositories;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  @Autowired
  private UserRepositories repositories;

  @Override
  public void initialize(UniqueEmail constraintAnnotation) {
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (repositories == null) {
      return true;
    }

    return repositories.findAllByEmail(s) == null && s != null;
  }
}
