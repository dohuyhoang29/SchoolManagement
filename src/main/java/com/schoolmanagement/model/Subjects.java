package com.schoolmanagement.model;

import java.util.HashSet;
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

import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
public class Subjects {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "subject_name")
  private String subjectName;

  @ManyToMany
  @JoinTable(name = "class_teacher_subject",
      joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id", updatable=false, nullable=false),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", updatable=false, nullable=false))
  private Set<Class> classes = new HashSet<>();
}
