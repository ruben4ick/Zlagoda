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
        return customerCardDao.getById(cardNumber)
                .map(customerCardConverter::convertToDto);
    }

    @Override
    public void create(CustomerCardDto customerCardDto) {
        CustomerCard customerCard = customerCardConverter.convertToEntity(customerCardDto);
        customerCardDao.create(customerCard);
    }

    @Override
    public void update(CustomerCardDto customerCardDto) {
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
        List<CustomerCard> customerCards = customerCardDao.findBySurname(surname);
        return customerCards.stream()
                .map(customerCardConverter::convertToDto)
                .collect(Collectors.toList());
    }

   /* private CustomerCard mapToCustomerCard(CustomerCardDto customerCardDto) {
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
    }*/
}
