package com.oxlxs.hotelmanagementsysback.dto.response;

import com.oxlxs.hotelmanagementsysback.entity.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomTypeResponse {
    private String name;
    private String fullName;
    private BigDecimal price;

    public RoomTypeResponse(RoomType roomType) {
        this.name = roomType.getName();
        this.price = roomType.getPrice();
        this.fullName = roomType.getFullName();
    }
}
