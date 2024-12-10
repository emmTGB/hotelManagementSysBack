package com.oxlxs.hotelmanagementsysback.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_name", referencedColumnName = "name")
    private RoomType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "op_id", referencedColumnName = "id")
    private StayRecord stayRecord;
}
