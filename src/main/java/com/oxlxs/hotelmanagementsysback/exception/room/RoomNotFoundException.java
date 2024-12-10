package com.oxlxs.hotelmanagementsysback.exception.room;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super("Room Not Found");
    }
}
