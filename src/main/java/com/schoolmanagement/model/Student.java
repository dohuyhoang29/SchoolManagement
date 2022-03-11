package com.schoolmanagement.model;

import java.time.LocalDate;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

  @Column(name = "full_name", nullable = false)
  @NotEmpty(message = "Enter full name")
  private String fullName;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "dob", nullable = false)
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  @NotNull(message = "Enter Date of Birth")
  private LocalDate dob;

  @Column(name = "address")
  @NotEmpty(message = "Enter address")
  private String address;

  @Column(name = "status", nullable = false)
  @NotNull(message = "Choose a status")
  private Integer status;

  @Column(name = "image")
  private String image;

  @Column(name = "admission_year", nullable = false)
  @NotNull(message = "Enter admission year")
  private Integer admissionYear;

  @Column(name = "graduate_year", nullable = false)
  @NotNull(message = "Enter graduate year")
  private Integer graduateYear;

  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false)
  private LocalDateTime updatedDate;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @NotNull(message = "Choose a class")
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

    return "/upload/image/student_image/" + image;
  }

  public Student(String fullName, String username, String password, LocalDate dob,
      String address, Integer status, Integer admissionYear, Integer graduateYear,
      LocalDateTime createdDate, LocalDateTime updatedDate, Class aClass) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.dob = dob;
    this.address = address;
    this.status = status;
    this.admissionYear = admissionYear;
    this.graduateYear = graduateYear;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
    this.aClass = aClass;
  }
}
