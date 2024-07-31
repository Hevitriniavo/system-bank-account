package com.fresh.coding.systembankaccount.entities.accounts;

import com.fresh.coding.systembankaccount.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckingAccountTest {
    private CheckingAccount checkingAccount;

    @BeforeEach
    void setUp() {
        var customer = new Customer("John", "Doe", LocalDate.of(1968, 12, 1), "123456789", null, "john.doe@example.com", "password");
        checkingAccount = CheckingAccount.builder()
                .id(1L)
                .accountNumber("BA001")
                .balance(1000.0)
                .salary(10.0)
                .overdraftLimit(0.08 * 10.0)
                .openedOn(LocalDateTime.now())
                .customer(customer)
                .build();
    }

    @Test
    void withdraw_valid_amount_should_reduce_balance() {
        checkingAccount.withdraw(500.0);
        assertThat(checkingAccount.getBalance()).isEqualTo(500.0);
    }

    @Test
    void withdraw_with_amount_less_zero() {
        var exception = assertThrows(IllegalArgumentException.class, () -> checkingAccount.withdraw(-500.0));
        assertThat(exception.getMessage()).isEqualTo("Withdraw amount must be greater than zero");
        assertThat(checkingAccount.getBalance()).isEqualTo(1000.0);
    }

    @Test
    void withdraw_exceeding_overdraft_limit_should_throw_exception() {
        double withdrawalAmount = checkingAccount.getBalance() + checkingAccount.getOverdraftLimit() + 100.0;
        var exception = assertThrows(IllegalArgumentException.class, () -> checkingAccount.withdraw(withdrawalAmount));
        assertThat(exception.getMessage()).isEqualTo("Withdrawal amount exceeds available balance and overdraft limit");
        assertThat(checkingAccount.getBalance()).isEqualTo(1000.0);
    }

    @Test
    void deposit_checking_account_with_value_than_zero() {
        checkingAccount.deposit(1000.0);
        assertThat(checkingAccount).isNotNull();
        assertThat(checkingAccount.getBalance()).isEqualTo(2000.0);
    }

    @Test
    void deposit_checking_account_with_value_less_zero() {
        var exception = assertThrows(IllegalArgumentException.class, () -> checkingAccount.deposit(-1000.0));
        assertThat(exception.getMessage()).contains("-1000,00 < 0");
        assertThat(checkingAccount.getBalance()).isEqualTo(1000.0);
    }
}