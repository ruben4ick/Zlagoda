package com.zlagoda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private String position;

    private Double salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String phoneNumber;

    private String address;
}