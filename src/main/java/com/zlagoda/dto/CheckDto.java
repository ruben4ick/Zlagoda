package com.zlagoda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckDto {

    private String checkNumber;

    private EmployeeDto employee;

    private CustomerCardDto customerCard;

    private Date printDate;

    private BigDecimal totalSum;

    private BigDecimal vat;
}
