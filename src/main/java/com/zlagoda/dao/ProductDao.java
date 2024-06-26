package com.zlagoda.dao;

import com.zlagoda.entity.Product;
import com.zlagoda.entity.ProductClassic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductDao extends GenericDao<Product, Long>{

    List<Product> findByCategory(Long categoryId);

    List<Product> findByName(String name);

    List<ProductClassic> getProductCountsByCategory();

    Optional<Integer> findTotalSalesByNameInDateRange(String productName, LocalDateTime startDate, LocalDateTime endDate);
    List<Product> findNotInStoreNeverSoldProducts();

    List<Product> findBySoldFromCertainCity(String name);

}
