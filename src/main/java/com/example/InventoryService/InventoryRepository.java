package com.example.InventoryService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {

    public InventoryEntity findByProductId(Long productId);
}
