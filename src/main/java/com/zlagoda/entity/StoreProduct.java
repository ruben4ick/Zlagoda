package com.zlagoda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreProduct {

    private String upc;

    private Double price;

    private Integer quantity;

    private Boolean isPromotional;
}