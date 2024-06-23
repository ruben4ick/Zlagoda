package com.zlagoda.service.impl;

import com.zlagoda.converter.CheckConverter;
import com.zlagoda.converter.SaleConverter;
import com.zlagoda.dao.CheckDao;
import com.zlagoda.dao.SaleDao;
import com.zlagoda.dto.CheckDto;
import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.dto.SaleDto;
import com.zlagoda.entity.Check;
import com.zlagoda.entity.Sale;
import com.zlagoda.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final CheckDao checkDao;
    private final SaleDao saleDao;
    private final CheckConverter checkConverter;
    private final SaleConverter saleConverter;

    private final CustomerCardService customerCardService;
    private final StoreProductService storeProductService;
    private final SaleService saleService;
    private final EmployeeService employeeService;

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
    public List<CheckDto> getByEmplId(String employee_id){
        return checkDao.getByEmplId(employee_id).stream()
                .map(checkConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckDto> getByEmplSurname(String employee_surname){
        List<EmployeeDto> employees = employeeService.getBySurname(employee_surname);
        List<CheckDto> checks = new ArrayList<>();
        for (EmployeeDto employee : employees){
            checks.addAll(getByEmplId(employee.getId()));
        }
        return checks;
    }

    @Override
    public List<CheckDto> selectByDate(List<CheckDto> checks, LocalDateTime start, LocalDateTime end){
        List<CheckDto> selectedChecks = new ArrayList<>();
        for (CheckDto check : checks){
            if ((check.getPrintDate().isAfter(start) && check.getPrintDate().isBefore(end))
                    || check.getPrintDate().isEqual(start) || check.getPrintDate().isEqual(end)){
                selectedChecks.add(check);
            }
        }
        return selectedChecks;
    }
    @Override
    public BigDecimal sumOfChecks(List<CheckDto> checks){
        BigDecimal sum = BigDecimal.ZERO;

        for (CheckDto check : checks) {
            sum = sum.add(check.getTotalSum());
        }

        return sum;
    }

    @Override
    public void create(CheckDto checkDto) {
        checkDto.setPrintDate(LocalDateTime.now());
        BigDecimal totalSum = checkDto.getSales().stream()
                .map(sale -> sale.getSellingPrice().multiply(BigDecimal.valueOf(sale.getProductNumber())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal vat = totalSum.multiply(BigDecimal.valueOf(0.2));
        checkDto.setTotalSum(totalSum);
        checkDto.setVat(vat);

        Check check = checkConverter.convertToEntity(checkDto);
        checkDao.create(check);

        // Зберігаємо всі продажі
        for (SaleDto saleDto : checkDto.getSales()) {
            Sale sale = saleConverter.convertToEntity(saleDto);
            sale.setCheckNumber(check.getCheckNumber()); // Встановлюємо номер чека для кожного продажу
            saleDao.create(sale);
        }
    }

    @Override
    public void update(CheckDto checkDto) {
        //not needed
    }

    @Override
    public void delete(String checkNumber) {
        checkDao.delete(checkNumber);
    }

    /*public BigDecimal countSum(CheckDto checkDto) {
        return checkDto.getSales().stream()
                .map(sale -> storeProductService.getPriceByUpc(sale.getStoreProductUpc())
                        .multiply(BigDecimal.valueOf(sale.getProductNumber()))
                ).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(4, RoundingMode.HALF_UP);
    }*/

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
