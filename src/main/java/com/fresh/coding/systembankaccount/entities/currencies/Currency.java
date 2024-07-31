package com.fresh.coding.systembankaccount.entities.currencies;

import jakarta.persistence.*;
import lombok.*;

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
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String code;
}
