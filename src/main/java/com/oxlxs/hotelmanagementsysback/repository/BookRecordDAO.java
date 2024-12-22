package com.oxlxs.hotelmanagementsysback.repository;

import com.oxlxs.hotelmanagementsysback.entity.BookRecord;
import com.oxlxs.hotelmanagementsysback.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRecordDAO extends JpaRepository<BookRecord, Long> {
}
