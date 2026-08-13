package com.example.InventoryService;

import java.util.List;

public interface InventoryService {

    InventoryResponse createInventory(
            InventoryRequest request);

    List<InventoryResponse> getAllInventory();

    InventoryResponse getInventory(Long id);

    InventoryResponse updateInventory(
            Long id,
            InventoryRequest request);

    void deleteInventory(Long id);

    void reduceStock(
            Long productId,
            Integer quantity);

    InventoryResponse getInventoryByProductId(Long productId);
}