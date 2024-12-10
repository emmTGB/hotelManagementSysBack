package com.oxlxs.hotelmanagementsysback.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookRequest {
    private Long customerId;
    private Long roomId;
    private LocalDateTime orderTime;
    private Long operatorId;
}
