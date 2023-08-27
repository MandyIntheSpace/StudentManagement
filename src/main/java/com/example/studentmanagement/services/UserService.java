package com.example.studentmanagement.services;

import com.example.studentmanagement.Request.UserRequest;
import com.example.studentmanagement.entity.User;

public interface UserService {

    UserRequest addUser(UserRequest userRequest);

    void initUser();

}
