package com.zlagoda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sale {

    private String storeProductUPC;

    private Long check_number;

    private Integer quantity;

    private Double salePrice;
}