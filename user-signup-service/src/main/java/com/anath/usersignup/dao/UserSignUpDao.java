package com.anath.usersignup.dao;

import com.anath.model.User;
import com.anath.usersignup.dto.UserDto;


public interface UserSignUpDao {
    String createUser(UserDto user);
    User findUserById(String id);
}
