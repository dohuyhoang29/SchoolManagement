package com.schoolmanagement.validation;

import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.UserRepositories;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

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
    List<User> list = (List<User>) repositories.findUserByEmail(s);
    return list.size() <= 0 && s != null;
  }
}
