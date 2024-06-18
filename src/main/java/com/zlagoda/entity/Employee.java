package com.zlagoda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    public enum Role {
        CASHIER,
        MANAGER
    }

    private String id;

    private String surname;

    private String name;

    private String patronymic;

    private Role role;

    private BigDecimal salary;

    private Date startDate;

    private Date birthDate;

    private String phoneNumber;

    private String city;

    private String street;

    private String zipCode;

    private String username;

    private String password;
}