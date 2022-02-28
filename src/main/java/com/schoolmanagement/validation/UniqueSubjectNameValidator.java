package com.schoolmanagement.validation;

import com.schoolmanagement.model.Subjects;
import com.schoolmanagement.repositories.SubjectRepositories;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueSubjectNameValidator implements ConstraintValidator<UniqueSubjectName, String> {

  @Autowired
  private SubjectRepositories repositories;

  @Override
  public void initialize(UniqueSubjectName constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (repositories == null) {
      return true;
    }

    List<Subjects> list = (List<Subjects>) repositories.findAllBySubjectName(s);
    return list.size() <= 0 && s != null;
  }
}
