package com.fresh.coding.systembankaccount.entities.accounts;

import com.fresh.coding.systembankaccount.entities.Customer;
import com.fresh.coding.systembankaccount.entities.currencies.Currency;
import com.fresh.coding.systembankaccount.entities.currencies.CurrencyConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    @Test
    void test_convert_currency_and_balance() {
        var customer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        var fromCurrency = Currency.builder()
                .id(1L)
                .name("US Dollar")
                .code("USD")
                .build();

        var toCurrency = Currency.builder()
                .id(2L)
                .name("Euro")
                .code("EUR")
                .build();

        var exchangeRate = 0.92;

        var currencyConverter = new CurrencyConverter(1L, fromCurrency, toCurrency, exchangeRate);

        var account = SavingsAccount.builder()
                .id(1L)
                .accountNumber("S123456")
                .balance(1000.0)
                .salary(2000.0)
                .openedOn(LocalDateTime.now())
                .currency(fromCurrency)
                .customer(customer)
                .interestRate(0.03)
                .lastInterestApplied(LocalDateTime.now().minusMonths(1))
                .build();

        var initialBalance = account.getBalance();
        account.convertCurrencyAndBalance(currencyConverter);
        var convertedBalance = account.getBalance();
        assertEquals(initialBalance * exchangeRate, convertedBalance, 0.001);
        assertEquals(toCurrency, account.getCurrency());
    }
}
