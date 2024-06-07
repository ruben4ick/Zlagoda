package com.zlagoda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerCard {

    private Long card_number;

    private String surname;

    private String name;

    private String patronymic;

    private String number;

    private String city;

    private String street;

    private String zipCode;

    private Integer percent;
}