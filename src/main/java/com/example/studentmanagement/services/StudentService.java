package com.example.studentmanagement.services;

import com.example.studentmanagement.Request.RegistrationRequest;
import com.example.studentmanagement.Response.ResponseBody;
import com.example.studentmanagement.dto.StudentDetails;

import java.util.List;

public interface StudentService {

    ResponseBody saveStudent(RegistrationRequest registrationRequest);

    ResponseBody getAllStudent();

    ResponseBody getStudentByName(String name);

    RegistrationRequest updateStudent(RegistrationRequest registrationRequest, Long studentId);

    ResponseBody deleteStudent( Long studentId);

    List<RegistrationRequest> searchStudent(String keywords);

}
