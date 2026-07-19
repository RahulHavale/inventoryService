package com.example.InventoryService;

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
    public void createInventory(InventoryRequest request) {

        InventoryEntity entity = new InventoryEntity();
        entity.setProductId(request.getProductId());
        entity.setAvailableStock(request.getAvailableStock());
        entity.setReservedStock(request.getReservedStock());
        entity.setCreatedDate(LocalDate.now());
        System.out.println(entity.getInventoryId());

        repository.save(entity);
    }

    @Override
    public List<InventoryResponse> getAllInventory() {

        Type listType = new TypeToken<List<InventoryResponse>>() {}.getType();

        return mapper.map(repository.findAll(), listType);
    }

    @Override
    public InventoryResponse getInventory(Long id) {

        InventoryEntity entity = repository.findById(id).orElse(null);

        if(entity == null){
            return null;
        }

        return mapper.map(entity, InventoryResponse.class);
    }

    @Override
    public void updateInventory(Long id, InventoryRequest request) {

        InventoryEntity entity = repository.findById(id).orElse(null);

        if(entity == null){
            return;
        }

        entity.setProductId(request.getProductId());
        entity.setAvailableStock(request.getAvailableStock());
        entity.setReservedStock(request.getReservedStock());
        entity.setUpdatedDate(LocalDate.now());

        repository.save(entity);
    }

    @Override
    public void deleteInventory(Long id) {

        repository.deleteById(id);
    }


    public void reduceStock(Long productId, Integer quantity) {

        InventoryEntity entity =
                repository.findByProductId(productId);

        entity.setAvailableStock(
                entity.getAvailableStock() - quantity);

        repository.save(entity);
    }
}
