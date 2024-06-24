package com.zlagoda.service.impl;

import com.zlagoda.converter.CheckConverter;
import com.zlagoda.converter.SaleConverter;
import com.zlagoda.dao.CheckDao;
import com.zlagoda.dao.SaleDao;
import com.zlagoda.dto.CheckDto;
import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.dto.SaleDto;
import com.zlagoda.dto.CustomerCardDto;
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
        checkNumber = checkNumber.trim();
        return checkDao.getById(checkNumber)
                .map(checkConverter::convertToDto);
    }

    @Override
    public List<CheckDto> getByEmplId(String employee_id){
        employee_id = employee_id.trim();
        return checkDao.getByEmplId(employee_id).stream()
                .map(checkConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckDto> getByEmplSurname(String employee_surname){
        employee_surname = employee_surname.trim();
        List<EmployeeDto> employees = employeeService.getBySurname(employee_surname);
        List<CheckDto> checks = new ArrayList<>();
        for (EmployeeDto employee : employees){
            checks.addAll(getByEmplId(employee.getId()));
        }
        return checks;
    }

    @Override
    public List<CheckDto> selectByDate(List<CheckDto> checks, LocalDateTime start, LocalDateTime end){
        // rounding time for convenience
//        LocalDateTime startOfDay = start.withHour(0).withMinute(0).withSecond(0).withNano(0);
//        LocalDateTime endOfDay = end.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
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
        checkDto.normalize();
        checkDto.setPrintDate(LocalDateTime.now());
        BigDecimal totalSum = checkDto.getSales().stream()
                .map(sale -> sale.getSellingPrice().multiply(BigDecimal.valueOf(sale.getProductNumber())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        String customerCardNumber = checkDto.getCustomerCard().getCardNumber();
        if (!customerCardNumber.isEmpty()) {
            double percent = customerCardService.getById(customerCardNumber)
                    .map(CustomerCardDto::getPercent)
                    .orElse(0);
            totalSum = totalSum.multiply(BigDecimal.valueOf(1 - percent / 100));
        } else checkDto.setCustomerCard(null);
        BigDecimal vat = totalSum.multiply(BigDecimal.valueOf(0.2));
        checkDto.setTotalSum(totalSum);
        checkDto.setVat(vat);

        Check check = checkConverter.convertToEntity(checkDto);
        checkDao.create(check);

        for (SaleDto saleDto : checkDto.getSales()) {
            storeProductService.updateProductQuantity(saleDto.getStoreProduct().getUpc(), saleDto.getProductNumber());

            if (saleDto.getStoreProduct().getIsPromotional() ) {
                saleDto.setStoreProduct(storeProductService.findByUpcProm(saleDto.getStoreProduct().getUpc()).get());
            }
            Sale sale = saleConverter.convertToEntity(saleDto);
            sale.setCheckNumber(check.getCheckNumber());
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
}
