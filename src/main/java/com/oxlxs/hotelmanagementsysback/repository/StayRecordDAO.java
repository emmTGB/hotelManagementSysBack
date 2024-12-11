package com.oxlxs.hotelmanagementsysback.repository;

import com.oxlxs.hotelmanagementsysback.entity.StayRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface StayRecordDAO extends JpaRepository<StayRecord, Long> {

    @Transactional
    @Modifying
    @Query(value = "call checkin(:time, :roomId, :deposit, :name, :idNumber, :phone)", nativeQuery = true)
    Long checkin(
            @Param("time") LocalDateTime time,
            @Param("roomId") Long roomId,
            @Param("deposit") BigDecimal deposit,
            @Param("name") String name,
            @Param("idNumber") String idNumber,
            @Param("phone") String phone
    );
}