package com.oxlxs.hotelmanagementsysback.entity;

import java.math.BigDecimal;

import org.springframework.context.annotation.Primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room_types")
public class RoomType {
    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private Long fullName;

    @Column(nullable = false)
    private BigDecimal price;

}
