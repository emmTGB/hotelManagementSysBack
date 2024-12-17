package com.oxlxs.hotelmanagementsysback.entity;

import com.oxlxs.hotelmanagementsysback.dto.request.NewRoomTypeRequest;
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

    public RoomType(NewRoomTypeRequest request) {
        this.name = request.getName();
        this.fullName = request.getFullName();
        this.price = request.getPrice();
    }

    public RoomType() {

    }
}
