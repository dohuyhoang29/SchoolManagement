package com.schoolmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "dob")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate dob;

	@Column(name = "address")
	private String address;

	@Column(name = "status")
	private Integer status;

	@Column(name = "image")
	private String image;

	@Column(name = "admission_year")
	private Integer admissionYear;

	@Column(name = "graduate_year")
	private Integer graduateYear;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private Class aClass;

	
	@OneToMany(mappedBy = "students")
	List<Mark> mark = new ArrayList<>();
	
	
//	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//	@JoinTable(name = "student_evaluate", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
//	private Set<Class> studentClass = new HashSet<>();

	@Transient
	public String getStudentImagePath() {
		if (image == null || id == null) {
			return null;
		}

		return "/images/student-images/" + image;
	}
	
	
}
