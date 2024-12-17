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
    @Query(value = "call checkin(:time, :roomId, :deposit, :id, :idNumber)", nativeQuery = true)
    void checkin(
            @Param("time") LocalDateTime time,
            @Param("roomId") Long roomId,
            @Param("deposit") BigDecimal deposit,
            @Param("id") Long id,
            @Param("idNumber") String idNumber
    );

    @Transactional
    @Modifying
    @Query(
            value = "call checkout(:roomId, :outTime)",
            nativeQuery = true
    )
    void checkout(
            @Param("roomId") Long roomId,
            @Param("outTime") LocalDateTime outTime
    );

}