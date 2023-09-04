package com.example.studentmanagement.controller;

import com.example.studentmanagement.Request.AuthRequest;
import com.example.studentmanagement.Response.ResponseBody;
import com.example.studentmanagement.config.JwtUtil;
import com.example.studentmanagement.config.UserAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthRequest request) {
        System.out.println(request.getUserName());
        try{
            ResponseBody responseBody = userAuthService.createJwtToken(request);
            System.out.println(responseBody.getMessage());
//            return ResponseEntity.status(responseBody.getStatus()).body(responseBody);
            return ResponseEntity.status(responseBody.getStatus()).body(responseBody);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong");

        }

    }

}
