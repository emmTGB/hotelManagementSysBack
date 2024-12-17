package com.oxlxs.hotelmanagementsysback.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutRequest {
    private Long roomId;
    private String name;
    private String idNumber;
    private Long operatorId;
}
