package com.fresh.coding.systembankaccount.entities.accounts;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Represents a checking account extending from Account.
 * <p>
 * This class defines properties and methods specific to checking accounts.
 */
@Getter
@Setter
@SuperBuilder
@Entity
@PrimaryKeyJoinColumn(name = "checking_id")
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAccount extends Account implements Serializable {

    @Column
    private Double overdraftLimit;

    /**
     * Withdraws a specified amount from the checking account.
     *
     * @param amount The amount to withdraw. Must be greater than zero and within available balance and overdraft limit.
     * @throws IllegalArgumentException If amount is not within valid range.
     */
    @Override
    public void withdraw(@NonNull Double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }

        if (amount > this.balance + overdraftLimit) {
            throw new IllegalArgumentException("Withdrawal amount exceeds available balance and overdraft limit");
        }

        this.balance -= amount;
    }

}

