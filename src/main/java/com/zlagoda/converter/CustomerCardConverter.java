package com.zlagoda.converter;

import com.zlagoda.dto.CustomerCardDto;
import com.zlagoda.entity.CustomerCard;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerCardConverter extends Converter<CustomerCard, CustomerCardDto> {
    public CustomerCardConverter() {
        super(new ModelMapper(), CustomerCard.class, CustomerCardDto.class);
    }

}