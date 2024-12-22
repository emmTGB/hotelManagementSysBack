package com.oxlxs.hotelmanagementsysback.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private CustomerRecordRequest customer;

    private String typeName;
    private Long days;
}
