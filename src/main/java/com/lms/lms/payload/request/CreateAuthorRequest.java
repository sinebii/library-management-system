package com.lms.lms.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
