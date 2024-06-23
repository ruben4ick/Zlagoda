package com.zlagoda.service;

import com.zlagoda.dto.StoreProductDto;
import com.zlagoda.entity.StoreProduct;

import java.util.List;
import java.util.Optional;

public interface StoreProductService extends GenericService<StoreProductDto, String>{
    List<StoreProductDto> getPromotionalProducts();
    List<StoreProductDto> getStandardProducts();
    Optional<StoreProductDto> findByUpcProm(String prom_upc);

    void addPromotionStoreProduct(String upc);

    void removePromotionStoreProduct(String upc);

    void updateProductQuantity(String upc, int quantity);

}
