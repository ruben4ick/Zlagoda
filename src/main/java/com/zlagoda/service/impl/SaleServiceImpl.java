package com.zlagoda.service.impl;

import com.zlagoda.entity.CustomerCard;
import com.zlagoda.entity.Sale;
import org.modelmapper.internal.Pair;
import com.zlagoda.converter.SaleConverter;
import com.zlagoda.dao.SaleDao;
import com.zlagoda.dto.SaleDto;
import com.zlagoda.service.SaleService;
import com.zlagoda.service.StoreProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleDao saleDao;
    private final SaleConverter saleConverter;


    @Override
    public List<SaleDto> getAll() {
        return saleDao.getAll()
                .stream()
                .map(saleConverter::convertToDto)
                .toList();
    }

    @Override
    public Optional<SaleDto> getById(Pair<String, String> upcAndCheckNumber) {
        return saleDao.getById(upcAndCheckNumber)
                .map(saleConverter::convertToDto);

    }

    @Override
    public List<SaleDto> getByCheck(String check_number){
        check_number = check_number.trim();
        return saleDao.getByCheck(check_number).stream()
                .map(saleConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void create(SaleDto saleDto) {
        saleDto.normalize();
        Sale sale = saleConverter.convertToEntity(saleDto);
        saleDao.create(sale);
    }

    @Override
    public void update(SaleDto e) {
        //not needed I think
    }

    @Override
    public void delete(Pair<String, String> upcAndCheckNumber) {
        saleDao.delete(upcAndCheckNumber);

    }
}
