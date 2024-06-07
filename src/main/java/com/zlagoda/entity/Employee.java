package com.zlagoda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class Employee {

    public enum Role {
        CASHIER,
        MANAGER
    }

    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    private Role role;

    private Double salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String phoneNumber;

    private String city;

    private String street;

    private String zip_code;
}