package com.schoolmanagement.model;

import com.schoolmanagement.validation.UniqueSubjectName;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

  @NotEmpty(message = "Enter subject name")
  @Column(name = "subject_name")
  @UniqueSubjectName(message = "Such subject name already exist!")
  private String subjectName;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "teacher_subjects",
      joinColumns = @JoinColumn(name = "subject_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> users = new HashSet<>();

	
	@OneToMany(mappedBy = "subjects", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ClassTeacherSubject> subjects = new ArrayList<>();
//	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//	@JoinTable(name = "mark", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
//	@MapKeyJoinColumn(name = "class_id")
//	private Map<Class, Student> studentClass = new HashMap<>();

  @Override
  public String toString() {
    return subjectName;
  }
}
