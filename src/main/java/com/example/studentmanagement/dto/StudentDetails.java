package com.example.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetails {

    @NotBlank(message = "first name must be specified")
    private String firstName;

    @NotBlank(message = "gender must be specified")
    private String gender;

    @NotBlank(message = "status must be specified")
    private String status;

    @NotBlank(message = "address must be specified")
    private String address;

    @NotBlank(message = "maitial status must be specified")
    private String maritialStatus;

    @NotBlank(message = "contact must be specified")
    private String contact;

    @NotBlank(message = "added by must be specified")
    private String addedBy;

    @NotBlank(message = "modified by must be specified")
    private String modifiedBy;

    @NotBlank(message = "enrolled year must be specified")
    private String enrolledYear;

    @NotBlank(message = "The course should not be empty")
    private String course;

    @NotBlank(message = "The departemnt should not be empty")
    private String department;

}
