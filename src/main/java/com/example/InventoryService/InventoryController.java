package com.example.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(
            @RequestBody InventoryRequest request) {

        InventoryResponse response =
                service.createInventory(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {

        return ResponseEntity.ok(service.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventory(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getInventory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable Long id,
            @RequestBody InventoryRequest request) {

        return ResponseEntity.ok(
                service.updateInventory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(
            @PathVariable Long id) {

        service.deleteInventory(id);

        return ResponseEntity.ok("Inventory deleted successfully.");
    }

    @PutMapping("/reduce-stock/{productId}")
    public ResponseEntity<String> reduceStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {

        service.reduceStock(productId, quantity);

        return ResponseEntity.ok("Stock reduced successfully.");
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(
            @PathVariable Long productId) {

        return ResponseEntity.ok(service.getInventoryByProductId(productId));
    }
}