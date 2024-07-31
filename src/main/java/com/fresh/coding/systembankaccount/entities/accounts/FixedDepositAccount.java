package com.fresh.coding.systembankaccount.entities.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Represents a fixed deposit account extending from Account.
 * <p>
 * This class defines properties and methods specific to fixed deposit accounts.
 */
@Getter
@Setter
@SuperBuilder
@Entity
@PrimaryKeyJoinColumn(name = "fixed_deposit_id")
@AllArgsConstructor
@NoArgsConstructor
public class FixedDepositAccount extends Account {

    @Column
    private LocalDate maturityDate;

    @Column
    private Double interestRate;

    /**
     * Withdraws a specified amount from the fixed deposit account.
     *
     * @param amount The amount to withdraw. Must be greater than zero and within available balance.
     * @throws IllegalArgumentException If amount is not within valid range or if withdrawal is attempted before maturity date.
     * @throws IllegalStateException    If withdrawal is attempted before maturity date.
     */
    @Override
    public void withdraw(@NonNull Double amount) {
        if (LocalDate.now().isAfter(maturityDate)) {
            if (amount > 0 && amount <= this.balance) {
                this.balance -= amount;
            } else {
                throw new IllegalArgumentException("Invalid withdrawal amount or exceeds balance");
            }
        } else {
            throw new IllegalStateException("Cannot withdraw before maturity date");
        }
    }

    /**
     * Applies accrued interest to the fixed deposit account if maturity date has passed.
     */
    public void applyInterest() {
        if (LocalDate.now().isAfter(maturityDate)) {
            var interestAmount = balance * interestRate;
            balance += interestAmount;
        }
    }
}

