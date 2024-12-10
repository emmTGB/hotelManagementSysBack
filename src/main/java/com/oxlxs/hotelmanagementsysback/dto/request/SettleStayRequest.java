package com.oxlxs.hotelmanagementsysback.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCancelRequest {
    private Long preId;
    private Long operatorId;
}
