package com.oxlxs.hotelmanagementsysback.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BillResponse {
    private String name;
    private Long roomId;
    private String typeName;
    private BigDecimal amount;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
}
