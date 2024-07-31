package com.fresh.coding.systembankaccount.entities;

import com.fresh.coding.systembankaccount.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a loan entity.
 * <p>
 * This class defines properties and methods for managing loan details.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    private Double amount;

    @Column
    private Double interestRate;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    /**
     * Checks if the loan is currently active.
     *
     * @return True if the loan is active (i.e., today's date is within the loan's start and end dates), false otherwise.
     */
    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return (today.isAfter(startDate) || today.isEqual(startDate)) && (today.isBefore(endDate) || today.isEqual(endDate));
    }
}
