package com.schoolmanagement.validation;

import com.schoolmanagement.model.request.InsertTeacherRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartDateValidator implements ConstraintValidator<StartDateValid, Object> {

  private String message;

  @Override
  public void initialize(StartDateValid constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    InsertTeacherRequest insertTeacherRequest = (InsertTeacherRequest) o;
    boolean isValid;
    if (insertTeacherRequest.getDob() != null && insertTeacherRequest.getUserInfo().getStartDate() != null) {
      isValid = insertTeacherRequest.getUserInfo().getStartDate().getYear() - insertTeacherRequest.getDob().getYear() >= 22;
    } else {
      isValid = true;
    }

    if (!isValid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext
          .buildConstraintViolationWithTemplate( message )
          .addPropertyNode( "userInfo.startDate" ).addConstraintViolation();
    }

    return isValid;
  }
}
