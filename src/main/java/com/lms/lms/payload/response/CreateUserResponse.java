package com.lms.lms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
