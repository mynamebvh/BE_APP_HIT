package com.backend_app_hit.app_hit.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GetUserNameByContext {

  public static String getUserName() {
    String userName = "hi";
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    return userName;
  }
}
