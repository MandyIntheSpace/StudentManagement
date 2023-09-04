package com.example.studentmanagement.Request;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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
