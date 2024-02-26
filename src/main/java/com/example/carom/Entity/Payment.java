package com.example.carom.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_bank_account_id")
    private String ownerBankAccountId;

    @Column(name = "renter_bank_account_id")
    private String renterBankAccountId;

    private BigDecimal amount;
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
