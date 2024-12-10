package com.oxlxs.hotelmanagementsysback.repository;

import com.oxlxs.hotelmanagementsysback.entity.Customer;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    // public Page<Customer> findAll(Pageable pageable);

    @Modifying
    @Query(value = "call find_room_by_id_number(:id_number)", nativeQuery = true)
    public Long getRoom(@Param("id_number") String idNumber);

    public boolean existsByIdNumber(String idNumber);

    public Optional<Customer> findFullNameById(Long id);

    public Optional<Customer> findByIdNumber(String idNumber);
}
