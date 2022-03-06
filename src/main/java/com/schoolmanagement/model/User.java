package com.schoolmanagement.model;

import com.schoolmanagement.validation.UniqueEmail;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotEmpty(message = "Enter full name")
  @Column(name = "full_name", nullable = false)
  private String fullName;

  @NotEmpty(message = "Enter username")
  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  @NotEmpty(message = "Enter password")
  private String password;

  @Column(name = "email", nullable = false)
  @NotEmpty(message = "Enter email")
  @UniqueEmail(message = "Such email already exist!")
  private String email;

  @Column(name = "phone", nullable = false)
  @NotEmpty(message = "Enter phone number")
  private String phone;

  @Column(name = "dob", nullable = false)
  @NotNull(message = "Enter date of birth")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate dob;

  @Column(name = "address", nullable = false)
  @NotEmpty(message = "Enter address")
  private String address;

  @Column(name = "start_date", nullable = false)
  @NotNull(message = "Enter start date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  @NotNull(message = "Enter end date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate endDate;

  @Column(name = "deleted", nullable = false)
  @NotNull(message = "Choose status")
  private Boolean deleted;

  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false)
  private LocalDateTime updatedDate;

  @Column(name = "image", nullable = true)
  private String image;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Blog> blogs = new HashSet<>();

  @OneToOne(mappedBy = "user")
  private Class aClass;

  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Mark> marks = new HashSet<>();

  @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Mark> mark = new HashSet<>();

  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Mark> studentEvaluateCreate = new HashSet<>();

  @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Mark> studentEvaluateUpdate = new HashSet<>();

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "teacher_subjects",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id"))
  private Set<Subjects> subjects = new HashSet<>();

  public void addRole(Role role) {
    this.roles.add(role);
  }

  public String getUserImagePath() {
    if (image == null && id == null) {
      return null;
    }

    return "/upload/image/user_image/" + image;
  }

  public User(String fullName, String username, String password, String email, String phone,
      LocalDate dob, String address, LocalDate startDate, LocalDate endDate, boolean deleted,
      LocalDateTime createdDate, LocalDateTime updatedDate) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.dob = dob;
    this.address = address;
    this.startDate = startDate;
    this.endDate = endDate;
    this.deleted = deleted;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
  }
}
