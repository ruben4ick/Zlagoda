package com.zlagoda.service;

import com.zlagoda.dto.SaleDto;
import org.modelmapper.internal.Pair;

import java.util.List;

public interface SaleService extends GenericService<SaleDto, Pair<String, String>>{
    List<SaleDto> getByCheck(String check_number);
}
