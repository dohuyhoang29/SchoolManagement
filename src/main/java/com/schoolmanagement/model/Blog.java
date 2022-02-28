package com.schoolmanagement.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blog")
public class Blog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotEmpty(message = "enter header ")
  @Column(name = "header")
  private String header;

  @NotEmpty(message = "enter content Blog")
  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "created_date")
  private Date createdDate;

  @Column(name = "updated_date")
  private Date updatedDate;
}
