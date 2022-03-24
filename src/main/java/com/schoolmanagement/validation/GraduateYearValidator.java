package com.schoolmanagement.validation;

import com.schoolmanagement.model.User;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class GraduateYearValidator implements ConstraintValidator<GraduateYearValid, Object> {

  @Autowired
  private ModelMapper modelMapper;

  private String message;

  @Override
  public void initialize(GraduateYearValid constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    User user = modelMapper.map(o, User.class);
    boolean isValid = true;

    if (user.getUserInfo().getAdmissionYear() == null || user.getUserInfo().getGraduateYear() == null) {
      return true;
    }

    isValid = user.getUserInfo().getAdmissionYear() < user.getUserInfo().getGraduateYear();

    if (!isValid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext.buildConstraintViolationWithTemplate(message)
          .addPropertyNode("userInfo.graduateYear").addConstraintViolation();
    }

    return isValid;
  }
}
