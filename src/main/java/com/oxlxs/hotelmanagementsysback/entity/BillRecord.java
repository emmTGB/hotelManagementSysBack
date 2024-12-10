package com.oxlxs.hotelmanagementsysback.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import org.hibernate.annotations.Collate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bill_records")
public class BillRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rec_id", referencedColumnName = "id", nullable = false)
    private StayRecord record;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillType type;
}
