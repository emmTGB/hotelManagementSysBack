package com.oxlxs.hotelmanagementsysback.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CheckInRequest {
    List<CustomerRecordRequest> customers;

    private Long roomId;
    private BigDecimal deposit;
}
