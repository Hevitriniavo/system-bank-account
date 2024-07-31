package com.fresh.coding.systembankaccount.entities;

import com.fresh.coding.systembankaccount.entities.accounts.Account;
import com.fresh.coding.systembankaccount.enums.TransferType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Represents a transfer entity.
 * <p>
 * This class defines properties and methods for managing transfer details between accounts.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;

    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiver;

    private String description;

    @Enumerated(EnumType.STRING)
    private TransferType type;
}

