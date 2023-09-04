package com.example.studentmanagement.Request;


import com.example.studentmanagement.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseRequest {

    @NotBlank(message = "course name must be entered")
    @Pattern(regexp = "[a-zA-Z]*", message = "The name of the course must start with alphabetical words not number or with special charcters")
    @Size(max = 20, message = "Course must not be long then 20 characters")
        private String courseName;

    @NotBlank(message = "enrolled year for the specific course must be entered")
    @Pattern(regexp = "[0-9]*", message = "The duration must be in number")
    @Size(max = 2075, message = "Enrolled year must be greater than 2075")
    @Size(min = 2078, message = "ENrolled year must be lesser than 2078")
        private String enrolledYear;

    @NotBlank(message = "Duration of the specific course must be entered")
    @Pattern(regexp = "[0-9]*", message = "The duration must be in numbers")
    private String duration;

    @NotBlank(message = "name of the department in which the course existed must be entered")
    @Pattern(regexp = "[a-zA-z]*", message = "The name of the department must start with alphabetical words not number or with special characters")
    private String departmentName;

//    @NotNull
//    private Department department;


}
