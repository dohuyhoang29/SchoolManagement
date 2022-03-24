package com.schoolmanagement.validation;

import com.schoolmanagement.model.User;
import com.schoolmanagement.service.UserService;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicateEmailValidator implements ConstraintValidator<DuplicateEmail, Object> {

  @Autowired
  private UserService userService;
  @Autowired
  private ModelMapper modelMapper;

  private String message;

  @Override
  public void initialize(DuplicateEmail constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
    User user = modelMapper.map(obj, User.class);
    User check = userService.getUserByEmail(user.getEmail());
    boolean isValid = false;
    if (userService == null) {
      return true;
    }
    if (user.getId() == null) {
      if (check == null) {
        return true;
      }
    } else {
      if (check != null) {
        isValid = Objects.equals(check.getId(), user.getId());
      }
    }

    if (!isValid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext
          .buildConstraintViolationWithTemplate(message)
          .addPropertyNode("email").addConstraintViolation();
    }

    return isValid;
  }
}
