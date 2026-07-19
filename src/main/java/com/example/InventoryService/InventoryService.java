package com.example.InventoryService;

import java.util.List;

public interface InventoryService {

    void createInventory(InventoryRequest request);

    List<InventoryResponse> getAllInventory();

    InventoryResponse getInventory(Long id);

    void updateInventory(Long id, InventoryRequest request);

    void deleteInventory(Long id);

    void reduceStock(Long productId, Integer quantity);
}