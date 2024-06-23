package com.zlagoda.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductDto {

    private String upc;

    @Nullable
    private StoreProductDto upcProm;

    private ProductDto product;

    private Double price;

    private Integer quantity;

    private Boolean isPromotional;
}
