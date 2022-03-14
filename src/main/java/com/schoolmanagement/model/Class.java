package com.schoolmanagement.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Student> students = new HashSet<>();

	@OneToMany(mappedBy = "theClass", fetch = FetchType.LAZY)
	private Set<ClassTeacherSubject> teacherSubjects = new HashSet<>();
}
