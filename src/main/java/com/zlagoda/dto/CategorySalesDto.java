package com.zlagoda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorySalesDto {
    private Long categoryNumber;
    private String categoryName;
    private BigDecimal totalSales;
}