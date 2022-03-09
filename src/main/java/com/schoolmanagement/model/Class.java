package com.schoolmanagement.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "class")
public class Class {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotEmpty(message = "Enter Class Name")
  @Length(min = 3, max = 10, message = "Class Name more than 3 and less than 10")
  @Column(name = "class_name")
  private String className;

  @NotNull(message = "Enter Grade")
  @Column(name = "grade")
  private Integer grade;

  @NotNull(message = "Enter School Year")
  @Column(name = "school_year")
  private Integer schoolYear;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "created_date")
  private Date createdDate;

  @Column(name = "updated_date")
  private Date updatedDate;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "student_class",
      joinColumns = @JoinColumn(name = "class_id"),
      inverseJoinColumns = @JoinColumn(name = "student_id"))
  private Set<Student> students = new HashSet<>();

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "class_teacher_subject",
      joinColumns = @JoinColumn(name = "class_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id"))

  @MapKeyJoinColumn(name = "user_id")
  private Map<User, Subjects> teacherSubjects = new HashMap<>();
}
