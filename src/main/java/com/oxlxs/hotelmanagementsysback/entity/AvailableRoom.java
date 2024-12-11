package com.oxlxs.hotelmanagementsysback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "available_rooms")
public class AvailableRoom {

    @Id
    private Long id;

    @Column(nullable = false)
    private String typeName;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long available;
}
