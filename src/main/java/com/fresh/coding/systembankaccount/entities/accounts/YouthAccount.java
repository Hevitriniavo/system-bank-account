package com.fresh.coding.systembankaccount.entities.accounts;

import com.fresh.coding.systembankaccount.entities.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Represents a youth account extending from Account.
 * <p>
 * This class defines properties and methods specific to youth accounts.
 */
@Getter
@Setter
@SuperBuilder
@Entity
@PrimaryKeyJoinColumn(name = "youth_id")
@AllArgsConstructor
@NoArgsConstructor
public class YouthAccount extends Account {

    @ManyToOne
    private Customer guardian;

    /**
     * Withdraws a specified amount from the youth account.
     *
     * @param amount The amount to withdraw. Must be greater than or equal to zero and within available balance.
     * @throws IllegalArgumentException If amount is negative or exceeds available balance.
     */
    @Override
    public void withdraw(@NonNull Double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
        } else if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds to withdraw " + amount);
        } else {
            this.balance -= amount;
        }
    }
}

