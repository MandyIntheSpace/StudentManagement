package com.example.studentmanagement.controller;

import com.example.studentmanagement.Request.RegistrationRequest;
import com.example.studentmanagement.Response.ResponseBody;
import com.example.studentmanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllStudent() {
        ResponseBody responseBody = this.studentService.getAllStudent();
        return ResponseEntity.status(responseBody.getStatus()).body(responseBody.getBody());
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Object> getStudentByName(@PathVariable("name") String name) {
        ResponseBody responseBody = this.studentService.getStudentByName(name);
        return ResponseEntity.status(responseBody.getStatus()).body(responseBody.getBody());
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<RegistrationRequest> updateStudent(@RequestBody RegistrationRequest registrationRequest, @PathVariable("studentId") Long studentId) {
        RegistrationRequest responseBody = this.studentService.updateStudent(registrationRequest, studentId);
        return new ResponseEntity<RegistrationRequest>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Long studentId) {
        ResponseBody responseBody = this.studentService.deleteStudent(studentId);
        return ResponseEntity.status(responseBody.getStatus()).body(responseBody.getBody());
    }

}
