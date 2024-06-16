package com.zlagoda.converter;

import com.zlagoda.dto.StoreProductDto;
import com.zlagoda.entity.StoreProduct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StoreProductConverter extends Converter<StoreProduct, StoreProductDto> {

    public StoreProductConverter() {
        super(new ModelMapper(), StoreProduct.class, StoreProductDto.class);
    }

}