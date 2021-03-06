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
@Constraint(validatedBy = AdmissionYearValidator.class)
@Documented
public @interface AdmissionYearValid {
  String message() default "Student must be 16 year old from dob to admission year";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
