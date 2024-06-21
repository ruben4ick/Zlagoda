package com.zlagoda.dao;

import com.zlagoda.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends GenericDao<Product, Long>{

    List<Product> findByCategory(Long categoryId);

    List<Product> findByName(String name);

    List<Product> findBySoldFromCertainCity(String name);

}
