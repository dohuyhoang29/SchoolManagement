package com.schoolmanagement.model;

import java.time.LocalDate;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "admission_year")
  private Integer admissionYear;

  @Column(name = "graduate_year")
  private Integer graduateYear;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "class_id")
  private Class aClass;

  @Column(name = "status")
  private Integer status;

  @OneToMany(mappedBy = "students")
  List<Mark> mark = new ArrayList<>();

  @Column(name = "start_date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate startDate;

  @Column(name = "end_date")
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private LocalDate endDate;

  @Column(name = "deleted")
  private Boolean deleted;

  @OneToOne(mappedBy = "userInfo")
  private User user;
}
