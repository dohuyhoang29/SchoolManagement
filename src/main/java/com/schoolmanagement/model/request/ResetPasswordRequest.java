package com.schoolmanagement.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordRequest {
  private Integer id;
  private String fullName;

  @NotEmpty
  private String newPassword;

  @NotEmpty
  private String confirmPassword;
}
