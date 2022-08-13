package com.lms.lms.payload.request;

import com.lms.lms.model.BAuthor;
import com.lms.lms.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {
    private Long bookId;
    private String bookName;
    private Long isbn;
    private String bookYear;
    private BAuthor bAuthor;
    private Publisher publisher;
}
