package com.example.InventoryService;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class ProductResponse {
    private long productId;

    private String name;

    private String description;

    private int price;

    private int stock;

    private String category;

    private LocalDate createdDate;

    private LocalDate updatedDate;
}
