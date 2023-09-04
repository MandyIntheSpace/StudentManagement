package com.example.studentmanagement.services;

import com.example.studentmanagement.Request.DepartmentRequest;
import com.example.studentmanagement.Request.UserRequest;
import com.example.studentmanagement.entity.Department;
import org.springframework.stereotype.Service;


public interface DepartmentService {

    DepartmentRequest saveDepartment(DepartmentRequest departmentRequest);

}
