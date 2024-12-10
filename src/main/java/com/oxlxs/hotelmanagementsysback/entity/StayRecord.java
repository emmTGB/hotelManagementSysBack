package com.oxlxs.hotelmanagementsysback.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stay_records")
public class StayRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StayType type;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column
    private LocalDateTime orderTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_id", referencedColumnName = "id")
    private StayRecord preRecord;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operator_id", referencedColumnName = "id", nullable = false)
    private Employee operator;
}
