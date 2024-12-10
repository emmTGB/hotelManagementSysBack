package com.oxlxs.hotelmanagementsysback.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutRequest {
    private Long stayId;
    private Long operatorId;
}
