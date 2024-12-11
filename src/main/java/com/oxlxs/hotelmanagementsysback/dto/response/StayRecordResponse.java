package com.oxlxs.hotelmanagementsysback.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StayRecordResponse {

    private Long id;
    private String customer;
    private String roomNumber;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

}
