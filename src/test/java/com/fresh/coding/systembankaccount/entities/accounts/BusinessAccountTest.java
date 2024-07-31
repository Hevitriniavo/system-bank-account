package com.fresh.coding.systembankaccount.entities.accounts;

import com.fresh.coding.systembankaccount.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BusinessAccountTest {
    BusinessAccount businessAccount;

    @BeforeEach
    void setUp() {
        var customer = new Customer("John", "Doe", LocalDate.of(1990, 5, 15), "123456789", null, "john.doe@example.com", "password");
        businessAccount = BusinessAccount.builder()
                .id(1L)
                .accountNumber("BA001")
                .balance(1000.0)
                .openedOn(LocalDateTime.now())
                .customer(customer)
                .businessName("Fresh Coding Inc.")
                .build();
    }

    @Test
    void withdraw_business_account_with_sufficient_balance() {
        businessAccount.withdraw(1000.0);
        assertThat(businessAccount).isNotNull();
        assertThat(businessAccount.getBalance()).isEqualTo(0.0);
    }

    @Test
    void withdraw_business_account_with_insufficient_balance() {
        var exception = assertThrows(IllegalArgumentException.class, () -> businessAccount.withdraw(-1001.0));
        assertThat(exception.getMessage()).isEqualTo("-1001,00 < 0 || -1001,00 > 1000,00");
        assertThat(businessAccount.getBalance()).isEqualTo(1000.0);
    }

    @Test
    void withdraw_business_account_with_zero_balance() {
        businessAccount.withdraw(1000.0);
        assertThat(businessAccount).isNotNull();
        assertThat(businessAccount.getBalance()).isEqualTo(0.0);
    }

    @Test
    void deposit_business_account_with_value_than_zero() {
        businessAccount.deposit(1000.0);
        assertThat(businessAccount).isNotNull();
        assertThat(businessAccount.getBalance()).isEqualTo(2000.0);
    }

    @Test
    void deposit_business_account_with_value_less_zero() {
        var exception = assertThrows(IllegalArgumentException.class, () -> businessAccount.deposit(-1000.0));
        assertThat(exception.getMessage()).contains("-1000,00 < 0");
        assertThat(businessAccount.getBalance()).isEqualTo(1000.0);
    }
}