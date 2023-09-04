package com.example.studentmanagement.controller;

import com.example.studentmanagement.Request.UserRequest;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserRequest> addUser(@Valid @RequestBody UserRequest userRequest) {

        UserRequest userRequest1 = this.userService.addUser(userRequest);
        return new ResponseEntity<UserRequest>(userRequest1, HttpStatus.OK);

    }

//    @PostConstruct
//    public void addAdmin() {
//        userService.initUser();
//    }

}
