package com.schoolmanagement.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UserRolePk implements Serializable {

  private static final long serialVersionUID = 8041312196804147912L;

  @Column(name = "role_id", insertable = false, updatable = false)
  private Integer roleId;

  @Column(name = "user_id", insertable = false, updatable = false)
  private Integer userId;
}
