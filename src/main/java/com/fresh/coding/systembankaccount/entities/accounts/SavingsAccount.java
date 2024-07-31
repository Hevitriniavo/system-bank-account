package com.fresh.coding.systembankaccount.entities.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Represents a savings account extending from Account.
 * <p>
 * This class defines properties and methods specific to savings accounts.
 */
@Getter
@Setter
@SuperBuilder
@Entity
@PrimaryKeyJoinColumn(name = "savings_id")
@AllArgsConstructor
@NoArgsConstructor
public class SavingsAccount extends Account {

    @Column
    private Double interestRate;

    @Column
    private LocalDateTime lastInterestApplied;

    /**
     * Withdraws a specified amount from the savings account.
     *
     * @param amount The amount to withdraw. Must be greater than or equal to zero and within available balance.
     * @throws IllegalArgumentException If amount is negative or exceeds available balance.
     */
    @Override
    public void withdraw(@NonNull Double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
        } else if (amount > getBalance()) {
            throw new IllegalArgumentException("Insufficient funds to withdraw " + amount);
        } else {
            this.balance -= amount;
        }
    }

    /**
     * Applies accrued interest to the savings account.
     */
    public void applyInterest() {
        var interestAmount = balance * interestRate;
        balance += interestAmount;
    }
}
