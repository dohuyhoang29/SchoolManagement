package com.schoolmanagement.model.request;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.User;
import com.schoolmanagement.validation.UniqueSubjectName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SubjectRequest {

  @Entity
  @Table(name = "subjects")
  @Getter
  @Setter
  @NoArgsConstructor

  public class Subjects {

    private Integer id;

    @NotEmpty(message = "Enter subject name")
    private String subjectName;

    private List<User> users = new ArrayList<>();

  }

}
