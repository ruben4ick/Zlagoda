package com.zlagoda.service;

import com.zlagoda.dto.StoreProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface StoreProductService extends GenericService<StoreProductDto, String>{
    List<StoreProductDto> getPromotionalProducts();
    List<StoreProductDto> getNotPromotionalProducts();

  /*  BigDecimal getPriceByUpc(String upc);*/
}
