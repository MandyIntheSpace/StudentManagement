package com.example.studentmanagement.services;

import com.example.studentmanagement.Request.RegistrationRequest;
import com.example.studentmanagement.Response.ResponseBody;

public interface StudentService {

    ResponseBody saveStudent(RegistrationRequest registrationRequest);

}
