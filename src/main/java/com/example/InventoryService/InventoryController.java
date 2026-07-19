package com.example.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @PostMapping
    public void createInventory(@RequestBody InventoryRequest request){

        service.createInventory(request);
    }

    @GetMapping
    public List<InventoryResponse> getAllInventory(){

        return service.getAllInventory();
    }

    @GetMapping("/{id}")
    public InventoryResponse getInventory(@PathVariable Long id){

        return service.getInventory(id);
    }

    @PutMapping("/{id}")
    public void updateInventory(@PathVariable Long id,
                                @RequestBody InventoryRequest request){

        service.updateInventory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id){

        service.deleteInventory(id);
    }

    @PutMapping("/reduce-stock/{productId}")
    public void reduceStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {

        service.reduceStock(productId, quantity);
    }
}
