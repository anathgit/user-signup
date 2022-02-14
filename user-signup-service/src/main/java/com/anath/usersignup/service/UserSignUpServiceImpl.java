package com.anath.usersignup.service;

import com.anath.model.User;
import com.anath.usersignup.dao.UserSignUpDao;
import com.anath.usersignup.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class UserSignUpServiceImpl implements UserSignUpService {

    private UserSignUpDao userSignUpDao;

    public UserSignUpServiceImpl(UserSignUpDao userSignUpDao){
        this.userSignUpDao = userSignUpDao;
    }

    @Override
    public String createUser(final User user) {
        LocalDateTime timestamp = LocalDateTime.now();
        UserDto userDto =
                new UserDto.UserDtoBuilder()
                        .email(user.getEmail())
                        .registeredAt(timestamp)
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .instagramUsername(user.getInstagramUsername())
                        .twitterUsername(user.getTwitterUsername())
                        .developmentEnvironment(user.getDevelopmentEnvironment())
                        .address(user.getAddress())
                        .build();

        return userSignUpDao.createUser(userDto);
    }

    @Override public User findUserById(final String id) {
        return userSignUpDao.findUserById(id);
    }
}
