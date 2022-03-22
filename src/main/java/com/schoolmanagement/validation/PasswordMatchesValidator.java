package com.schoolmanagement.validation;

import com.schoolmanagement.model.request.InsertTeacherRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
  private String message;

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    final InsertTeacherRequest insertTeacherRequest = (InsertTeacherRequest) o;
    boolean isValid = insertTeacherRequest.getPassword().equalsIgnoreCase(insertTeacherRequest.getConfirmPassword());

    if (!isValid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext
          .buildConstraintViolationWithTemplate( message )
          .addPropertyNode( "confirmPassword" ).addConstraintViolation();
    }

    return isValid;
  }
}
