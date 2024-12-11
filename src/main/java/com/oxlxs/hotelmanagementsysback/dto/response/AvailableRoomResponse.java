package com.oxlxs.hotelmanagementsysback.dto.response;

import com.oxlxs.hotelmanagementsysback.entity.AvailableRoom;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AvailableRoomResponse {
    private String typeName;
    private String fullName;
    private BigDecimal price;
    private Long available;

    public AvailableRoomResponse(AvailableRoom availableRoom) {
        this.typeName = availableRoom.getTypeName();
        this.fullName = availableRoom.getFullName();
        this.price = availableRoom.getPrice();
        this.available = availableRoom.getAvailable();
    }
}
