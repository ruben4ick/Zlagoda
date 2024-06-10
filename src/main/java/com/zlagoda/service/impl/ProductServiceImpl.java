package com.zlagoda.service.impl;

import com.zlagoda.dto.ProductDto;
import com.zlagoda.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductDto> getAll() {
        return null;
    }

    @Override
    public Optional<ProductDto> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void create(ProductDto e) {

    }

    @Override
    public void update(ProductDto e) {

    }

    @Override
    public void delete(Long id) {

    }
}
