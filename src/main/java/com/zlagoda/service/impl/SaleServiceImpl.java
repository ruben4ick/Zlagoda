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

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleDao saleDao;
    private final SaleConverter saleConverter;
    private final StoreProductService storeProductService;


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
    public void create(SaleDto saleDto) {
        Sale sale = saleConverter.convertToEntity(saleDto);
        saleDao.create(sale);
    }

    // я не розумію, що ти тут хтів зробити, тому я це чіпати не буду. з повагою bee
    /*@Override
    public void create(SaleDto saleDto) {
        Sale saleToCreate = saleConverter.convertToEntity(saleDto);
        saleToCreate.setSellingPrice(storeProductService.getPriceByUpc(saleDto.getStoreProductUpc()));

        int availableAmount = storeProductService.getAmountByUpc(saleDto.getStoreProductUpc());
        if (saleDto.getProductNumber() > availableAmount)
            throw new EntityCreationException("Not enough amount of store products with id = " +
                    saleDto.getStoreProductUpc() + " to create check");

        storeProductService.subtractAmountByUpc(saleDto.getStoreProductUpc(), saleDto.getProductNumber());

        saleDao.create(saleToCreate);
    }*/

    @Override
    public void update(SaleDto e) {
        //not needed I think
    }

    @Override
    public void delete(Pair<String, String> upcAndCheckNumber) {
        saleDao.delete(upcAndCheckNumber);

    }
}
