package com.anath.usersignup.service;

import com.anath.model.User;


public interface UserSignUpService {
    String createUser(User user);

    User findUserById(String id);
}
