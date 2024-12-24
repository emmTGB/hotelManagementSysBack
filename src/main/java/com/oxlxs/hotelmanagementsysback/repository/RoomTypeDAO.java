package com.oxlxs.hotelmanagementsysback.repository;

import com.oxlxs.hotelmanagementsysback.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeDAO extends JpaRepository<RoomType, String> {
    boolean existsByFullName(String fullName);
}
