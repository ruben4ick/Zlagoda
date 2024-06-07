package com.zlagoda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class Check {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Double totalAmount;

    private Double vat;
}