package com.zlagoda.converter;

import com.zlagoda.dto.SaleDto;
import com.zlagoda.entity.Sale;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SaleConverter extends Converter<Sale, SaleDto> {

    public SaleConverter() {
        super(new ModelMapper(), Sale.class, SaleDto.class);
    }

}