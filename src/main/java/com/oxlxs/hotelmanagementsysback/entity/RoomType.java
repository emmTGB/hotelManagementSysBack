package com.oxlxs.hotelmanagementsysback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "room_types")
public class RoomType {
    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String fullName;

    @Column(nullable = false)
    private BigDecimal price;

}
