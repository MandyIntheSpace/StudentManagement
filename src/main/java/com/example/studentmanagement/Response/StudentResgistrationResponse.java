package com.example.studentmanagement.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResgistrationResponse {

    @NotBlank(message = "name must be entered")
    private String name;

    @NotBlank(message = "gender must not be blank")
    private String gender;

    @NotBlank(message = "martial status must not be left blank")
    private String maritalStatus;

    @NotBlank(message = "added by must not be left blank")
    private String addedBy;

    @NotBlank(message = "modified by must not be left blank")
    private String modifiedBy;

    @NotBlank(message = "contact must not be left blank")
    private String contact;

    @NotBlank(message = "department must not be left blank")
    private String department;

    @NotBlank(message = "course must not be left blank")
    private String course;

    @NotBlank(message = "enrolled year must not be left blank")
    private String enrolledYear;

    @NotBlank(message = "modified date must not left blank")
    private String modifiedDate;

}
