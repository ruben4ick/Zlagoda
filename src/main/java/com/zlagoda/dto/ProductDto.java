package com.zlagoda.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    @NotNull(message = "Product category cannot be null")
    private CategoryDto category;

    @NotNull(message = "Product name cannot be null")
    @Size(min = 1, max = 50, message = "Product name length must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "Product characteristics cannot be null")
    @Size(min = 1, max = 100, message = "Product characteristics length must be between 1 and 100 characters")
    private String characteristics;

    public void normalize() {
        if (category != null)
            category.normalize();
        if (name != null)
            name = name.trim();
        if (characteristics != null)
            characteristics = characteristics.trim();
    }
}
