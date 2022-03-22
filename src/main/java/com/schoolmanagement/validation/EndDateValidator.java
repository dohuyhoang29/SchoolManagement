package com.schoolmanagement.validation;

import com.schoolmanagement.model.request.InsertTeacherRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndDateValidator implements ConstraintValidator<EndDateValid, Object> {

  private String message;

  @Override
  public void initialize(EndDateValid constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    final InsertTeacherRequest insertTeacherRequest = (InsertTeacherRequest) o;

    boolean isValid = true;
    if (insertTeacherRequest.getUserInfo().getStartDate() != null && insertTeacherRequest.getUserInfo().getEndDate() != null) {
      isValid = insertTeacherRequest.getUserInfo().getEndDate().getYear() - insertTeacherRequest.getUserInfo().getStartDate().getYear() > 0;
    }

    if (!isValid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext
          .buildConstraintViolationWithTemplate( message )
          .addPropertyNode( "userInfo.endDate" ).addConstraintViolation();
    }

    return isValid;
  }
}
