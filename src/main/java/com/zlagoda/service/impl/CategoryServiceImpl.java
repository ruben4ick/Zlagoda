package com.zlagoda.service.impl;

import com.zlagoda.dao.CategoryDao;
import com.zlagoda.dto.CategoryDto;
import com.zlagoda.dto.CategorySalesDto;
import com.zlagoda.entity.Category;
import com.zlagoda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryDao.getAll().stream()
                .map(this::mapToCategoryDto)
                .toList();
    }

    @Override
    public Optional<CategoryDto> getById(Long categoryId) {
        return categoryDao.getById(categoryId)
                .map(this::mapToCategoryDto);
    }

    @Override
    public void create(final CategoryDto categoryDto) {
        Category category = mapToCategory(categoryDto);
        categoryDao.create(category);
    }

    @Override
    public void update(final CategoryDto categoryDto) {
        Category category = mapToCategory(categoryDto);
        categoryDao.update(category);
    }

    @Override
    public void delete(Long categoryId) {
        categoryDao.delete(categoryId);
    }

    public List<CategorySalesDto> findTotalSalesByCategory() {
        return categoryDao.findTotalSalesByCategory();
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
    }
}


