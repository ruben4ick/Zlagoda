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

    @Nullable
    private Boolean isPromotional;

    public void normalize() {
        if (upc != null)
            upc = upc.trim();
        if (upcProm != null)
            upcProm.normalize();
        if (product != null)
            product.normalize();
    }
}
