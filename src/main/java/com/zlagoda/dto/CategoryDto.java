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
public class CategoryDto {

    private Long number;

    @NotNull(message = "Surname cannot be null")
    @Size(min = 1, max = 50, message = "Category name must be between 1 and 50 characters")
    private String name;

    public void normalize() {
        name = name.trim();
    }
}
