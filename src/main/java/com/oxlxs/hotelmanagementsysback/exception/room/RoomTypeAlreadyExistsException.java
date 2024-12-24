package com.oxlxs.hotelmanagementsysback.exception.room;

public class RoomTypeAlreadyExistsException extends RuntimeException {
    public RoomTypeAlreadyExistsException() {
        super("room type already exists");
    }
}
