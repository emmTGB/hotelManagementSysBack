package com.oxlxs.hotelmanagementsysback.exception.room;

public class RoomAlreadyExistsException extends RuntimeException {
    public RoomAlreadyExistsException() {
        super("Room already exists");
    }
}
