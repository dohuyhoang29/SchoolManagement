package com.schoolmanagement.model.request;

import com.schoolmanagement.validation.PasswordMatches;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatches
public class ResetPasswordRequest {
  @NotEmpty(message = "Enter password")
  private String password;

  @NotEmpty(message = "Enter confirm password")
  private String confirmPassword;
}
