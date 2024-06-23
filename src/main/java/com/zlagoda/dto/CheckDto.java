package com.zlagoda.dto;

import com.zlagoda.entity.CustomerCard;
import com.zlagoda.entity.Employee;
import com.zlagoda.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckDto {

    private String checkNumber;

    private EmployeeDto employee;

    private CustomerCardDto customerCard;

    private LocalDateTime printDate;

    private BigDecimal totalSum;

    private BigDecimal vat;

    private List<SaleDto> sales;

    public void normalize() {
        checkNumber = checkNumber.trim();
        employee.normalize();
        customerCard.normalize();
    }
}
