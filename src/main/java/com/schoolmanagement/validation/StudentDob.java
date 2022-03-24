package com.schoolmanagement.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentDobValidator.class)
@Documented
public @interface StudentDob {
  String message() default "Student must be 16 year old";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
