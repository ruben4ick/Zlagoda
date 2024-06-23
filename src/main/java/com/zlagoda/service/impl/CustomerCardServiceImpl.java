package com.zlagoda.service.impl;

import com.zlagoda.converter.CustomerCardConverter;
import com.zlagoda.dao.CustomerCardDao;
import com.zlagoda.dto.CustomerCardDto;
import com.zlagoda.entity.CustomerCard;
import com.zlagoda.service.CustomerCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerCardServiceImpl implements CustomerCardService {

    private final CustomerCardDao customerCardDao;
    private final CustomerCardConverter customerCardConverter;
    @Override
    public List<CustomerCardDto> getAll() {
        return customerCardDao.getAll().stream()
                .map(customerCardConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerCardDto> getById(String cardNumber) {
        cardNumber = cardNumber.trim();
        return customerCardDao.getById(cardNumber)
                .map(customerCardConverter::convertToDto);
    }

    @Override
    public void create(CustomerCardDto customerCardDto) {
        customerCardDto.normalize();
        CustomerCard customerCard = customerCardConverter.convertToEntity(customerCardDto);
        customerCardDao.create(customerCard);
    }

    @Override
    public void update(CustomerCardDto customerCardDto) {
        customerCardDto.normalize();
        CustomerCard customerCard = customerCardConverter.convertToEntity(customerCardDto);
        customerCardDao.update(customerCard);
    }

    @Override
    public void delete(String cardNumber) {
        customerCardDao.delete(cardNumber);
    }

    @Override
    public List<CustomerCardDto> findByPercent(int percent) {
        List<CustomerCard> customerCards = customerCardDao.findByPercent(percent);
        return customerCards.stream()
                .map(customerCardConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerCardDto> findBySurname(String surname) {
        surname = surname.trim();
        List<CustomerCard> customerCards = customerCardDao.findBySurname(surname);
        return customerCards.stream()
                .map(customerCardConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
