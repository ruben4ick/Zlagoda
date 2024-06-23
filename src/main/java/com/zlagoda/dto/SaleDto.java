package com.zlagoda.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {

    private String storeProductUpc;

    @NotNull(message = "cant be null")
    @Min(value = 1, message = "product number must  be more than 1")
    private int productNumber;

    private BigDecimal sellingPrice;

    public void normalize() {
        storeProductUpc = storeProductUpc.trim();
    }
}
