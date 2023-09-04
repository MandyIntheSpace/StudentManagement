package com.example.studentmanagement.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EducationRquest {

    private String educationId;

    private String level;

    private String course;

    private String instituteName;

    private String universityName;

    private String studentName;

}
