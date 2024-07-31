package com.fresh.coding.systembankaccount.entities;

import com.fresh.coding.systembankaccount.entities.accounts.SavingsAccount;
import com.fresh.coding.systembankaccount.entities.currencies.Currency;
import com.fresh.coding.systembankaccount.entities.currencies.CurrencyConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyConverterTest {

    private CurrencyConverter converterUSDToEUR;
    private CurrencyConverter converterEURToUSD;
    private CurrencyConverter converterARToEUR;
    private CurrencyConverter converterUSDToAR;

    @BeforeEach
    void setUp() {
        var usd = new Currency(1L, "US Dollar", "USD");
        var eur = new Currency(2L, "Euro", "EUR");
        var ar = new Currency(3L, "Ariary", "AR");
        converterUSDToEUR = new CurrencyConverter(1L, usd, eur, 0.92);
        converterEURToUSD = new CurrencyConverter(2L, eur, usd, 1.18);
        converterARToEUR = new CurrencyConverter(3L, ar, eur, 0.000022);
        converterUSDToAR = new CurrencyConverter(4L, usd, ar, 4468.10);
    }

    @Test
    void test_usd_to_eur_conversion() {
        var amountUSD = 1.0;
        var expectedEUR = amountUSD * converterUSDToEUR.getExchangeRate();
        assertEquals(expectedEUR, converterUSDToEUR.convert(amountUSD), 0.001);
    }

    @Test
    void test_eur_to_usd_conversion() {
        var amountEUR = 100.0;
        var expectedUSD = amountEUR * converterEURToUSD.getExchangeRate();
        assertEquals(expectedUSD, converterEURToUSD.convert(amountEUR), 0.001);
    }

    @Test
    void test_ar_to_eur_conversion() {
        var amountAR = 10000.0;
        var expectedEUR = amountAR * converterARToEUR.getExchangeRate();
        assertEquals(expectedEUR, converterARToEUR.convert(amountAR), 0.001);
    }

    @Test
    void test_usd_to_ar_conversion() {
        var amountUSD = 2.0;
        var expectedAr = amountUSD * converterUSDToAR.getExchangeRate();
        assertEquals(expectedAr, converterUSDToAR.convert(amountUSD), 0.001);
    }

    @Test
    void convert_currency_and_balance() {
        var usd = new Currency(1L, "US Dollar", "USD");
        var eur = new Currency(2L, "Euro", "EUR");
        var account = SavingsAccount.builder()
                .id(1L)
                .accountNumber("ACC-001")
                .balance(1000.0)
                .currency(usd)
                .interestRate(0.05)
                .build();

        converterUSDToEUR.convertCurrencyAndBalance(account);
        assertEquals(920, account.getBalance());
        assertEquals(eur, account.getCurrency());
    }
}
