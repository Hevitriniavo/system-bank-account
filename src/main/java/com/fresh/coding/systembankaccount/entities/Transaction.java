package com.fresh.coding.systembankaccount.entities;

import com.fresh.coding.systembankaccount.entities.accounts.Account;
import com.fresh.coding.systembankaccount.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a transaction entity.
 * <p>
 * This class defines properties and methods for managing transaction details.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
}

