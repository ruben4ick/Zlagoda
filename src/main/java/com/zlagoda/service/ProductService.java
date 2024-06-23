package com.zlagoda.service;


import com.zlagoda.dto.ProductDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ProductService extends GenericService<ProductDto, Long> {
    List<ProductDto> findByCategory(Long categoryId);
    Optional<Integer> findTotalSalesByNameInDateRange(String productName,
                                                         LocalDateTime startDate,
                                                         LocalDateTime endDate);
    List<ProductDto> findByName(String name);
}
