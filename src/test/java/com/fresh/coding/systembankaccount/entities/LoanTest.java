package com.fresh.coding.systembankaccount.entities;

import com.fresh.coding.systembankaccount.enums.LoanStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LoanTest {

    private Loan loanJohn;
    private Loan loanJane;

    @BeforeEach
    void setUp() {
        var customerJohn = new Customer("John", "Doe", LocalDate.of(1985, 5, 15), "123456789", 1L, "john.doe@example.com", "password");
        var customerJane = new Customer("Jane", "Doe", LocalDate.of(1990, 6, 20), "987654321", 2L, "jane.doe@example.com", "password");
        loanJohn = new Loan(1L, customerJohn, 10000.0, 5.5, LocalDate.now().minusDays(30), LocalDate.now().plusDays(60), LoanStatus.APPROVED);
        loanJane = new Loan(2L, customerJane, 20000.0, 4.5, LocalDate.now().minusDays(90), LocalDate.now().plusDays(30), LoanStatus.APPROVED);
    }

    @Test
    void is_active() {
        assertThat(loanJohn.isActive()).isTrue();
        assertThat(loanJane.isActive()).isTrue();
    }
}
