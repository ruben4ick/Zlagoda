package com.zlagoda.service;

import com.zlagoda.dto.CustomerCardDto;

import java.util.List;

public interface CustomerCardService extends GenericService<CustomerCardDto, String>{

    List<CustomerCardDto> findByPercent(int percent);
}
