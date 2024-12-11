package com.oxlxs.hotelmanagementsysback.dto.response;

import com.oxlxs.hotelmanagementsysback.entity.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomInfoResponse {
    private Long id;
    private String typeName; // fullName
    private String status;

    public RoomInfoResponse(Room room) {
        this.id = room.getId();
        this.typeName = room.getType().getName();
        this.status = room.getStatus().name();
    }
}
