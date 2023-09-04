package com.example.studentmanagement.Request;


import com.example.studentmanagement.entity.Education;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedNotification;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    @NotBlank(message = "first name cannot be empty")
    @Pattern(regexp = "[a-zA-Z.]*", message = "first name cant start with number or special characters")
    private String firstName;

    @NotBlank(message = "middle name cannot be empty")
    @Pattern(regexp = "[a-zA-z]*", message = "first name cant start with number or special characters")
    private String middleName;

    @NotBlank(message = "Gender should be sepecified")
    private String gender;

    @NotBlank(message = "Date of birth must be specified")
    private String dateOfBirth;

    @NotBlank(message = "Status must be specified")
    private String status;

    @NotBlank(message = "citizenship must number specified")
    private String citizenshipNo;

    @NotBlank(message = "Address must be specified")
    private String address;

    @NotBlank(message = "Maritial Status must be specified")
    private String maritialStatus;

    @NotBlank(message = "Email address msut be specified")
    private String emailAddress;

    @NotBlank(message = "mobile number must be specified")
    private String mobileNumber;

//    @NotBlank(message = "department name must be specified")
    private String departmentName;

    @NotBlank(message = "course name must be specified")
    private String courseName;

    @NotBlank(message = "enrolled year must be specified")
    private String enrolledYear;

    private Set<Education> educationSet = new HashSet<>();

}
