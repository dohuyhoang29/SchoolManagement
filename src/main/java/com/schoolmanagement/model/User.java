package com.schoolmanagement.model;

import java.time.LocalDateTime;
import java.util.Date;
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

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer userID;

  @NotEmpty(message = "Enter full name")
  @Column(name = "full_name")
  private String fullName;

  @NotEmpty(message = "Enter username")
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  @NotEmpty(message = "Enter password")
  private String password;

  @Column(name = "email")
  @NotEmpty(message = "Enter email")
  private String email;

  @Column(name = "phone")
  @NotEmpty(message = "Enter phone number")
  private String phone;

  @Column(name = "dob")
  @NotNull(message = "Enter date of birth")
  private Date dob;

  @Column(name = "address")
  @NotEmpty(message = "Enter address")
  private String address;

  @Column(name = "start_date")
  @NotNull(message = "Enter start date")
  private Date startDate;

  @Column(name = "end_date")
  @NotNull(message = "Enter end date")
  private Date endDate;

  @Column(name = "deleted")
  private Boolean deleted;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

  @Column(name = "image")
  @NotEmpty(message = "Choose a image")
  private String image;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "teacher_subjects",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id"))
  private Set<Subjects> subjects = new HashSet<>();

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

  public void addRole(Role role){
    this.roles.add(role);
  }
}
