package com.oxlxs.hotelmanagementsysback.entity;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_name", referencedColumnName = "name")
    private RoomType type;

}
