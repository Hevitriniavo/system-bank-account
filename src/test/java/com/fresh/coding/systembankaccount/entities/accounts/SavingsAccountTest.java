package com.fresh.coding.systembankaccount.entities.accounts;

import com.fresh.coding.systembankaccount.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SavingsAccountTest {

    SavingsAccount savingsAccount;

    @BeforeEach
    void setUp() {
        var customer = new Customer("John", "Doe", LocalDate.of(1990, 5, 15), "123456789", null, "john.doe@example.com", "password");
        savingsAccount = SavingsAccount.builder()
                .id(1L)
                .accountNumber("BA001")
                .balance(1000.0)
                .openedOn(LocalDateTime.now())
                .customer(customer)
                .interestRate(0.8)
                .build();
    }

    @Test
    void withdraw_valid_amount_should_reduce_balance() {
        savingsAccount.withdraw(500.0);
        assertThat(savingsAccount.getBalance()).isEqualTo(500.0);
    }

    @Test
    void withdraw_amount_exceeds_balance_should_throw_exception() {
        assertThatThrownBy(() -> savingsAccount.withdraw(1500.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Insufficient funds to withdraw 1500.0");
    }

    @Test
    void withdraw_negative_amount_should_throw_exception() {
        assertThatThrownBy(() -> savingsAccount.withdraw(-100.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Withdrawal amount cannot be negative.");
    }

    @Test
    void applyInterest() {
        LocalDateTime oneYearLater = savingsAccount.getOpenedOn().plusYears(1);
        savingsAccount.setOpenedOn(oneYearLater);
        double initialBalance = savingsAccount.getBalance();
        savingsAccount.applyInterest();
        double expectedBalance = initialBalance * (1 + savingsAccount.getInterestRate());
        assertThat(savingsAccount.getBalance()).isEqualTo(expectedBalance);
    }
}
