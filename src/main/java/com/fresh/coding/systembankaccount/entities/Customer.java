package com.fresh.coding.systembankaccount.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a customer entity.
 * <p>
 * This class defines properties and methods for managing customer details.
 */
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Customer implements Serializable {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDate birthdate;

    @Column
    private String cin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

}

