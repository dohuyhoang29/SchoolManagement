package com.schoolmanagement.validation;

import com.schoolmanagement.model.request.InsertTeacherRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Autowired
  private ModelMapper modelMapper;

  private String message;

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    final InsertTeacherRequest insertTeacherRequest = modelMapper.map(o,
        InsertTeacherRequest.class);
    boolean isValid = insertTeacherRequest.getPassword()
        .equalsIgnoreCase(insertTeacherRequest.getConfirmPassword());

    if (!isValid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext
          .buildConstraintViolationWithTemplate(message)
          .addPropertyNode("confirmPassword").addConstraintViolation();
    }

    return isValid;
  }
}
