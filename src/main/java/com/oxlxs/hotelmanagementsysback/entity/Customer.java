package com.oxlxs.hotelmanagementsysback.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.oxlxs.hotelmanagementsysback.dto.request.CustomerValidateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "op_id", referencedColumnName = "id")
    private StayRecord stayRecord;

    @Column
    private BigDecimal deposit;

    public Customer(CustomerValidateRequest request) {
        this.name = request.getName();
        this.idNumber = request.getIdNumber();
        this.phone = request.getPhone();
    }

    public Customer(){}
}