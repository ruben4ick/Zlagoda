package com.zlagoda.dto;

import com.zlagoda.entity.Employee;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
public class EmployeeDto
{public enum Role {
    CASHIER,
    MANAGER
}

    private String id;

    private String surname;
    private String name;
    //private String password;
    private String patronymic;
    private Employee.Role role;
    private BigDecimal salary;
    private Date birthDate;
    private Date startDate;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
}
