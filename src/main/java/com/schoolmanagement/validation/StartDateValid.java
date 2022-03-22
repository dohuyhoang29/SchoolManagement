package com.schoolmanagement.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {ElementType.TYPE, ElementType.ANNOTATION_TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartDateValidator.class)
@Documented
public @interface StartDateValid {
  String message() default "Teacher must be 22 year old from dob to start date";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
