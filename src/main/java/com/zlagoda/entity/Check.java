package com.zlagoda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Check {

    private String checkNumber;

    private String idEmployee;

    private String cardNumber;

    private Date printDate;

    private BigDecimal totalSum;

    private BigDecimal vat;
}