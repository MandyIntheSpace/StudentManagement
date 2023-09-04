package com.example.studentmanagement.controller;


import com.example.studentmanagement.Request.DepartmentRequest;
import com.example.studentmanagement.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentContoller {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<DepartmentRequest> saveDepartment( @RequestBody DepartmentRequest departmentRequest) {

        DepartmentRequest departmentRequest1  = this.departmentService.saveDepartment(departmentRequest);
        return new ResponseEntity<DepartmentRequest>(departmentRequest1, HttpStatus.OK);

    }

}
