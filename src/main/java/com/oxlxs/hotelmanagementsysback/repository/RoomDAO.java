package com.oxlxs.hotelmanagementsysback.repository;

import com.oxlxs.hotelmanagementsysback.entity.Room;
import com.oxlxs.hotelmanagementsysback.entity.RoomStatus;
import com.oxlxs.hotelmanagementsysback.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomDAO extends JpaRepository<Room, Long> {
    Optional<Room> findFirstByTypeAndStatus(RoomType type, RoomStatus status);
}
