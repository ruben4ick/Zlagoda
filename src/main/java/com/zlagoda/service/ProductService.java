package com.zlagoda.service;


import com.zlagoda.dto.ProductDto;

import java.util.List;


public interface ProductService extends GenericService<ProductDto, Long> {
    List<ProductDto> findByCategory(Long categoryId);

    List<ProductDto> findByName(String name);
}
