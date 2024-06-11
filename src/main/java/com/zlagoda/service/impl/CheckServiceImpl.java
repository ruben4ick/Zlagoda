package com.zlagoda.service.impl;

import com.zlagoda.converter.CheckConverter;
import com.zlagoda.dao.CheckDao;
import com.zlagoda.dto.CheckDto;
import com.zlagoda.dto.CustomerCardDto;
import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Check;
import com.zlagoda.entity.CustomerCard;
import com.zlagoda.entity.Employee;
import com.zlagoda.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckServiceImpl implements CheckService {

    private final CheckDao checkDao;
    private final CheckConverter checkConverter;

    @Autowired
    public CheckServiceImpl(CheckDao checkDao, CheckConverter checkConverter) {
        this.checkDao = checkDao;
        this.checkConverter = checkConverter;
    }

    @Override
    public List<CheckDto> getAll() {
        return checkDao.getAll().stream()
                .map(checkConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CheckDto> getById(String checkNumber) {
        return checkDao.getById(checkNumber)
                .map(checkConverter::convertToDto);
    }

    @Override
    public void create(CheckDto checkDto) {
        Check check = checkConverter.convertToEntity(checkDto);
        checkDao.create(check);
    }

    @Override
    public void update(CheckDto checkDto) {
        //not needed
    }

    @Override
    public void delete(String checkNumber) {
        checkDao.delete(checkNumber);
    }

    /*private Check mapToCheck(CheckDto checkDto) {
        return Check.builder()
                .checkNumber(checkDto.getCheckNumber())
                .employee(Employee.builder()
                        .id(checkDto.getEmployee().getId())
                        .surname(checkDto.getEmployee().getSurname())
                        .name(checkDto.getEmployee().getName())
                        .build())
                .customerCard(checkDto.getCustomerCard() != null ? CustomerCard.builder()
                        .cardNumber(checkDto.getCustomerCard().getCardNumber())
                        .surname(checkDto.getCustomerCard().getSurname())
                        .name(checkDto.getCustomerCard().getName())
                        .build() : null)
                .printDate(checkDto.getPrintDate())
                .totalSum(checkDto.getTotalSum())
                .vat(checkDto.getVat())
                .build();
    }

    private CheckDto mapToCheckDto(Check check) {
        return CheckDto.builder()
                .checkNumber(check.getCheckNumber())
                .employee(EmployeeDto.builder()
                        .id(check.getEmployee().getId())
                        .surname(check.getEmployee().getSurname())
                        .name(check.getEmployee().getName())
                        .build())
                .customerCard(check.getCustomerCard() != null ? CustomerCardDto.builder()
                        .cardNumber(check.getCustomerCard().getCardNumber())
                        .surname(check.getCustomerCard().getSurname())
                        .name(check.getCustomerCard().getName())
                        .build() : null)
                .printDate(check.getPrintDate())
                .totalSum(check.getTotalSum())
                .vat(check.getVat())
                .build();
    }*/
}
