package com.oxlxs.hotelmanagementsysback.entity;

public enum RoomStatus {
    AVAILABLE,     // 房间空闲状态，可供预订
    BOOKED,        // 房间已被预订，尚未入住
    OCCUPIED,      // 房间已入住
    CLOSED,
    DELETED
}
