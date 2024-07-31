package com.fresh.coding.systembankaccount.entities.currencies;

import com.fresh.coding.systembankaccount.entities.accounts.Account;
import jakarta.persistence.*;
import lombok.*;


/**
 * Represents a currency converter entity.
 * <p>
 * This class defines properties and methods for converting currency amounts.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConverter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_currency_id")
    private Currency fromCurrency;

    @ManyToOne
    @JoinColumn(name = "to_currency_id")
    private Currency toCurrency;

    @Column
    private Double exchangeRate;

    /**
     * Converts a specified amount from one currency to another.
     *
     * @param amount The amount to convert. Must be greater than or equal to zero.
     * @return The converted amount.
     * @throws IllegalArgumentException If either fromCurrency or toCurrency is null.
     */
    public @NonNull Double convert(@NonNull Double amount) {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Currency must be specified for conversion.");
        }
        return amount * exchangeRate;
    }

    /**
     * Converts the balance of an account to the 'toCurrency'.
     *
     * @param account The account whose balance to convert.
     */
    public void convertCurrencyAndBalance(Account account) {
        var currentBalance = account.getBalance();
        var convertedBalance = convert(currentBalance);
        account.setCurrency(toCurrency);
        account.setBalance(convertedBalance);
    }
}

