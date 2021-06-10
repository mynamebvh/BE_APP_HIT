package com.backend_app_hit.app_hit.helpers;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.dto.SignUpDTO;

public class ConvertObject {
  public static User fromSignUpDTOToUserDAO(SignUpDTO signUpDTO) {
    if (!CheckValid.checkSignUp(signUpDTO)) {
      return null;
    }

    User user = new User();
    user.setFullName(signUpDTO.getFullName().trim().replace("\\s+", " "));
    user.setUsername(signUpDTO.getUsername());
    user.setRole("MEMBER");
    user.setEmail(signUpDTO.getEmail());
    user.setPhone(signUpDTO.getPhone());
    user.setBirthday(signUpDTO.getBirthday());
    return user;
  }
}
