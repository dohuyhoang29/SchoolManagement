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
@Constraint(validatedBy = TeacherDobValidator.class)
@Documented
public @interface TeacherDob {
  String message() default "Teachers must be 22 years old";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
