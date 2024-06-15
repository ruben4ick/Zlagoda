package com.zlagoda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    private StoreProduct storeProduct;
    private String checkNumber;
    private int productNumber;
    private BigDecimal sellingPrice;

}