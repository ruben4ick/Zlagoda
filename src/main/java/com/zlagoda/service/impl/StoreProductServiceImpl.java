package com.zlagoda.service.impl;

import com.zlagoda.converter.StoreProductConverter;
import com.zlagoda.dao.StoreProductDao;
import com.zlagoda.dto.CategoryDto;
import com.zlagoda.dto.ProductDto;
import com.zlagoda.dto.StoreProductDto;
import com.zlagoda.entity.Category;
import com.zlagoda.entity.Product;
import com.zlagoda.entity.StoreProduct;
import com.zlagoda.service.StoreProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreProductServiceImpl implements StoreProductService {

    private final StoreProductDao storeProductDao;
    private final StoreProductConverter converter;

    @Override
    public List<StoreProductDto> getAll() {
        return storeProductDao.getAll().stream()
                .map(converter::convertToDto)
                .toList();
    }

    @Override
    public Optional<StoreProductDto> getById(String upc) {
        return storeProductDao.getById(upc)
                .map(converter::convertToDto);
    }

    @Override
    public void create(StoreProductDto storeProductDto) {
        StoreProduct storeProduct = converter.convertToEntity(storeProductDto);
        storeProduct.setIsPromotional(false);
        storeProductDao.create(storeProduct);
    }

    @Override
    public void update(StoreProductDto storeProductDto) {
        StoreProduct storeProduct = converter.convertToEntity(storeProductDto);
        storeProductDao.update(storeProduct);
    }

    @Override
    public void delete(String upc) {
        storeProductDao.delete(upc);
    }

    @Override
    public List<StoreProductDto> getPromotionalProducts() {
        return storeProductDao.getPromotionalProducts().stream()
                .map(this::mapToStoreProductDto)
                .collect(Collectors.toList());
    }

    private StoreProduct mapToStoreProduct(StoreProductDto storeProductDto) {
        return StoreProduct.builder()
                .upc(storeProductDto.getUpc())
                .upcProm(storeProductDto.getUpcProm() != null ? mapToStoreProduct(storeProductDto.getUpcProm()) : null)
                .product(storeProductDto.getProduct() != null ? mapToProduct(storeProductDto.getProduct()) : null)
                .price(storeProductDto.getPrice())
                .quantity(storeProductDto.getQuantity())
                .isPromotional(storeProductDto.getIsPromotional())
                .build();
    }

    private Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .characteristics(productDto.getCharacteristics())
                .category(productDto.getCategory() != null ? mapToCategory(productDto.getCategory()) : null)
                .build();
    }

    private Category mapToCategory(CategoryDto categoryDto) {
        return Category.builder()
                .number(categoryDto.getNumber())
                .name(categoryDto.getName())
                .build();
    }


    private StoreProductDto mapToStoreProductDto(StoreProduct storeProduct) {
        return StoreProductDto.builder()
                .upc(storeProduct.getUpc())
                .upcProm(storeProduct.getUpcProm() != null ? mapToStoreProductDto(storeProduct.getUpcProm()) : null)
                .product(storeProduct.getProduct() != null ? mapToProductDto(storeProduct.getProduct()) : null)
                .price(storeProduct.getPrice())
                .quantity(storeProduct.getQuantity())
                .isPromotional(storeProduct.getIsPromotional())
                .build();
    }

    private ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .characteristics(product.getCharacteristics())
                .category(product.getCategory() != null ? mapToCategoryDto(product.getCategory()) : null)
                .build();
    }

    private CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .number(category.getNumber())
                .name(category.getName())
                .build();
    }

}
