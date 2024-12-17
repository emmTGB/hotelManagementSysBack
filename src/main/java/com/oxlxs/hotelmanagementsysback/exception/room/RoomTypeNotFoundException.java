package com.oxlxs.hotelmanagementsysback.exception.room;

public class RoomTypeNotFoundException extends RuntimeException {
    public RoomTypeNotFoundException() {
        super("Room type not found");
    }
}
