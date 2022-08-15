package com.lms.lms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookResponse {
    private Long bookId;
    private String bookName;
    private Long isbn;
    private String bookYear;
}
