package com.schoolmanagement.config;

import com.schoolmanagement.model.AccountDetails;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws ServletException, IOException {
    AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();

    String redirectURl = request.getContextPath();

    if (accountDetails.hasRole("ADMIN")) {
      redirectURl = "/dashboard";
    } else if (accountDetails.hasRole("TEACHER") || accountDetails.hasRole("HOMEROOM_TEACHER")) {
      redirectURl = "/show/student";
    } else if (accountDetails.hasRole("STUDENT")) {
      redirectURl = "/student/show/mark";
    }

    response.sendRedirect(redirectURl);
  }
}
