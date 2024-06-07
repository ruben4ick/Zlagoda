package com.zlagoda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private Long id;

    private String name;

    private String manufacturer;

    private String characteristics;
}