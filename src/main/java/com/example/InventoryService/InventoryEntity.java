package com.example.InventoryService;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private Long productId;

    private Integer availableStock;

    private Integer reservedStock;

    private LocalDate createdDate;

    private LocalDate updatedDate;
}