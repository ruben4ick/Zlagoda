package com.zlagoda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProduct {

    private String upc;

    StoreProduct upcProm;

    Product product;

    private Double price;

    private Integer quantity;

    private Boolean isPromotional;
}