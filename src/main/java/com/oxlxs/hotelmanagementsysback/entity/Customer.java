package com.oxlxs.hotelmanagementsysback.entity;

import com.oxlxs.hotelmanagementsysback.dto.request.CustomerRecordRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String idNumber;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stay_id", referencedColumnName = "id")
    private StayRecord stayRecord;

    @Column
    private Integer status;

    @Column(nullable = false)
    private boolean mainRes = false;

    public Customer(CustomerRecordRequest request) {
        this.name = request.getName();
        this.idNumber = request.getIdNumber();
        this.phone = request.getPhone();
        this.status = request.getStatus();
    }

    public Customer() {
    }
}