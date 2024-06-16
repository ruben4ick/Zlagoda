package com.zlagoda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCard {

    private String cardNumber;

    private String surname;

    private String name;

    private String patronymic;

    private String phoneNumber;

    private String city;

    private String street;

    private String zipCode;

    private int percent;
}