package com.zlagoda.service.impl;

import com.zlagoda.dao.CustomerCardDao;
import com.zlagoda.dto.CustomerCardDto;
import com.zlagoda.entity.CustomerCard;
import com.zlagoda.service.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerCardServiceImpl implements CustomerCardService {

    private final CustomerCardDao customerCardDao;

    @Autowired
    public CustomerCardServiceImpl(CustomerCardDao customerCardDao) {
        this.customerCardDao = customerCardDao;
    }

    @Override
    public List<CustomerCardDto> getAll() {
        return customerCardDao.getAll().stream()
                .map(this::mapToCustomerCardDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerCardDto> getById(String cardNumber) {
        return customerCardDao.getById(cardNumber)
                .map(this::mapToCustomerCardDto);
    }

    @Override
    public void create(CustomerCardDto customerCardDto) {
        CustomerCard customerCard = mapToCustomerCard(customerCardDto);
        customerCardDao.create(customerCard);
    }

    @Override
    public void update(CustomerCardDto customerCardDto) {
        CustomerCard customerCard = mapToCustomerCard(customerCardDto);
        customerCardDao.update(customerCard);
    }

    @Override
    public void delete(String cardNumber) {
        customerCardDao.delete(cardNumber);
    }

    private CustomerCard mapToCustomerCard(CustomerCardDto customerCardDto) {
        return CustomerCard.builder()
                .cardNumber(customerCardDto.getCardNumber())
                .surname(customerCardDto.getSurname())
                .name(customerCardDto.getName())
                .patronymic(customerCardDto.getPatronymic())
                .phoneNumber(customerCardDto.getPhoneNumber())
                .city(customerCardDto.getCity())
                .street(customerCardDto.getStreet())
                .zipCode(customerCardDto.getZipCode())
                .percent(customerCardDto.getPercent())
                .build();
    }

    private CustomerCardDto mapToCustomerCardDto(CustomerCard customerCard) {
        return CustomerCardDto.builder()
                .cardNumber(customerCard.getCardNumber())
                .surname(customerCard.getSurname())
                .name(customerCard.getName())
                .patronymic(customerCard.getPatronymic())
                .phoneNumber(customerCard.getPhoneNumber())
                .city(customerCard.getCity())
                .street(customerCard.getStreet())
                .zipCode(customerCard.getZipCode())
                .percent(customerCard.getPercent())
                .build();
    }
}
