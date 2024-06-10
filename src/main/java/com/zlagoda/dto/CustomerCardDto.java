package com.zlagoda.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCardDto {

    private String cardNumber;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    private Integer percent;
}
