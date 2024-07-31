package com.fresh.coding.systembankaccount.entities.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Represents a business account extending from Account.
 * <p>
 * This class defines properties and methods specific to business accounts.
 */
@Getter
@Setter
@SuperBuilder
@Entity
@PrimaryKeyJoinColumn(name = "business_id")
@AllArgsConstructor
@NoArgsConstructor
public class BusinessAccount extends Account implements Serializable {

    @Column
    private String businessName;

    /**
     * Withdraws a specified amount from the business account.
     *
     * @param amount The amount to withdraw. Must be greater than zero and less than or equal to current balance.
     * @throws IllegalArgumentException If amount is not within valid range.
     */
    @Override
    public void withdraw(@NonNull Double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException(String.format("%.2f < 0 || %.2f > %.2f", amount, amount, this.balance));
        }
    }
}
