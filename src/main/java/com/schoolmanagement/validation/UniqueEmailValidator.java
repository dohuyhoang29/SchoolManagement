package com.schoolmanagement.validation;

import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.UserRepositories;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, User> {

  @Autowired
  private UserRepositories repositories;

  @Override
  public void initialize(UniqueEmail constraintAnnotation) {
  }

  @Override
  public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
    if (repositories == null) {
      return true;
    }


    return false;
  }
}
