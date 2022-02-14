package com.anath.usersignup.controller;

import com.anath.api.V1Api;
import com.anath.model.User;
import com.anath.usersignup.service.UserSignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserSignUpController implements V1Api {

    private UserSignUpService userSignUpService;

    public UserSignUpController(UserSignUpService userSignUpService){
        this.userSignUpService = userSignUpService;
    }

    @Override
    public ResponseEntity<String> signUpNewUser(@Valid final User user) {
        System.out.println(user.getFirstName());
        return new ResponseEntity<String>(userSignUpService.createUser(user),  HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> getUserById(final String id){
        User user = userSignUpService.findUserById(id.trim());
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
}
