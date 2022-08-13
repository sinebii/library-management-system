package com.lms.lms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
