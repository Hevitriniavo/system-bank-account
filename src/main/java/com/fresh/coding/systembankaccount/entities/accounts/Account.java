package com.fresh.coding.systembankaccount.entities.accounts;

import com.fresh.coding.systembankaccount.entities.Customer;
import com.fresh.coding.systembankaccount.entities.currencies.Currency;
import com.fresh.coding.systembankaccount.entities.currencies.CurrencyConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Abstract class representing a bank account.
 * <p>
 * This class defines common properties and methods for all types of bank accounts.
 * Specific account types should extend this class.
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String accountNumber;

    @Column
    protected Double balance;

    @Column
    protected Double salary;

    @Column
    protected LocalDateTime openedOn;

    @ManyToOne
    @JoinColumn
    protected Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    protected Customer customer;

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount The amount to deposit. Must be greater than zero.
     * @throws IllegalArgumentException If amount is not greater than zero.
     */
    public void deposit(@NonNull Double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException(String.format("%.2f < 0", amount));
        }
    }

    /**
     * Abstract method to withdraw a specified amount from the account.
     *
     * @param amount The amount to withdraw. Must be greater than zero.
     */
    public abstract void withdraw(@NonNull Double amount);

    /**
     * Transfers a specified amount from this account to another destination account.
     *
     * @param destinationAccount The account to transfer to.
     * @param amount             The amount to transfer. Must be greater than zero.
     * @throws IllegalArgumentException If amount is not greater than zero or if balance is insufficient.
     */
    public void transfer(@NonNull Account destinationAccount, @NonNull Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient funds to transfer " + amount);
        }
        this.withdraw(amount);
        destinationAccount.deposit(amount);
    }

    /**
     * Converts the account's balance to another currency using a currency converter.
     *
     * @param currencyConverter The currency converter instance.
     */
    public void convertCurrencyAndBalance(@NonNull CurrencyConverter currencyConverter) {
        currencyConverter.convertCurrencyAndBalance(this);
    }
}
