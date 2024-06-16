package com.zlagoda.converter;

import com.zlagoda.dto.ProductDto;
import com.zlagoda.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends Converter<Product, ProductDto> {

    public ProductConverter() {
        super(new ModelMapper(), Product.class, ProductDto.class);
    }

}
