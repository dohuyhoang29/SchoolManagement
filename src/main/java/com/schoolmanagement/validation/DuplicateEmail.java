package com.schoolmanagement.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateEmailValidator.class)
@Documented
public @interface DuplicateEmail {
  String message() default "An account already exists for this email.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
