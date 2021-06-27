package com.backend_app_hit.app_hit.services.builder;

import com.backend_app_hit.app_hit.dao.User;

public interface UserBuilder {
    UserBuilder setFullName(String fullName);
    UserBuilder setUsername(String username);
    UserBuilder setPassword(String password);
    UserBuilder setBirthday(String birthday);
    UserBuilder setEmail(String email);
    UserBuilder setPhone(String phone);
    User build();
}
