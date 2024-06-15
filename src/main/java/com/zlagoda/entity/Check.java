package com.zlagoda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Check {

    private String checkNumber;

    private Employee employee;

    private CustomerCard customerCard;

    private LocalDateTime printDate;

    private BigDecimal totalSum;

    private BigDecimal vat;

    private List<Sale> sales;
}