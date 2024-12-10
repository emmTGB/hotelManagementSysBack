package com.oxlxs.hotelmanagementsysback.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckInRequest {
    private Long customerId;
    private Long roomId;
    private Long operatorId;
}
