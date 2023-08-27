package com.example.studentmanagement.Request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotNull(message = "The password should not be empty")
    private String password;

    private String status = "ACTIVE";

    @NotNull(message = "The username of the user should not be empty")
    private String userName;

    private int attemptCount = 0;

}
