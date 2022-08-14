package com.lms.lms.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePublisherRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long publisherId;
    private String publisherCoyName;
}
