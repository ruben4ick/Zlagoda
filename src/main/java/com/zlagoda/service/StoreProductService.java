package com.zlagoda.service;

import com.zlagoda.dto.StoreProductDto;

import java.util.List;

public interface StoreProductService extends GenericService<StoreProductDto, String>{
    List<StoreProductDto> getPromotionalProducts();

    void addPromotionStoreProduct(String upc);

    void removePromotionStoreProduct(String upc);

  /*  BigDecimal getPriceByUpc(String upc);*/
}
