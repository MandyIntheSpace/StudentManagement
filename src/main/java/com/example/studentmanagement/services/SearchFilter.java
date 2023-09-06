package com.example.studentmanagement.services;

import com.example.studentmanagement.dto.StudentDetails;

import java.util.List;

public interface SearchFilter {
    List<StudentDetails> getAllFilteredStudent(String filter);
}
