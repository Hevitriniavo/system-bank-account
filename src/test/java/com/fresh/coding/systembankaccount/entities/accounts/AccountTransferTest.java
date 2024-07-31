package com.fresh.coding.systembankaccount.entities.accounts;

import com.fresh.coding.systembankaccount.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTransferTest {

    private CheckingAccount sourceAccount;
    private SavingsAccount destinationAccount;

    @BeforeEach
    void setUp() {
        var customer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        sourceAccount = CheckingAccount.builder()
                .id(1L)
                .accountNumber("123456789")
                .balance(1000.0)
                .overdraftLimit(0.08 * 10.0)
                .customer(customer)
                .build();

        destinationAccount = SavingsAccount.builder()
                .id(2L)
                .accountNumber("987654321")
                .balance(500.0)
                .customer(customer)
                .interestRate(0.05)
                .build();
    }

    @Test
    void test_transfer_valid_amount() {
        var transferAmount = 200.0;
        sourceAccount.transfer(destinationAccount, transferAmount);
        assertEquals(800.0, sourceAccount.getBalance());
        assertEquals(700.0, destinationAccount.getBalance());
    }

    @Test
    void test_transfer_insufficient_funds() {
        var transferAmount = 1200.0;
        var exception = assertThrows(IllegalArgumentException.class, () -> sourceAccount.transfer(destinationAccount, transferAmount));
        assertEquals("Insufficient funds to transfer 1200.0", exception.getMessage());
        assertEquals(1000.0, sourceAccount.getBalance(), 0.01);
        assertEquals(500.0, destinationAccount.getBalance(), 0.01);
    }

    @Test
    void test_transfer_negative_amount() {
        var transferAmount = -100.0;
        var exception = assertThrows(IllegalArgumentException.class, () -> sourceAccount.transfer(destinationAccount, transferAmount));
        assertEquals("Transfer amount must be greater than zero.", exception.getMessage());
        assertEquals(1000.0, sourceAccount.getBalance());
        assertEquals(500.0, destinationAccount.getBalance());
    }
}
