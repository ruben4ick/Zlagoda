package com.zlagoda.service.impl;

import com.zlagoda.converter.ProductConverter;
import com.zlagoda.dao.ProductDao;
import com.zlagoda.dto.CategoryDto;
import com.zlagoda.dto.ProductDto;
import com.zlagoda.entity.Category;
import com.zlagoda.entity.Product;
import com.zlagoda.service.ProductService;
import com.zlagoda.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductConverter productConverter;

    @Override
    public List<ProductDto> getAll() {
        return productDao.getAll().stream()
                .map(productConverter::convertToDto)
                .toList();
    }

    @Override
    public Optional<ProductDto> getById(Long productId) {
        return productDao.getById(productId)
                .map(productConverter::convertToDto);
    }

    @Override
    public void create(ProductDto productDto) {
        productDto.normalize();
        Product product = productConverter.convertToEntity(productDto);
        productDao.create(product);
    }

    @Override
    public void update(ProductDto productDto) {
        productDto.normalize();
        Product product = productConverter.convertToEntity(productDto);
        productDao.update(product);
    }

    @Override
    public void delete(Long productId) {
        productDao.delete(productId);
    }

    public List<ProductDto> findByCategory(Long categoryId) {
        return productDao.findByCategory(categoryId).stream()
                .map(productConverter::convertToDto)
                .toList();
    }

    @Override
    public List<ProductDto> findByName(String name) {
        name = name.trim();
        return productDao.findByName(name).stream()
                .map(productConverter::convertToDto)
                .toList();
    }
    /*private Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .category(mapToCategory(productDto.getCategory())) // Перетворюємо CategoryDto на Category
                .name(productDto.getName())
                .characteristics(productDto.getCharacteristics())
                .build();
    }

    private ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .category(mapToCategoryDto(product.getCategory())) // Перетворюємо Category на CategoryDto
                .name(product.getName())
                .characteristics(product.getCharacteristics())
                .build();
    }


    private Category mapToCategory(CategoryDto categoryDto) {
        return Category.builder()
                .number(categoryDto.getNumber())
                .name(categoryDto.getName())
                .build();
    }

    private CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .number(category.getNumber())
                .name(category.getName())
                .build();
    }*/
}