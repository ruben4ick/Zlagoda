package com.zlagoda.converter;

import com.zlagoda.dto.CheckDto;
import com.zlagoda.entity.Check;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CheckConverter extends Converter<Check, CheckDto>{
    public CheckConverter() {
        super(new ModelMapper(), Check.class, CheckDto.class);
    }
}

