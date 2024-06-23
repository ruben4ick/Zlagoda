package com.zlagoda.dto;

import jakarta.annotation.Nullable;
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
public class CustomerCardDto {

    private String cardNumber;

    @NotNull(message = "surname can not be null")
    @Size(max = 50, message = "surname length cannot be larger than 50 characters")
    private String surname;

    @NotNull(message = "name can not be null")
    @Size(max = 50, message = "name length cannot be larger than 50 characters")
    private String name;

    @Nullable
    @Size(max = 50, message = "patronymic length cannot be larger than 50 characters")
    private String patronymic;

    @NotNull(message = "phone number can not be null")
    @Size(max = 13, message = "phone number length cannot be larger than 13 characters")
    private String phoneNumber;

    @Nullable
    @Size(max = 50, message = "city length cannot be larger than 50 characters")
    private String city;

    @Nullable
    @Size(max = 50, message = "street length cannot be larger than 50 characters")
    private String street;

    @Nullable
    @Size(max = 9, message = "zip code length cannot be larger than 50 characters")
    private String zipCode;

    @NotNull(message = "percent can not be null")
    private int percent;

    public void normalize() {
        cardNumber = cardNumber.trim();
        surname = surname.trim();
        name = name.trim();
        if (patronymic != null)
            patronymic = patronymic.trim();
        phoneNumber = phoneNumber.trim();
        if (city != null)
            city = city.trim();
        if (street != null)
            street = street.trim();
        if (zipCode != null)
            zipCode = zipCode.trim();
    }
}
