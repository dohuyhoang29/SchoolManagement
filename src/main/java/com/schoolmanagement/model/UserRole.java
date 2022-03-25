package com.schoolmanagement.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_role")
@Data
public class UserRole {

  @EmbeddedId
  private UserRolePk pk;
}
