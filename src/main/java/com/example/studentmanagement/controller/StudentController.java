package com.example.studentmanagement.controller;

import com.example.studentmanagement.Request.RegistrationRequest;
import com.example.studentmanagement.Response.ResponseBody;
import com.example.studentmanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveStudent(@RequestBody RegistrationRequest registrationRequest) {
        ResponseBody responseBody = studentService.saveStudent(registrationRequest);
        return ResponseEntity.status(responseBody.getStatus()).body(responseBody.getBody());

    }

}
