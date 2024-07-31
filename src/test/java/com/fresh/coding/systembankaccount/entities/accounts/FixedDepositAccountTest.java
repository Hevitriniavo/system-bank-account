package com.fresh.coding.systembankaccount.entities.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FixedDepositAccountTest {
    private FixedDepositAccount fixedDepositAccount;

    @BeforeEach
    void setUp() {
        fixedDepositAccount = FixedDepositAccount.builder()
                .id(1L)
                .accountNumber("FDA001")
                .balance(1000.0)
                .salary(0.0)
                .openedOn(LocalDateTime.now())
                .maturityDate(LocalDate.now().plusMonths(6))
                .interestRate(0.05)
                .build();
    }

    @Test
    void withdraw_valid_amount_after_maturity_should_reduce_balance() {
        fixedDepositAccount.setMaturityDate(LocalDate.now().minusDays(1));
        fixedDepositAccount.withdraw(500.0);
        assertThat(fixedDepositAccount.getBalance()).isEqualTo(500.0);
    }

    @Test
    void withdraw_validAmount_before_maturity_should_throw_illegal_state_exception() {
        assertThatThrownBy(() -> fixedDepositAccount.withdraw(500.0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Cannot withdraw before maturity date");
    }

    @Test
    void withdraw_amount_exceeds_balance_should_throw_illegal_argument_exception() {
        fixedDepositAccount.setMaturityDate(LocalDate.now().minusDays(1));
        double withdrawalAmount = fixedDepositAccount.getBalance() + 100.0;
        assertThatThrownBy(() -> fixedDepositAccount.withdraw(withdrawalAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid withdrawal amount or exceeds balance");
    }

    @Test
    void withdraw_negative_amount_should_throw_illegal_argument_exception() {
        fixedDepositAccount.setMaturityDate(LocalDate.now().minusDays(1));
        assertThatThrownBy(() -> fixedDepositAccount.withdraw(-100.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid withdrawal amount or exceeds balance");
    }


    @Test
    void applyInterest_after_maturity_date() {
        fixedDepositAccount.setMaturityDate(LocalDate.now().minusDays(1));
        fixedDepositAccount.applyInterest();
        assertThat(fixedDepositAccount.getBalance()).isEqualTo(1000.0 * (1 + 0.05));
    }

    @Test
    void applyInterest_before_maturity_date() {
        fixedDepositAccount.applyInterest();
        assertThat(fixedDepositAccount.getBalance()).isEqualTo(1000.0);
    }
}
