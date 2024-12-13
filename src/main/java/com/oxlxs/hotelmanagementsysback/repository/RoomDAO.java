package com.oxlxs.hotelmanagementsysback.repository;

import com.oxlxs.hotelmanagementsysback.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDAO extends JpaRepository<Room, Long> {
}
