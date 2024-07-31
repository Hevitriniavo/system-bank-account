package com.fresh.coding.systembankaccount.entities.currencies;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * Represents a currency entity.
 * <p>
 * This class defines properties and methods for managing currency details.
 */
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Currency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String code;
}
