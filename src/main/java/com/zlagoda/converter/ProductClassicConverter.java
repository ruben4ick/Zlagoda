package com.zlagoda.converter;

import com.zlagoda.dto.ProductClassicDto;
import com.zlagoda.entity.ProductClassic;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductClassicConverter {

    private final ModelMapper modelMapper;

    public ProductClassicConverter() {
        this.modelMapper = new ModelMapper();
    }

    public ProductClassicDto convertToDto(ProductClassic entity) {
        return modelMapper.map(entity, ProductClassicDto.class);
    }

    public ProductClassic convertToEntity(ProductClassicDto dto) {
        return modelMapper.map(dto, ProductClassic.class);
    }
}
