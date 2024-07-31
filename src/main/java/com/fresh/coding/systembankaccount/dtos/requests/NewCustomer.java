package com.fresh.coding.systembankaccount.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewCustomer(
        @NotEmpty(message = "FirstName should not be empty")
        @Size(min = 2, max = 150, message = "FirstName should be between 2 and 50 characters")
        String firstName,

        @NotEmpty(message = "LastName should not be empty")
        @Size(min = 2, max = 150, message = "LastName should be between 2 and 50 characters")
        String lastName,

        @NotNull(message = "Birthdate is required")
        LocalDate birthdate,

        @NotEmpty(message = "CIN should not be empty")
        @Size(min = 10, max = 20, message = "CIN should be between 10 and 20 characters")
        String cin,

        @NotEmpty(message = "Email should not be empty")
        @Email(message = "Email should be valid")
        String email,

        @NotEmpty(message = "Password should not be empty")
        @Size(min = 5, message = "Password should be at least 5 characters long")
        String password
) {
}
