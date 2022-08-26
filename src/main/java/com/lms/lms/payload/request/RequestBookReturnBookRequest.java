package com.lms.lms.payload.request;

import com.lms.lms.enums.BookRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestBookReturnBookRequest {
    @Enumerated(EnumType.STRING)
    private BookRequestStatus bookRequestStatus;
}
