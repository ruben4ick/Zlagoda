package com.zlagoda.converter;

import com.zlagoda.dto.CategoryDto;
import com.zlagoda.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends Converter<Category, CategoryDto> {
    public CategoryConverter() {
        super(new ModelMapper(), Category.class, CategoryDto.class);
    }
}
