package com.fresh.coding.systembankaccount.entities.accounts;

import com.fresh.coding.systembankaccount.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class YouthAccountTest {

    YouthAccount youthAccount;

    @BeforeEach
    void setUp() {
        var customer = new Customer("Jane", "Doe", LocalDate.of(2005, 8, 20), "987654321", null, "jane.doe@example.com", "password");
        youthAccount = YouthAccount.builder()
                .id(2L)
                .accountNumber("YA002")
                .balance(500.0)
                .openedOn(LocalDateTime.now())
                .guardian(customer)
                .build();
    }

    @Test
    void withdraw_valid_amount_should_reduce_balance() {
        youthAccount.withdraw(200.0);
        assertThat(youthAccount.getBalance()).isEqualTo(300.0);
    }

    @Test
    void withdraw_amount_exceeds_balance_should_throw_exception() {
        assertThatThrownBy(() -> youthAccount.withdraw(600.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Insufficient funds to withdraw 600.0");
    }

    @Test
    void withdraw_negative_amount_should_throw_exception() {
        assertThatThrownBy(() -> youthAccount.withdraw(-100.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Withdrawal amount cannot be negative.");
    }


}
