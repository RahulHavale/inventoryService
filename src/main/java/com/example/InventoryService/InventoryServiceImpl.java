package com.example.InventoryService;

import com.example.InventoryService.InsufficientStockException;
import com.example.InventoryService.InventoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final ModelMapper mapper;

    @Override
    public InventoryResponse createInventory(InventoryRequest request) {

        InventoryEntity entity = new InventoryEntity();

        entity.setProductId(request.getProductId());
        entity.setAvailableStock(request.getAvailableStock());
        entity.setReservedStock(request.getReservedStock());
        entity.setCreatedDate(LocalDate.now());
        entity.setUpdatedDate(LocalDate.now());

        repository.save(entity);

        return mapper.map(entity, InventoryResponse.class);
    }

    @Override
    public List<InventoryResponse> getAllInventory() {

        Type listType =
                new TypeToken<List<InventoryResponse>>() {
                }.getType();

        return mapper.map(repository.findAll(), listType);
    }

    @Override
    public InventoryResponse getInventory(Long id) {

        InventoryEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new InventoryNotFoundException(
                                "Inventory not found with id : " + id));

        return mapper.map(entity, InventoryResponse.class);
    }

    @Override
    public InventoryResponse updateInventory(Long id,
                                             InventoryRequest request) {

        InventoryEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new InventoryNotFoundException(
                                "Inventory not found with id : " + id));

        entity.setProductId(request.getProductId());
        entity.setAvailableStock(request.getAvailableStock());
        entity.setReservedStock(request.getReservedStock());
        entity.setUpdatedDate(LocalDate.now());

        repository.save(entity);

        return mapper.map(entity, InventoryResponse.class);
    }

    @Override
    public void deleteInventory(Long id) {

        if (!repository.existsById(id)) {
            throw new InventoryNotFoundException(
                    "Inventory not found with id : " + id);
        }

        repository.deleteById(id);
    }

    @Override
    public void reduceStock(Long productId, Integer quantity) {

        InventoryEntity entity = repository.findByProductId(productId);

        if (entity == null) {
            throw new InventoryNotFoundException(
                    "Inventory not found for product id : " + productId);
        }

        if (entity.getAvailableStock() < quantity) {
            throw new InsufficientStockException(
                    "Insufficient stock available.");
        }

        entity.setAvailableStock(
                entity.getAvailableStock() - quantity);

        entity.setUpdatedDate(LocalDate.now());

        repository.save(entity);
    }

    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {

        InventoryEntity entity = repository.findByProductId(productId);
        if (entity == null) {
            throw new RuntimeException("Inventory not found with Product Id : " + productId);
        }

        return mapper.map(entity, InventoryResponse.class);
    }
}