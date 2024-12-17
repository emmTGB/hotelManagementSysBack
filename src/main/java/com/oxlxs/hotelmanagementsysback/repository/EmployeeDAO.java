package com.oxlxs.hotelmanagementsysback.repository;

import com.oxlxs.hotelmanagementsysback.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {


    Optional<Employee> findByUsername(String username);

    boolean existsByUsername(String username);
}
