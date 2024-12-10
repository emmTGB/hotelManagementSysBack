package com.oxlxs.hotelmanagementsysback.repository;

import com.oxlxs.hotelmanagementsysback.entity.StayRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StayRecordDAO extends JpaRepository<StayRecord, Long> {

    @Modifying
    @Transactional
    @Query(value = "CALL SettleStay(:stayId, :operatorId)", nativeQuery = true)
    void settleStay(@Param("stayId") Long stayId, @Param("operatorId") Long operatorId);

    @Modifying
    @Transactional
    @Query(value = "call CheckIn(:customerId, :roomId, :operatorId)", nativeQuery = true)
    void checkIn(
            @Param("customerId") Long customerId,
            @Param("roomId") Long roomId,
            @Param("operatorId") Long operatorId
    );
}