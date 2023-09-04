package com.example.studentmanagement.Request;

import com.example.studentmanagement.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentRequest {

    @NotBlank(message = "Department name should not be hull")
    @Pattern(regexp = "[a-zA-Z]*", message = "Course name should start with alphabet not with the number or special characters")
    @Size(max = 40, message = "The name of the department should not be more than 40 characters")
    private String departmentName;

    @NotBlank(message = "Name of the hod of the department should not be null")
    @Pattern(regexp = "[a-zA-Z]*", message = "Name of the HOD should start with alphabet not with the number or special characters")
    private String departmentHod;

    @NotBlank(message = "The contact number of the department should not be null")
    @Size(max = 10)
    private String departmentContactNo;

//    @NotBlank(message = "The list of the course available in the department should not be null")
    private Set<Course> courseList = new HashSet<>();

}
