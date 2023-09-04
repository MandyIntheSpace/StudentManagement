package com.example.studentmanagement.controller;

import com.example.studentmanagement.Request.CourseRequest;
import com.example.studentmanagement.Request.DepartmentRequest;
import com.example.studentmanagement.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<CourseRequest> addCourse(@RequestBody CourseRequest courseRequest) {

        CourseRequest courseRequest1 = this.courseService.addCourse(courseRequest);
        return new ResponseEntity<CourseRequest>(courseRequest1, HttpStatus.OK);

    }

}
