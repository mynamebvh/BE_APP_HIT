package com.backend_app_hit.app_hit.services.builder;

import com.backend_app_hit.app_hit.dao.User;

public class UserDaoBuilder implements UserBuilder {

    private String fullName;
    private String username;
    private String password;
    private String birthday;
    private String email;
    private String phone;

    @Override
    public UserBuilder setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public User build() {
        return new User(fullName, username,password,birthday,phone,email);
    }
}